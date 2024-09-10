package core.basesyntax;

class Result {
    private String[] variables;
    private int[] numbers;

    public Result(String[] variables, int[] numbers) {
        this.variables = variables;
        this.numbers = numbers;
    }

    public String[] getVariables() {
        return variables;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
