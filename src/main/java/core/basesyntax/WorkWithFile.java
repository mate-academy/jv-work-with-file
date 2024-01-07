package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";
    private static final int WORD_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static String FROM_FILE_NAME;
    private static String TO_FILE_NAME;
    private StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        this.FROM_FILE_NAME = fromFileName;
        this.TO_FILE_NAME = toFileName;

        builder.setLength(0);

        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FROM_FILE_NAME))) {
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
            String[] info = row.split(SPLIT_CHARACTER);
            if (info[WORD_INDEX].equals("supply")) {
                supply += Integer.valueOf(info[COUNT_INDEX]);
            }
            if (info[WORD_INDEX].equals("buy")) {
                buy += Integer.valueOf(info[COUNT_INDEX]);
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
