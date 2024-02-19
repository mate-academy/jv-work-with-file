package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int BUY_OR_SUPPLY_INDEX = 1;
    private static final int BUY_ARRAY_INDEX = 0;
    private static final int SUPPLY_ARRAY_INDEX = 1;
    private static final int BUY_AND_SUPPLY_ARRAY_SIZE = 2;
    private static final String BUY = "buy,";
    private static final String SUPPLY = "supply,";
    private static final String RESULT = "result,";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private int[] readFile(String fromFileName) {
        int[] buyAndSupplyArray = new int[BUY_AND_SUPPLY_ARRAY_SIZE];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineOfFileName = bufferedReader.readLine();
            while (lineOfFileName != null) {
                if (lineOfFileName.contains(BUY)) {
                    buyAndSupplyArray[BUY_ARRAY_INDEX] += parseBuyOrSupply(lineOfFileName);
                } else {
                    buyAndSupplyArray[SUPPLY_ARRAY_INDEX] += parseBuyOrSupply(lineOfFileName);
                }
                lineOfFileName = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return buyAndSupplyArray;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writeToFileName = new BufferedWriter(new FileWriter(toFileName))) {
            writeToFileName.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private int parseBuyOrSupply(String lineOfFileName) {
        return Integer.parseInt(lineOfFileName.split(SEPARATOR)[BUY_OR_SUPPLY_INDEX]);
    }

    private String createReport(int[] buyAndSupplyArray) {
        int bye = buyAndSupplyArray[BUY_ARRAY_INDEX];
        int supply = buyAndSupplyArray[SUPPLY_ARRAY_INDEX];
        return SUPPLY + supply + System.lineSeparator()
                + BUY + bye + System.lineSeparator() + RESULT + (supply - bye);
    }
}
