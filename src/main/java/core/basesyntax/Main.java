package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "input.csv";
        String outputFileName = "output.csv";

        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(inputFileName, outputFileName);

        System.out.println("Report generated successfully in " + outputFileName);
    }
}
