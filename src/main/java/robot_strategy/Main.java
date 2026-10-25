package robot_strategy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Room room = createRoom();
        List<CleaningStrategy> strategies = List.of(
                new ZigZagCleaning(),
                new SpiralCleaning(),
                new WallFollowCleaning(),
                new RandomBounceCleaning(42)
        );

        RobotVacuum vacuum = new RobotVacuum(strategies.get(0));
        for (CleaningStrategy strategy : strategies) {
            vacuum.setStrategy(strategy);
            System.out.println(vacuum.startCleaning(room));
        }
    }

    private static Room createRoom() {
        Room room = new Room(6, 5);
        room.addObstacle(2, 2);
        room.addObstacle(3, 2);
        return room;
    }
}