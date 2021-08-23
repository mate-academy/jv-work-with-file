package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String STRING_TO_COMPARE = "supply";
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFile = readFromFile(fromFileName);
        String report = createReport(readFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return builder.toString().split(" ");
    }

    private int[] calculateForReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String datum : data) {
            String[] dataArr = datum.split(",");
            int amount = Integer.parseInt(dataArr[AMOUNT_INDEX]);
            if (dataArr[WORD_INDEX].equals(STRING_TO_COMPARE)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        return new int[]{supply, buy};
    }

    private String createReport(String[] data) {
        int[] amounts = calculateForReport(data);
        return new StringBuilder()
                .append("supply,").append(amounts[0])
                .append(System.lineSeparator())
                .append("buy,").append(amounts[1])
                .append(System.lineSeparator())
                .append("result,").append(amounts[0] - amounts[1])
                .toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
