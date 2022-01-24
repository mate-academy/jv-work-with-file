package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TITLE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLIER = "supply";
    private static final String BUYER = "buy";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFile(fromFileName);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String fileToWrite, String dataToBeWritten) {
        File resultedInfo = new File(fileToWrite);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultedInfo))) {
            writer.write(dataToBeWritten);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFile(String incomeFile) {
        int buySum = 0;
        int supplySum = 0;
        File incomeInfo = new File(incomeFile);
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeInfo))) {
            line = reader.readLine();
            while (line != null) {
                if (line.split(COMA)[TITLE_INDEX].equals(SUPPLIER)) {
                    supplySum += Integer.parseInt(line.split(COMA)[AMOUNT_INDEX]);
                }
                if (line.split(COMA)[TITLE_INDEX].equals(BUYER)) {
                    buySum += Integer.parseInt(line.split(COMA)[AMOUNT_INDEX]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        StringBuilder builder = new StringBuilder()
                .append(SUPPLIER).append(COMA).append(supplySum).append(System.lineSeparator())
                .append(BUYER).append(COMA).append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum);
        return builder.toString();
    }
}
