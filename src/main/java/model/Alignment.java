package model;

public class Alignment {

    private int barredValue;
    private int index;
    private int option1;
    private int option2;
    private boolean isRow;

    public Alignment(int barredValue, int index, int option1, int option2, boolean isRow) {
        this.barredValue = barredValue;
        this.index = index;
        this.option1 = option1;
        this.option2 = option2;
        this.isRow = isRow;
    }

    public int getBarredValue() {
        return barredValue;
    }

    public void setBarredValue(int barredValue) {
        this.barredValue = barredValue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isRow() {
        return isRow;
    }

    public void setRow(boolean row) {
        isRow = row;
    }

    public int getOption1() {
        return option1;
    }

    public void setOption1(int option1) {
        this.option1 = option1;
    }

    public int getOption2() {
        return option2;
    }

    public void setOption2(int option2) {
        this.option2 = option2;
    }
}
