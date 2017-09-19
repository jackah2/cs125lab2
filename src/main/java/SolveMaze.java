import edu.illinois.cs.cs125.lib.mazemaker.Maze;

/**
 * Solve a randomly-generated maze.
 *
 * @see <a href="https://github.com/cs125-illinois/mazemaker">Mazemaker on GitHub</a>
 * @see <a href="https://cs125-illinois.github.io/mazemaker/">Mazemaker Documentation</a>
 * @see <a href="https://cs125.cs.illinois.edu/lab/2/#maze">Lab 2 Writeup</a>
 */
@SuppressWarnings("checkstyle:emptyblock")
public class SolveMaze {

    /**
     * Implement your maze solving algorithm here.
     *
     * @param unused unused input arguments
     * @throws InterruptedException thread.sleep()
     */

    public static void main(final String[] unused) throws InterruptedException {
        /*
         * Create a new 10 x 10 maze. Feel free to change these values.
         */
        Maze maze = new Maze(10, 10);

        /*
         * Pick (0, 0), the bottom left corner, as the starting point.
         * Put the end in the top right corner.
         */
        maze.startAtZero();
        maze.endAtTopRight();

        /*
         * You should be able to solve a 10 x 10 maze in (far fewer than) 1000 steps.
         * Feel free to adjust this number if you experiment with other mazes.
         */
        int step = 0;
        while (!maze.isFinished()) {
            maze.turnLeft();
            while (!maze.canMove()) {
                maze.turnRight();
            }
            maze.move();

            //Displaying maze
            System.out.println(maze + "\n");

            if (maze.isFinished()) {
                System.out.println(step);
                break;
            }
            Thread.sleep(50);
            step++;
        }

        if (maze.isFinished()) {
            System.out.println("You solved the maze!");
        } else {
            System.out.println("Try again!");
        }

        System.out.println("Average steps in " + 1000 + " runs:" + findAverageSteps(1000, 10, 10));
    }

    /**
     *
     * @param runs amount of runs to average
     * @param xDim x dimension
     * @param yDim y dimension
     * @return double average steps in maze runs
     */

    private static double findAverageSteps(final int runs, final int xDim, final int yDim) {
        long totalSteps = 0;
        for (int ind = 0; ind < runs; ind++) {
            totalSteps += runMaze(xDim, yDim);
        }
        return totalSteps / runs;
    }

    /**
     *
     * @param xDim x dimension
     * @param yDim y dimension
     * @return int amount of steps taken
     */
    private static int runMaze(final int xDim, final int yDim) {
        Maze maze = new Maze(xDim, yDim);

        /*
         * Pick (0, 0), the bottom left corner, as the starting point.
         * Put the end in the top right corner.
         */
        maze.startAtZero();
        maze.endAtTopRight();

        /*
         * You should be able to solve a 10 x 10 maze in (far fewer than) 1000 steps.
         * Feel free to adjust this number if you experiment with other mazes.
         */
        int step = 0;
        while (!maze.isFinished()) {
            maze.turnLeft();
            while (!maze.canMove()) {
                maze.turnRight();
            }
            maze.move();
            if (maze.isFinished()) {
                return step;
            }
            step++;
        }
        return step;
    }
}
