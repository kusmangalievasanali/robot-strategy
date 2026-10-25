package robot_strategy;

public class WallFollowCleaning implements CleaningStrategy {
    private static final String NAME = "Wall follow";

    @Override
    public CleaningResult clean(Room room) {
        CoverageTracker tracker = new CoverageTracker(room);
        int width = room.getWidth();
        int height = room.getHeight();

        for (int x = 0; x < width; x++) {
            tracker.tryMove(x, 0);
        }
        for (int y = 1; y < height; y++) {
            tracker.tryMove(width - 1, y);
        }
        if (height > 1) {
            for (int x = width - 2; x >= 0; x--) {
                tracker.tryMove(x, height - 1);
            }
        }
        if (width > 1) {
            for (int y = height - 2; y >= 1; y--) {
                tracker.tryMove(0, y);
            }
        }
        return tracker.toResult(NAME);
    }
}