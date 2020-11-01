import exception.ValueNotAllowedException;
import model.Grid;

import java.util.Arrays;
import java.util.List;

public final class ExampleGrids {

    private ExampleGrids() {}

    private static final List<List<Integer>> SUDOKU_EASY_1 = Arrays.asList(
            Arrays.asList(5,0,0,0,0,0,0,0,3),
            Arrays.asList(0,0,2,7,5,6,4,0,0),
            Arrays.asList(0,8,7,0,0,0,5,2,0),
            Arrays.asList(0,2,0,0,4,0,0,3,0),
            Arrays.asList(0,1,0,8,7,2,0,4,0),
            Arrays.asList(0,4,0,0,1,0,0,8,0),
            Arrays.asList(0,3,9,0,0,0,1,7,0),
            Arrays.asList(0,0,6,9,8,1,3,0,0),
            Arrays.asList(1,0,0,0,0,0,0,0,9)
    );

    private static final List<List<Integer>> SUDOKU_MEDIUM_1 = Arrays.asList(
            Arrays.asList(0,3,0,8,0,0,0,0,7),
            Arrays.asList(5,8,0,0,0,0,0,0,0),
            Arrays.asList(0,0,6,7,0,0,9,0,0),
            Arrays.asList(0,7,0,0,3,9,6,0,0),
            Arrays.asList(0,0,0,6,0,8,0,0,0),
            Arrays.asList(0,0,2,5,4,0,0,7,0),
            Arrays.asList(0,0,5,0,0,2,1,0,0),
            Arrays.asList(0,0,0,0,0,0,0,3,4),
            Arrays.asList(1,0,0,0,0,4,0,2,0)
    );

    private static final List<List<Integer>> SUDOKU_HARD_1 = Arrays.asList(
            Arrays.asList(5,0,0,0,2,0,0,3,0),
            Arrays.asList(4,0,7,6,0,0,0,2,0),
            Arrays.asList(0,0,8,0,0,3,0,5,0),
            Arrays.asList(3,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,2,5,8,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,9),
            Arrays.asList(0,8,0,9,0,0,5,0,0),
            Arrays.asList(0,9,0,0,0,4,8,0,1),
            Arrays.asList(0,7,0,0,8,0,0,0,6)
    );

    private static final List<List<Integer>> sudoku_empty = Arrays.asList(
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0),
            Arrays.asList(0,0,0,0,0,0,0,0,0)
    );

    public static final List<List<Integer>> SUDOKU_EASY_2 = Arrays.asList(
            Arrays.asList(1,0,2,8,0,0,0,4,7),
            Arrays.asList(4,0,7,5,0,0,0,0,0),
            Arrays.asList(0,0,0,9,0,0,0,1,3),
            Arrays.asList(0,0,0,0,3,0,8,2,6),
            Arrays.asList(0,0,0,7,0,8,0,0,0),
            Arrays.asList(3,8,4,0,9,0,0,0,0),
            Arrays.asList(7,1,0,0,0,5,0,0,0),
            Arrays.asList(0,0,0,0,0,2,7,0,9),
            Arrays.asList(2,4,0,0,0,9,3,0,1)
    );

    private static final List<List<Integer>> SUDOKU_MEDIUM_2 = Arrays.asList(
            Arrays.asList(0,1,0,3,0,0,0,0,0),
            Arrays.asList(0,0,5,0,0,2,0,0,0),
            Arrays.asList(0,0,4,6,5,0,0,0,2),
            Arrays.asList(5,0,8,0,0,4,0,2,0),
            Arrays.asList(9,0,0,0,0,0,0,0,6),
            Arrays.asList(0,3,0,2,0,0,9,0,5),
            Arrays.asList(7,0,0,0,9,5,1,0,0),
            Arrays.asList(0,0,0,1,0,0,6,0,0),
            Arrays.asList(0,0,0,0,0,3,0,9,0)
    );

    private static final List<List<Integer>> SUDOKU_HARD_2 = Arrays.asList(
            Arrays.asList(0,0,6,0,0,0,0,1,0),
            Arrays.asList(0,0,1,5,0,8,0,0,0),
            Arrays.asList(3,7,0,0,0,0,9,0,0),
            Arrays.asList(0,9,0,0,4,0,6,2,0),
            Arrays.asList(0,0,0,0,8,0,0,0,0),
            Arrays.asList(0,1,7,0,3,0,0,9,0),
            Arrays.asList(0,0,3,0,0,0,0,6,8),
            Arrays.asList(0,0,0,6,0,7,5,0,0),
            Arrays.asList(0,6,0,0,0,0,1,0,0)
    );

    public static Grid setExampleGrid(String difficultyLevel) throws Exception, ValueNotAllowedException {
        switch (difficultyLevel.trim()) {
            case "easy":
                return new Grid(ExampleGrids.SUDOKU_EASY_1);
            case "medium":
                return new Grid(ExampleGrids.SUDOKU_MEDIUM_1);
            case "hard":
                return new Grid(ExampleGrids.SUDOKU_HARD_1);
            default:
                throw new Exception("Error: " + difficultyLevel.trim() + " is not a difficulty level.");
        }
    }

}
