package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        write(toFileName, createReport(generateReport(read(fromFileName))));
    }

    private String[] read(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                if (value.startsWith("buy") || value.startsWith("supply")) {
                    stringBuilder.append(value).append(" ");
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String[] generateReport(String[] operations) {
        int buy = 0;
        int supply = 0;
        for (String array : operations) {
            String[] arrayOperations = array.split(COMMA);
            if (arrayOperations[OPERATION_TYPE].equals("buy")) {
                buy += Integer.parseInt(arrayOperations[AMOUNT]);
            } else if (arrayOperations[OPERATION_TYPE].equals("supply")) {
                supply += Integer.parseInt(arrayOperations[AMOUNT]);
            }
        }
        return new String[] {String.valueOf(buy), String.valueOf(supply)};
    }

    private String[] createReport(String[] data) {
        String[] report = new String[3];
        report[0] = "supply," + data[1] + System.lineSeparator();
        report[1] = "buy," + data[0] + System.lineSeparator();
        report[2] = "result," + (Integer.parseInt(data[1]) - Integer.parseInt(data[0]));
        return report;
    }

    private void write(String fileName, String[] report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String reports : report) {
                bufferedWriter.write(reports);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
