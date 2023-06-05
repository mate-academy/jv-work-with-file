package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;
    public static final String SEPARATOR = ",";
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                result.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return result.toString();
    }

    private String createReport(String data) {
        int buy = 0;
        int supply = 0;
        StringBuilder report = new StringBuilder();
        if (data != null) {
            String[] arrLines = data.split(System.lineSeparator());
            for (String line:arrLines) {
                String[] temp = line.split(SEPARATOR);
                switch (temp[OPERATION_TYPE]) {
                    case BUY:
                        buy += Integer.parseInt(temp[AMOUNT]);
                        break;
                    case SUPPLY:
                        supply += Integer.parseInt(temp[AMOUNT]);
                        break;
                    default:
                        break;
                }
            }
            int result = supply - buy;
            report.append(SUPPLY).append(SEPARATOR).append(supply).append(System.lineSeparator())
                    .append(BUY).append(SEPARATOR).append(buy).append(System.lineSeparator())
                    .append(RESULT).append(SEPARATOR).append(result);
        }
        return report.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
