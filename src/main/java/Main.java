import exception.DuplicateException;
import exception.ValueNotAllowedException;
import model.Grid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String instructions = "Available commands: NEW GRID / EXAMPLE GRID / SOLVE / STOP / SHOW / POTENTIAL VALUES";

    static List<List<Integer>> sudoku_easy = Arrays.asList(
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

    static List<List<Integer>> sudoku_medium = Arrays.asList(
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

    static List<List<Integer>> sudoku_hard = Arrays.asList(
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

    static List<List<Integer>> sudoku_empty = Arrays.asList(
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

    static List<List<Integer>> sudoku_easy_1 = Arrays.asList(
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

    static List<List<Integer>> sudoku_medium_1 = Arrays.asList(
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

    static List<List<Integer>> sudoku_hard_1 = Arrays.asList(
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

    private static Grid grid;
    private static boolean gridSet = false;

    private void setGrid(List<List<Integer>> gridValues) {
        try {
            grid = new Grid(gridValues);
            gridSet = true;
        } catch (ValueNotAllowedException | DuplicateException e) {
            System.out.println("Could not create grid");
            System.out.println(e.getMessage());
            gridSet = false;
        }
    }

    private static void setExampleGrid(String difficultyLevel) throws Exception, ValueNotAllowedException {
        switch (difficultyLevel.trim()) {
            case "easy":
                grid = new Grid(sudoku_easy);
                gridSet = true;
                break;
            case "medium":
                grid = new Grid(sudoku_medium);
                gridSet = true;
                break;
            case "hard":
                grid = new Grid(sudoku_hard);
                gridSet = true;
                break;
            default:
                throw new Exception("Error: " + difficultyLevel.trim() + " is not a difficulty level.");
        }
    }

    public static void main(String[]args) {

        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        boolean keepRunning = true;
        System.out.println("Welcome to the java sudoku solver");
        System.out.println(instructions);
        while(keepRunning) {
            System.out.print("Next action: ");
            String input = scanner.nextLine();
            switch (input) {
                case "NEW GRID":
                    SudokuInput sudokuInput = SudokuInput.getInstance();
                    try {
                        grid = sudokuInput.readSudokuLineByLine();
                        gridSet = true;
                    } catch (DuplicateException | ValueNotAllowedException e) {
                        System.out.println("Could not solve grid");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "EXAMPLE GRID":
                    System.out.print("Difficulty level: [easy|medium|hard]:");
                    String difficultyLevel = scanner.nextLine();
                    try {
                        setExampleGrid(difficultyLevel);
                    } catch (Exception | ValueNotAllowedException e) {
                        System.out.println("Could not set example grid");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "STOP":
                    keepRunning = false;
                    System.out.print("Good-bye");
                    break;
                case "SOLVE":
                    if(!gridSet) {
                        System.out.println("Set grid first");
                        break;
                    }
                    solveGrid(grid);
                    break;
                case "SHOW":
                    if(!gridSet) {
                        System.out.println("Set grid first");
                        break;
                    }
                    grid.print();
                    break;
                case "POTENTIAL":
                    if(!gridSet) {
                        System.out.println("Set grid first");
                        break;
                    }
                    System.out.print("row: ");
                    int row = Integer.parseInt(scanner.next());
                    System.out.println(row);
                    System.out.print("col: ");
                    int col = Integer.parseInt(scanner.next());
                    System.out.println(grid.getPotentialValues(row, col));
                    break;
                default:
                    System.out.println("Unknown command: " + input);
                    System.out.println(instructions);
                    break;
            }
        }

        scanner.close();
    }

    private static void solveGrid(Grid grid) {
        try {
            boolean solved = grid.solvePuzzle();
            System.out.println("Sudoku solved: " + solved);
            grid.print();
        } catch (ValueNotAllowedException e) {
            System.out.println("Could not solve grid");
            System.out.println(e.getMessage());
            grid.print();
        }
    }
}
