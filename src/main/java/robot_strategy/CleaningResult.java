package robot_strategy;

import java.util.Locale;

public final class CleaningResult {
    private final String strategyName;
    private final int cleanedCells;
    private final int freeCells;
    private final int pathLength;

    public CleaningResult(String strategyName, int cleanedCells, int freeCells, int pathLength) {
        this.strategyName = strategyName;
        this.cleanedCells = cleanedCells;
        this.freeCells = freeCells;
        this.pathLength = pathLength;
    }

    public double getCoveragePercent() {
        return freeCells == 0 ? 100.0 : cleanedCells * 100.0 / freeCells;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: cleaned %d/%d cells (%.1f%%), path length %d",
                strategyName, cleanedCells, freeCells, getCoveragePercent(), pathLength);
    }
}