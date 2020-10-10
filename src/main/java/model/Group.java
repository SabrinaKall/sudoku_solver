package model;

import exception.DuplicateException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {


    List<Cell> cells;
    Set<Integer> values;
    boolean isBlock;
    Specifications specs = Specifications.getInstance();

    public Group(List<Cell> cells, boolean isBlock) throws DuplicateException {
        this.cells = cells;
        this.isBlock = isBlock;
        this.values = new HashSet<>();
        for (Cell cell : cells) {
            if (cell.getValue().isPresent()) {
                boolean added = this.values.add(cell.getValue().get());
                if (!added) {
                    throw new DuplicateException("Duplicate value " + cell.getValue().get() + "! Something is wrong!");
                }

            }
        }
    }

    public boolean valueAllowed(int value) {
        return !values.contains(value);
    }

    public void updateValue(int value) {
        for (int i = 0; i < specs.SUDOKU_DIMENSION; ++i) {
            cells.get(i).removeImpossibleValue(value);
        }
    }

    public boolean updateValueWithCaviat(int value, List<Integer> exceptions) {
        boolean changeMade = false;
        for (int i = 0; i < specs.SUDOKU_DIMENSION; ++i) {
            if(!exceptions.contains(i)) {
                changeMade = changeMade || cells.get(i).removeImpossibleValue(value);
            }
        }
        return changeMade;
    }

    private List<Cell> getPossibleCellsForValue(int value) {
        List<Cell> possibleCells = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.valueAllowed(value)) {
                possibleCells.add(cell);
            }
        }
        return possibleCells;
    }

    public List<Cell> checkForSingleCellPossible() {
        List<Cell> newVals = new ArrayList<>();
        for (int i = 1; i <= specs.SUDOKU_DIMENSION; ++i) {
            if (valueAllowed(i)) {
                int nbPossibleCells = 0;
                Cell onlyCell = null;
                for (Cell cell : cells) {
                    if (cell.valueAllowed(i)) {
                        nbPossibleCells += 1;
                        onlyCell = cell;
                    }
                }
                if (nbPossibleCells == 1) {
                    onlyCell.setValue(i);
                    newVals.add(onlyCell);
                }
            }
        }
        return newVals;
    }

    public List<Alignment> checkForAlignments() {
        List<Alignment> alignments = new ArrayList<>();
        if (isBlock) {
            for (int i = 1; i <= specs.SUDOKU_DIMENSION; ++i) {
                if (valueAllowed(i)) {
                    List<Cell> groupings = this.getPossibleCellsForValue(i);
                    if (groupings.size() == 2) {
                        Cell cell1 = groupings.get(0);
                        Cell cell2 = groupings.get(1);
                        boolean rowSame = (cell1.getRow() == cell2.getRow());
                        boolean colSame = (cell1.getColumn() == cell2.getColumn());
                        if(rowSame) {
                            Alignment alignment = new Alignment(i, cell1.getRow(), cell1.getColumn(), cell2.getColumn(), true);
                            alignments.add(alignment);
                        } else if (colSame) {
                            Alignment alignment = new Alignment(i, cell1.getColumn(), cell1.getRow(), cell2.getRow(), false);
                            alignments.add(alignment);
                        }

                    }
                }
            }
        }
        return alignments;
    }
}
