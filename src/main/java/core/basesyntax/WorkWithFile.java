package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayStringOfFile = readFromFile(fromFileName);
        String result = getResult(arrayStringOfFile);
        writeToFile(toFileName, result);
    }

    private String getResult(String[] arrayStringOfFile) {
        int supply = 0;
        int buy = 0;
        StringBuilder resultOfTable = new StringBuilder();

        for (int i = 0; i < arrayStringOfFile.length; i++) {
            if (arrayStringOfFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(arrayStringOfFile[i + 1]);
            } else if (arrayStringOfFile[i].equals(BUY)) {
                buy += Integer.parseInt(arrayStringOfFile[i + 1]);
            }
        }

        resultOfTable
                .append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return resultOfTable.toString();
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileInStringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                fileInStringBuilder.append(" ").append(value);
                value = bufferedReader.readLine();
            }

            return fileInStringBuilder.toString().split(",|\\R|\\W+");
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file:", e);
        }
    }

    private void writeToFile(String fileNameToWrite, String stringToWriteIntoFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameToWrite))) {
            bufferedWriter.write(stringToWriteIntoFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: ", e);
        }
    }
}
