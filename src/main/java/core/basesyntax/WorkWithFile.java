package core.basesyntax;

public class WorkWithFile {
    private static FileDataReader readData = new FileDataReader();
    private static ReportCalculate dataToCalculate = new ReportCalculate();
    private static FileDataWritter writeData = new FileDataWritter();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readData.readFrom(fromFileName);
        String dataToWrite = dataToCalculate.calculateReportFrom(dataFromFile);
        writeData.writeTo(toFileName, dataToWrite);
    }
}
