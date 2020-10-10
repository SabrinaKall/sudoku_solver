package model;

import exception.DuplicateException;
import exception.ValueNotAllowedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Grid {

    Specifications specs = Specifications.getInstance();
    private List<List<Cell>> cells;
    private List<Group> rows;
    private List<Group> columns;
    private List<Group> blocks;

    private int counter;

    public Grid(List<List<Integer>> inputRows) throws DuplicateException, ValueNotAllowedException {

        this();
        for (int row = 0; row < specs.SUDOKU_DIMENSION; ++row) {

            for (int col = 0; col < specs.SUDOKU_DIMENSION; ++col) {
                int input = inputRows.get(row).get(col);
                if (0 < input && input <= specs.SUDOKU_DIMENSION) {
                    this.fillValueIn(input, row, col);
                    counter++;
                }
            }
        }
    }

    public Grid() throws DuplicateException {
        counter = 0;
        this.cells = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.blocks = new ArrayList<>();

        List<List<Cell>> cols = new ArrayList<>();
        List<List<Cell>> blcks = new ArrayList<>();

        for (int i = 0; i < specs.SUDOKU_DIMENSION; ++i) {
            cols.add(new ArrayList<>());
            blcks.add(new ArrayList<>());

        }

        for (int row = 0; row < specs.SUDOKU_DIMENSION; ++row) {
            List<Cell> rowCells = new ArrayList<>();
            for (int col = 0; col < specs.SUDOKU_DIMENSION; ++col) {
                Cell cell = new Cell(row, col);
                rowCells.add(cell);
                cols.get(col).add(cell);
                int blockIndex = this.selectBlockIndex(row, col);
                blcks.get(blockIndex).add(cell);
            }
            cells.add(rowCells);
            Group rowGroup = new Group(rowCells, false);
            this.rows.add(rowGroup);
        }

        for (int i = 0; i < specs.SUDOKU_DIMENSION; ++i) {
            this.columns.add(new Group(cols.get(i), false));
            this.blocks.add(new Group(blcks.get(i), true));

        }

    }

    public Set<Integer> getPotentialValues(int row, int col) {
        return cells.get(row).get(col).getPotentialValues();
    }

    public void fillValueIn(int value, int rowIndex, int colIndex) throws ValueNotAllowedException {
        Group row = rows.get(rowIndex);
        Group col = columns.get(colIndex);
        Group block = blocks.get(selectBlockIndex(rowIndex, colIndex));
        
        if (!row.valueAllowed(value)) {
            throw new ValueNotAllowedException("Row "+ rowIndex +" already has value " + value + " (allowed values: "+ row.values.toString()+")");
        }
        if (!col.valueAllowed(value)) {
            throw new ValueNotAllowedException("Col already has value " + value);
        }
        if (!block.valueAllowed(value)) {
            throw new ValueNotAllowedException("Block already has value " + value);
        }

        cells.get(rowIndex).get(colIndex).setValue(value);

        row.updateValue(value);
        col.updateValue(value);
        block.updateValue(value);

    }


    public boolean solvePuzzle() throws ValueNotAllowedException {
        while (!isSolved()) {
            boolean foundOne = false;
            for (int i = 0; i < specs.SUDOKU_DIMENSION; ++i) {
                for (int j = 0; j < specs.SUDOKU_DIMENSION; ++j) {
                    boolean found = checkCell(i, j);
                    foundOne = foundOne || found;
                }
            }
            for(Group row: rows) {
                foundOne = foundOne || checkGroup(row);
            }
            for(Group col: columns) {
                foundOne = foundOne || checkGroup(col);
            }
            for(Group block: blocks) {
                foundOne = foundOne || checkGroup(block);
            }

            foundOne = foundOne || checkIntersections();

            if (!foundOne) {
                System.out.println("Went through whole grid and cannot solve puzzle with given information");
                return false;
            }
        }

        return true;
    }

    private boolean checkCell(int row, int col) throws ValueNotAllowedException {
        Cell cell = cells.get(row).get(col);
        if (cell.hasValue()) {
            return false;
        }
        boolean newValAdded = cell.completeIfPossible();
        if (newValAdded) {
            int newVal = cell.getValue().get();
            fillValueIn(newVal, row, col);
            counter++;
            return true;
        }
        return false;
    }

    private boolean checkGroup(Group group) throws ValueNotAllowedException {
        boolean updated = false;
        List<Cell> foundVals = group.checkForSingleCellPossible();
        for(Cell cell: foundVals) {
            fillValueIn(cell.getValue().get(), cell.getRow(), cell.getColumn());
            counter++;
            updated = true;
        }
        return updated;
    }

    private boolean checkIntersections() {
        boolean updated = false;
        for(Group block: blocks) {
            List<Alignment> alignments = block.checkForAlignments();
            for(Alignment alignment: alignments) {
                if(alignment.isRow()) {
                    Group row = rows.get(alignment.getIndex());
                    List<Integer> exceptions = Arrays.asList(alignment.getOption1(), alignment.getOption2());
                    updated = updated || row.updateValueWithCaviat(alignment.getBarredValue(), exceptions);
                }

            }

        }

        return updated;
    }

    private boolean isSolved() {
        return counter == specs.TOTAL_NB_CELLS;
    }

    private int selectBlockIndex(int row, int col) {
        int blockRow = row / specs.BLOCK_DIM;
        int blockCol = col / specs.BLOCK_DIM;
        return (blockRow * specs.BLOCK_DIM) + blockCol;
    }

    public void print() {
        GridPrinter printer = new GridPrinter();
        printer.print(this);
    }

    private static class GridPrinter {

        public void print(Grid grid) {
            for(List<Cell> row: grid.cells) {
                printLine();
                printSep();
                for(Cell cell: row) {
                    if(cell.hasValue()) {
                        System.out.print(" " + cell.getValue().get() + " ");
                    } else {
                        System.out.print("   ");
                    }
                    printSep();
                }
                System.out.print("\n");
            }
            printLine();
        }

        private void printLine() {
            for(int i = 0; i < 9; ++i) {
                System.out.print(" _ _");
            }
            System.out.print("\n");
        }

        private void printSep() {
            System.out.print("|");
        }
    }


}
