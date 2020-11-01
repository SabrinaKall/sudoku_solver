public enum Command {
    NEW("Input the starting values of a new sudoku grid."),
    EXAMPLE("Use an example grid to test the tool. You can choose the difficulty level of the grid: easy, medium, hard."),
    SOLVE("Solve the given grid. Prerequisite: setting an example grid, or your own, using NEW or EXAMPLE."),
    SHOW("Show your grid in its current state. Prerequisite: setting an example grid, or your own, using NEW or EXAMPLE."),
    POTENTIAL("Show the potential values that can be put in a cell. Requires row and column of cell."),
    STOP("Shut down the tool.");

    private final String instructions;

    Command(String s) {
        this.instructions = s;
    }

    public String getInstructions() {
        return instructions;
    }
}
