package core.basesyntax;

public class WorkWithFile {
    private static ReadData readData = new ReadData();
    private static Calculate dataToCalculate = new Calculate();
    private static WriteData writeData = new WriteData();

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readData.getDataFromFile(fromFileName);
        String dataToWrite = dataToCalculate.calculateBuySupplyData(dataFromFile);
        writeData.writer(toFileName,dataToWrite);
    }

    public static void main(String[] args) {
        WorkWithFile test = new WorkWithFile();
        test.getStatistic("apple.csv","raport.txt");
    }
}
