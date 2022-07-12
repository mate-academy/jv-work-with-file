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
    private static final int OPERATION_TYPE = 0;
    private static final int AMMOUNT = 1;
    private static int supplyTotal = 0;
    private static int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    public void readFile(String fromFileName) {
        supplyTotal = 0;
        buyTotal = 0;
        File fileSource = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSource))) {
            String lineFromFile = reader.readLine();
            String[] splittedLine;
            while (lineFromFile != null) {
                splittedLine = lineFromFile.split(",");
                calculateTotalValues(splittedLine);
                lineFromFile = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("File " + fromFileName + " not found.", ex);
        }
    }

    private void calculateTotalValues(String[] splittedLine) {
        switch (splittedLine[OPERATION_TYPE]) {
            case (SUPPLY_EVENT_NAME):
                supplyTotal += Integer.valueOf(splittedLine[AMMOUNT]);
                break;
            case (BUY_EVENT_NAME):
                buyTotal += Integer.valueOf(splittedLine[AMMOUNT]);
                break;
            default:
                break;
        }
    }

    private void writeFile(String toFileName) {
        File fileTarget = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTarget, true))) {
            writer.write(createReport(supplyTotal, buyTotal));
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
