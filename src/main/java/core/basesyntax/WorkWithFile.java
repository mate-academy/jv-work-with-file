package core.basesyntax;

public class WorkWithFile {
    public static void main(String[] args) {
        var data = new WorkWithFile();
        data.getStatistic("grape.csv", "report.csv");
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String sourceData = new LoadData().readData(fromFileName);
        String report = new ProcessData().createReport(sourceData);
        new SaveData().writeData(toFileName, report);
    }
}
