package robot_strategy;

import java.util.Random;

public class RandomBounceCleaning implements CleaningStrategy {
    private static final String NAME = "Random bounce";
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private final long seed;

    public RandomBounceCleaning(long seed) {
        this.seed = seed;
    }

    @Override
    public CleaningResult clean(Room room) {
        CoverageTracker tracker = new CoverageTracker(room);
        Random random = new Random(seed);
        int x = 0;
        int y = 0;
        tracker.tryMove(x, y);

        int maxMoves = room.getWidth() * room.getHeight() * 2;
        for (int i = 0; i < maxMoves; i++) {
            int[] direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            if (tracker.tryMove(x + direction[0], y + direction[1])) {
                x += direction[0];
                y += direction[1];
            }
        }
        return tracker.toResult(NAME);
    }
}