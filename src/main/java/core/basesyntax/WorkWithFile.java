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
    private static final String COMMA = ",";
    private static final int STRING_VALUE_INDEX = 0;
    private static final int INTEGER_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder fileData = new StringBuilder();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                int readeValue = reader.read();
                while (readeValue != -1) {
                    fileData.append((char) readeValue);
                    readeValue = reader.read();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read file" + fromFileName, e);
            }
        }
        return fileData.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private String createReport(String reportData) {
        int supply = 0;
        int buy = 0;
        String[] reportDataArray = reportData.split(System.lineSeparator());
        for (String reportDataLine : reportDataArray) {
            String[] bufferString = reportDataLine.split(COMMA);
            if (bufferString[STRING_VALUE_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(bufferString[INTEGER_VALUE_INDEX]);
            }
            if (bufferString[STRING_VALUE_INDEX].equals(BUY)) {
                buy += Integer.parseInt(bufferString[INTEGER_VALUE_INDEX]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator());
        stringBuilder.append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator());
        stringBuilder.append(RESULT).append(COMMA)
                .append(supply - buy);
        return stringBuilder.toString();
    }
}
