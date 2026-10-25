package robot_strategy;

public class SpiralCleaning implements CleaningStrategy {
    private static final String NAME = "Spiral";

    @Override
    public CleaningResult clean(Room room) {
        CoverageTracker tracker = new CoverageTracker(room);
        int top = 0;
        int bottom = room.getHeight() - 1;
        int left = 0;
        int right = room.getWidth() - 1;

        while (top <= bottom && left <= right) {
            for (int x = left; x <= right; x++) {
                tracker.tryMove(x, top);
            }
            top++;
            for (int y = top; y <= bottom; y++) {
                tracker.tryMove(right, y);
            }
            right--;
            if (top <= bottom) {
                for (int x = right; x >= left; x--) {
                    tracker.tryMove(x, bottom);
                }
                bottom--;
            }
            if (left <= right) {
                for (int y = bottom; y >= top; y--) {
                    tracker.tryMove(left, y);
                }
                left++;
            }
        }
        return tracker.toResult(NAME);
    }
}