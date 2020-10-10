import exception.DuplicateException;
import exception.ValueNotAllowedException;
import model.Grid;
import model.Specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SudokuInput {

    private static final SudokuInput sudokuInput = new SudokuInput();

    private final Specifications specs = Specifications.getInstance();
    private final Scanner scanner;

    private SudokuInput() {
        scanner = new Scanner(System.in).useDelimiter("\\s");
    }

    public static SudokuInput getInstance() {
        return sudokuInput;
    }


    public Grid readSudokuLineByLine() throws DuplicateException, ValueNotAllowedException {
        System.out.println("Input your sudoku values line by line (for empty values, use \"0\"; split numbers with commas):");
        List<List<Integer>> grid =  new ArrayList<>(specs.SUDOKU_DIMENSION);
        for(int col = 0; col < specs.SUDOKU_DIMENSION; ++col) {
            List<Integer> line = new ArrayList<>(specs.SUDOKU_DIMENSION);
            System.out.print("Line " + (col + 1) + ": ");
            String[] lineVals = scanner.nextLine().split("(,|\\s)");
            if(lineVals.length != specs.SUDOKU_DIMENSION) {
                System.out.println("Wrong number of values: expected " + specs.SUDOKU_DIMENSION +" but got " + lineVals.length + ". Try again");
                col--;
            } else {
                for (int row = 0; row < specs.SUDOKU_DIMENSION; ++row) {
                    int nextVal = Integer.parseInt(lineVals[row]);
                    if (nextVal < 0 || specs.SUDOKU_DIMENSION < nextVal) {
                        System.out.println("Value must be between 0 and " + specs.SUDOKU_DIMENSION + " - please try again");
                    } else {
                        line.add(nextVal);
                    }
                }
                grid.add(line);
            }
        }
        return new Grid(grid);

    }

    public void endRead() {
        scanner.close();
    }
}
