package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        workWithFile.getStatistic("path_to_input_file.csv", "path_to_output_file.csv");
    }
}
