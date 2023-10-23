package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "apple.csv";
        String outputFileName = "appleResult.csv";

        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(inputFileName, outputFileName);
    }
}

