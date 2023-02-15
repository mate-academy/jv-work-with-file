package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String extractedData = readFromFile(fromFileName);
        writeToFile(toFileName, countResultAndGetReport(extractedData));
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + file.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file.getName(), e);
        }
        return builder.toString();
    }

    private String countResultAndGetReport(String extractedData) {
        String[] arrayOfLines = extractedData.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : arrayOfLines) {
            String[] dataParts = line.split(",");
            if (dataParts[FIRST_POSITION].equals(SUPPLY)) {
                supply += Integer.parseInt(dataParts[SECOND_POSITION]);
            }
            if (dataParts[FIRST_POSITION].equals(BUY)) {
                buy += Integer.parseInt(dataParts[SECOND_POSITION]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file" + file.getName(), e);
        }
    }
}
