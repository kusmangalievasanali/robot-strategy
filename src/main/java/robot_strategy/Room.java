package robot_strategy;

public class Room {
    private final int width;
    private final int height;
    private final boolean[][] blocked;

    public Room(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Room size must be positive: " + width + "x" + height);
        }
        this.width = width;
        this.height = height;
        this.blocked = new boolean[width][height];
    }

    public void addObstacle(int x, int y) {
        if (!isInside(x, y)) {
            throw new IllegalArgumentException("Obstacle is outside the room: " + x + "," + y);
        }
        if (x == 0 && y == 0) {
            throw new IllegalArgumentException("Cell 0,0 is the docking station");
        }
        blocked[x][y] = true;
    }

    public boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isFree(int x, int y) {
        return isInside(x, y) && !blocked[x][y];
    }

    public int countFreeCells() {
        int free = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!blocked[x][y]) {
                    free++;
                }
            }
        }
        return free;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}