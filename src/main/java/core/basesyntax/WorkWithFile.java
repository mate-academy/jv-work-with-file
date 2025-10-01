package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {

        String fileContent = readFromFile(fromFileName);
        String[] lines = fileContent.split("\n");
        int supplySum = 0;
        int buySum = 0;
        int result;
        for (String line : lines) {
            String[] splitedLine = line.split(",");
            int value = Integer.parseInt(splitedLine[1].trim());
            if (splitedLine[0].equals(SUPPLY_WORD)) {
                supplySum += value;
            }
            if (splitedLine[0].equals(BUY_WORD)) {
                buySum += value;
            }
        }
        result = getSupplyRemain(supplySum, buySum);
        String reportBuilder = SUPPLY_WORD + "," + supplySum + System.lineSeparator()
                + BUY_WORD + "," + buySum + System.lineSeparator()
                + "result" + "," + result + System.lineSeparator();
        writeToFile(toFileName, reportBuilder);
    }

    private int getSupplyRemain(int supply, int buy) {
        return supply - buy;
    }

    //takes a data as a String and writes it to a destination file
    private void writeToFile(String fromFileName, String data) {
        File file = new File(fromFileName);
        try {
            Files.write(file.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to this file", e);
        }
    }

    //reads all text in file and returns it as a single String
    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        final String fileContent;
        try {
            fileContent = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from this file", e);
        }
        return fileContent;
    }

    public static void main(String[] args) {
        WorkWithFile work = new WorkWithFile();
        work.getStatistic("C:\\projects\\jv-work-with-file\\apple.csv", "test.csv");
    }
}
