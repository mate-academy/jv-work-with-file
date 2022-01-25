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
    private static final int ZERO = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(calculateStock(readFile(fromFileName))));
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
        StringBuilder builder;
        File incomeInfo = new File(incomeFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(incomeInfo))) {
            String temporaryString = reader.readLine();
            while (temporaryString != null) {
                builder = new StringBuilder()
                        .append(temporaryString)
                        .append(System.lineSeparator());
                temporaryString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return builder.toString();
    }

    private int[] calculateStock(String data) {
        int buySum = ZERO;
        int supplySum = ZERO;
        String[] lineArray = data.split(System.lineSeparator());
        String[] wordArray;
        for (int i = 0; i < lineArray.length; i++) {
            wordArray = lineArray[i].split(",");
            if (wordArray[TITLE_INDEX].equals(SUPPLIER)) {
                supplySum += Integer.parseInt(wordArray[AMOUNT_INDEX]);
            }
            if (wordArray[TITLE_INDEX].equals(BUYER)) {
                buySum += Integer.parseInt(wordArray[AMOUNT_INDEX]);
            }
        }
        int[] amounts = new int[]{supplySum, buySum, supplySum - buySum};
        return amounts;
    }

    private String createReport(int[] array) {
        StringBuilder reportBuilder = new StringBuilder()
                .append(SUPPLIER).append(COMA).append(array[0]).append(System.lineSeparator())
                .append(BUYER).append(COMA).append(array[1]).append(System.lineSeparator())
                .append("result,").append(array[2]);
        return reportBuilder.toString();
    }
}
