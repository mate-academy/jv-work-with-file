package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] array = readFromFile(fromFileName);
        String report = generateReport(array);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(" ");
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be read", e);
        }
    }

    private String generateReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String line: data) {
            int amount = Integer.parseInt(line.substring(line.indexOf(',') + 1));
            if (line.contains(SUPPLY)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supply).append(System.lineSeparator()).append(BUY)
                .append(",").append(buy).append(System.lineSeparator()).append(RESULT).append(",")
                .append(result);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find a file", e);
        } catch (IOException e) {
            throw new RuntimeException("Line can`t be written", e);
        }
    }
}
