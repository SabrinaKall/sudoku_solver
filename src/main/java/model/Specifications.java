package model;

public final class Specifications {

    private static final Specifications specs = new Specifications();

    private Specifications() { }

    public static Specifications getInstance( ) {
        return specs;
    }

    public final int BLOCK_DIM  = 3;
    public final int SUDOKU_DIMENSION = BLOCK_DIM * BLOCK_DIM;
    public final int TOTAL_NB_CELLS = SUDOKU_DIMENSION * SUDOKU_DIMENSION;


}
