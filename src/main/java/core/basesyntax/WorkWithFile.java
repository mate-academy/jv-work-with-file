package core.basesyntax;

public class WorkWithFile {
    private final ReadFile readFile = new ReadFile();
    private final SupplierReport supplierReport = new SupplierReport();
    private final WriteToFile writeToFile = new WriteToFile();

    public void getStatistic(String fromFileName, String toFileName) {
        String readiedFile = readFile.read(fromFileName);
        String readyToWrite = supplierReport.creatReport(readiedFile);
        writeToFile.write(readyToWrite, toFileName);
    }
}
