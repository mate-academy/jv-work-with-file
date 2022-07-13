package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_EVENT_NAME = "buy";
    private static final String SUPPLY_EVENT_NAME = "supply";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supplyBuyTotal = new int[2];
        supplyBuyTotal = readFile(fromFileName);
        writeFile(toFileName,supplyBuyTotal);
    }

    public int[] readFile(String fromFileName) {
        File fileSource = new File(fromFileName);
        int[] supplyBuyTotal = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSource))) {
            String lineFromFile = reader.readLine();
            String[] splittedLine;
            while (lineFromFile != null) {
                splittedLine = lineFromFile.split(",");
                int[] temporaryArray = new int[2];
                temporaryArray = calculateTotalValues(splittedLine);
                supplyBuyTotal[SUPPLY_INDEX] += temporaryArray[SUPPLY_INDEX];
                supplyBuyTotal[BUY_INDEX] += temporaryArray[BUY_INDEX];
                lineFromFile = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("File " + fromFileName + " not found.", ex);
        }
        return supplyBuyTotal;
    }

    private int[] calculateTotalValues(String[] splittedLine) {
        int[] supplyBuyTotal = new int [] {0,0};
        switch (splittedLine[OPERATION_TYPE_INDEX]) {
            case (SUPPLY_EVENT_NAME):
                supplyBuyTotal[SUPPLY_INDEX] += Integer.valueOf(splittedLine[AMMOUNT_INDEX]);
                break;
            case (BUY_EVENT_NAME):
                supplyBuyTotal[BUY_INDEX] += Integer.valueOf(splittedLine[AMMOUNT_INDEX]);
                break;
            default:
                throw new RuntimeException("Unknown event, please check data source file.");
        }
        return supplyBuyTotal;
    }

    private void writeFile(String toFileName, int[] supplyBuyTotal) {
        File fileTarget = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTarget, true))) {
            writer.write(createReport(supplyBuyTotal[SUPPLY_INDEX], supplyBuyTotal[BUY_INDEX]));
        } catch (Exception e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }

    private String createReport(int supplyTotal, int buyTotal) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_EVENT_NAME)
                .append(",")
                .append(supplyTotal)
                .append(System.lineSeparator());
        stringBuilder.append(BUY_EVENT_NAME)
                .append(",")
                .append(buyTotal)
                .append(System.lineSeparator());
        stringBuilder.append("result,")
                .append(supplyTotal - buyTotal);
        return stringBuilder.toString();
    }
}
