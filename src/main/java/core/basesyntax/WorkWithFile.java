package core.basesyntax;

public class WorkWithFile {
    public static void main(String[] args) {
        var data = new WorkWithFile();
        data.getStatistic("grape.csv", "report.csv");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String sourceData = new DataLoader().readData(fromFileName);
        String report = new DataProcessing().createReport(sourceData);
        new DataSaver().writeData(toFileName, report);
    }
}
