package robot_strategy;

import java.util.Objects;

public class RobotVacuum {
    private CleaningStrategy strategy;

    public RobotVacuum(CleaningStrategy strategy) {
        setStrategy(strategy);
    }

    public final void setStrategy(CleaningStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Cleaning strategy must not be null");
    }

    public CleaningResult startCleaning(Room room) {
        return strategy.clean(room);
    }
}