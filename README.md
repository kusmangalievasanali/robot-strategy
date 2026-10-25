# Strategy Pattern — Robot Vacuum Cleaning Modes

Implementation of the **Strategy** behavioral design pattern in Java.

## Topic

A robot vacuum can clean a room with different algorithms. Each algorithm is a separate
strategy, and the robot can switch between them at runtime without changing its own code.
Every strategy really traverses a grid room (with obstacles) and reports how many cells
it cleaned and how long its path was.

## Pattern Structure

| Role | Class | Description |
|---|---|---|
| Strategy Interface | `CleaningStrategy` | `CleaningResult clean(Room room)` |
| Concrete Strategy | `ZigZagCleaning` | Row by row, alternating direction |
| Concrete Strategy | `SpiralCleaning` | Spiral from the outer edge to the center |
| Concrete Strategy | `WallFollowCleaning` | Only along the walls (fast, partial coverage) |
| Concrete Strategy | `RandomBounceCleaning` | Random moves with a fixed seed (repeatable) |
| Context | `RobotVacuum` | Holds a `CleaningStrategy`, delegates `clean()`, supports `setStrategy()` |
| Client | `Main` | Chooses strategies and switches them at runtime |

Supporting classes: `Room` (grid with obstacles), `CleaningResult` (immutable result),
`CoverageTracker` (shared counting of cleaned cells and path length).

## How to Run

1. Open the project in IntelliJ IDEA and wait for Gradle sync.
2. Open `src/main/java/robot_strategy/Main.java`.
3. Click the green Run button next to `main()` (or press `Shift+F10`).

Or from the terminal:

```
.\gradlew.bat build
java -cp build/classes/java/main robot_strategy.Main
```

## Example Output

```
Zigzag: cleaned 28/28 cells (100.0%), path length 29
Spiral: cleaned 28/28 cells (100.0%), path length 27
Wall follow: cleaned 18/28 cells (64.3%), path length 17
Random bounce: cleaned 13/28 cells (46.4%), path length 43
```

The room is 6x5 with two obstacles (28 free cells). `Random bounce` uses a fixed seed,
so its result is the same on every run.

## Clean Code Principles Applied

1. **Conditional logic replaced by polymorphism** — there is no `if` or `switch` on the
   strategy type anywhere. `RobotVacuum` simply calls `strategy.clean(room)`, and the
   chosen object decides how to clean.

2. **Meaningful, intention-revealing names** — `ZigZagCleaning`, `SpiralCleaning`,
   `WallFollowCleaning` and `RandomBounceCleaning` describe the algorithm; `startCleaning`
   and `setStrategy` describe the actions.

3. **Single Responsibility** — each strategy implements exactly one algorithm.
   `Room` only describes geometry, `CleaningResult` only holds a result, and
   `CoverageTracker` only counts cleaned cells and path length.

4. **Context stays algorithm-agnostic** — `RobotVacuum` depends only on the
   `CleaningStrategy` interface and knows nothing about the internals of any concrete
   strategy. Switching at runtime is a single `setStrategy()` call.

5. **Easy extensibility (Open/Closed)** — a new strategy (for example `SnakeByColumns`)
   is added by creating
