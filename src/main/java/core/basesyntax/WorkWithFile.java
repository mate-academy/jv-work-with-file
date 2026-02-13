package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int OPERATION_TYPE_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;
    public static final String OPERATION_SUPPLY = "supply";
    public static final String OPERATION_BUY = "buy";
    public static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFromFile = readDataFromFile(fromFileName);
        int[] stats = aggregateOperations(readFromFile);
        writeDataToFile(toFileName, createReport(stats));
    }

    private int[] aggregateOperations(String[] readFromFile) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < readFromFile.length; i++) {
            String[] splitedString = readFromFile[i].split(",");
            if (splitedString[OPERATION_TYPE_POSITION].equals(OPERATION_SUPPLY)) {
                supply += parsedStringData(splitedString[AMOUNT_POSITION]);
            }
            if (splitedString[OPERATION_TYPE_POSITION].equals(OPERATION_BUY)) {
                buy += parsedStringData(splitedString[AMOUNT_POSITION]);
            }
        }
        return new int[]{supply, buy};
    }

    private String[] createReport(int[] stats) {

        StringBuilder stringBuilder = new StringBuilder();
        int supply = stats[0];
        int buy = stats[1];
        int result = supply - buy;

        stringBuilder.append(OPERATION_SUPPLY).append(",").append(supply).append(" ")
                .append(System.lineSeparator())
                .append(OPERATION_BUY).append(",").append(buy).append(" ")
                .append(System.lineSeparator())
                .append(OPERATION_RESULT).append(",").append(result)
                .append(System.lineSeparator());

        return stringBuilder.toString().split(" ");
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                stringBuilder.append(readLine).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        if (!stringBuilder.toString().isEmpty()) {
            return stringBuilder.toString().split(" ");
        }
        return new String[]{};
    }

    private void writeDataToFile(String toFileName, String[] dataForWriting) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String data : dataForWriting) {
                if (!data.isEmpty()) {
                    bufferedWriter.write(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private int parsedStringData(String data) {
        return Integer.parseInt(data);
    }
}
