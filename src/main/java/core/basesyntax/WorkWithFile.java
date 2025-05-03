package core.basesyntax;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        ReadFile readFile = new ReadFile();
        String readiedFile = readFile.read(fromFileName);
        SupplierReport supplierReport = new SupplierReport();
        String readyToWrite = supplierReport.creatReport(supplierReport.countData(readiedFile));
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.write(readyToWrite, toFileName);
    }
}
