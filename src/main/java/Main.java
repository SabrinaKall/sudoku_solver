import exception.DuplicateException;
import exception.ValueNotAllowedException;
import model.Grid;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String instructions = "Available commands: NEW GRID / EXAMPLE GRID / SOLVE / STOP / SHOW / POTENTIAL VALUES";

    private static Grid grid;
    private static boolean gridSet = false;

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
                        grid = ExampleGrids.setExampleGrid(difficultyLevel);
                        gridSet = true;
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
