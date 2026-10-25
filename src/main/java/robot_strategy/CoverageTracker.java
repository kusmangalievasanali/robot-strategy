package robot_strategy;

class CoverageTracker {
    private final Room room;
    private final boolean[][] cleaned;
    private int cleanedCells;
    private int pathLength;
    private int lastX = -1;
    private int lastY = -1;

    CoverageTracker(Room room) {
        this.room = room;
        this.cleaned = new boolean[room.getWidth()][room.getHeight()];
    }

    boolean tryMove(int x, int y) {
        if (!room.isFree(x, y)) {
            return false;
        }
        if (hasPreviousPosition()) {
            pathLength += Math.abs(x - lastX) + Math.abs(y - lastY);
        }
        lastX = x;
        lastY = y;
        markCleaned(x, y);
        return true;
    }

    CleaningResult toResult(String strategyName) {
        return new CleaningResult(strategyName, cleanedCells, room.countFreeCells(), pathLength);
    }

    private boolean hasPreviousPosition() {
        return lastX >= 0;
    }

    private void markCleaned(int x, int y) {
        if (!cleaned[x][y]) {
            cleaned[x][y] = true;
            cleanedCells++;
        }
    }
}