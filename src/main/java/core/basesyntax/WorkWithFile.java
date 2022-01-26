package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SEPARATOR = ",";
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        writeToFile(toFileName, createReport(countData(fileData)));
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = null;
            value = reader.readLine();
            while (value != null) {
                builder.append(value).append("_");
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("can`t read file...", e);
        }
        return builder.toString();
    }

    private int[] countData(String fileData) {
        int supply = 0;
        int buy = 0;
        String[] res = fileData.split("_");
        for (String a: res) {
            String[] temporary = a.split(",");
            if (temporary[0].equals(SUPPLY)) {
                supply += Integer.valueOf(temporary[1]);
            } else if (temporary[0].equals(BUY)) {
                buy += Integer.valueOf(temporary[1]);
            }
        }
        int[] data = new int[]{supply, buy};
        return data;
    }

    private String createReport(int[] data) {
        int supply = data[0];
        int buy = data[1];
        StringBuilder builder = new StringBuilder();
        result += supply - buy;
        builder.append(SUPPLY).append(COMMA_SEPARATOR).append(supply)
                .append(System.lineSeparator()).append(BUY).append(COMMA_SEPARATOR)
                .append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA_SEPARATOR)
                .append(result).append(System.lineSeparator());
        String report = builder.toString();
        return report;
    }

    private void writeToFile(String fileName, String report) {

        File fileOne = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOne, true));) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file...", e);
        }
    }
}

