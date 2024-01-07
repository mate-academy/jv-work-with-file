package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static String FROM_FILE_NAME;
    private static String TO_FILE_NAME;

    public void getStatistic(String fromFileName, String toFileName) {
        FROM_FILE_NAME = fromFileName;
        TO_FILE_NAME = toFileName;

        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FROM_FILE_NAME))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + FROM_FILE_NAME, e);
        }
    }

    private String processData(String fileData) {
        String[] data = fileData.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;

        for (String row : data) {
            String[] info = row.split(SEPARATOR);
            if (info[OPERATION_INDEX].equals("supply")) {
                supply += Integer.valueOf(info[AMOUNT_INDEX]);
            }
            if (info[OPERATION_INDEX].equals("buy")) {
                buy += Integer.valueOf(info[AMOUNT_INDEX]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeToFile(String data, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TO_FILE_NAME))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + TO_FILE_NAME, e);
        }
    }
}
