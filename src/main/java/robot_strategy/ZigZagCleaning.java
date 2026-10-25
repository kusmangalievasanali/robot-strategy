package robot_strategy;

public class ZigZagCleaning implements CleaningStrategy {
    private static final String NAME = "Zigzag";

    @Override
    public CleaningResult clean(Room room) {
        CoverageTracker tracker = new CoverageTracker(room);
        for (int y = 0; y < room.getHeight(); y++) {
            boolean leftToRight = y % 2 == 0;
            for (int i = 0; i < room.getWidth(); i++) {
                int x = leftToRight ? i : room.getWidth() - 1 - i;
                tracker.tryMove(x, y);
            }
        }
        return tracker.toResult(NAME);
    }
}