package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Cell {

    Specifications specs = Specifications.getInstance();
    private Optional<Integer> value;
    private Set<Integer> potentialValues;
    private int row;
    private int column;

    public Cell(int value, int row, int column) {
        this.value = Optional.of(value);
        this.row = row;
        this.column = column;
        this.potentialValues = new HashSet<>();
    }

    public Cell(int row, int column) {
        this.value = Optional.empty();
        this.row = row;
        this.column = column;
        this.potentialValues = new HashSet<>();
        for (int i = 1; i <= specs.SUDOKU_DIMENSION; ++i) {
            this.potentialValues.add(i);
        }
    }

    public Set<Integer> getPotentialValues() {
        return potentialValues;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Optional<Integer> getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = Optional.of(value);
        this.potentialValues.clear();
    }

    public boolean hasValue() {
        return value.isPresent();
    }

    public boolean removeImpossibleValue(int forbidden) {
        return potentialValues.remove(forbidden);
    }

    public boolean completeIfPossible(){
        if(hasValue()){
            return false;
        }
        if (potentialValues.size() == 1) {
            for(int lastValue: potentialValues) {
                this.value = Optional.of(lastValue);
                this.potentialValues.clear();
                return true;
            }
        }
        return false;
    }

    public boolean valueAllowed(int val) {
        return potentialValues.contains(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row &&
                column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
