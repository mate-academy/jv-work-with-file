package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("read error!" + e);
        }
        return builder.toString();
    }

    private String createReport(String dataFromFile) {
        String[] infoReport = dataFromFile.split("\r?\n|\r");
        String[] infoCell = new String[2];
        int supply = 0;
        int buy = 0;
        for (String infoElement: infoReport) {
            infoCell = infoElement.split(",");
            if (infoCell[OPERATION_INDEX ].equals(SUPPLY_NAME)) {
                supply += Integer.parseInt(infoCell[AMOUNT_INDEX]);
            } else if (infoCell[OPERATION_INDEX ].equals(BUY_NAME)) {
                buy += Integer.parseInt(infoCell[AMOUNT_INDEX]);
            }
        }
        int result = supply - buy;
        StringBuilder finalBuilder = new StringBuilder();
        finalBuilder.append(SUPPLY_NAME + ","
                + supply + System.lineSeparator()
                + BUY_NAME + ","
                + buy + System.lineSeparator()
                + RESULT_NAME + ","
                + result + System.lineSeparator());
        return  finalBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("write error!" + e);
        }
    }
}
