package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_KEY_WORD = "supply";
    private static final String BUY_KEY_WORD = "buy";
    private static final String RESULT_KEY_WORD = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder readLines = new StringBuilder();
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                readLines.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read " + fromFileName + " file.",e);
        }
        return readLines.toString();

    }

    private String createReport(String lines) {
        StringBuilder report = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        String[] lineInArray = lines.split(System.lineSeparator());
        for (String s : lineInArray) {
            String[] split = s.split(SEPARATOR);
            switch (split[OPERATION_INDEX]) {
                case SUPPLY_KEY_WORD: {
                    supplySum += Integer.parseInt(split[AMOUNT_INDEX]);
                    break;
                }
                case BUY_KEY_WORD: {
                    buySum += Integer.parseInt(split[AMOUNT_INDEX]);
                    break;
                }
                default: {
                    throw new RuntimeException("Can`t read data");
                }
            }
        }

        return report.append(SUPPLY_KEY_WORD).append(SEPARATOR)
                    .append(supplySum).append(System.lineSeparator())
                    .append(BUY_KEY_WORD).append(SEPARATOR)
                    .append(buySum).append(System.lineSeparator())
                    .append(RESULT_KEY_WORD).append(SEPARATOR)
                    .append(supplySum - buySum).toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write" + toFileName + " file.", e);
        }
    }
}
