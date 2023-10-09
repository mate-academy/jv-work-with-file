package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COLUMN_SEPARATOR = ",";
    private static final int OPERATION_POSITION = 0;
    private static final int VALUE_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String[][] report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    private String[][] createReport(String fileData) {
        String[] lines = fileData.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] lineData = line.split(COLUMN_SEPARATOR);
            switch (lineData[OPERATION_POSITION]) {
                case SUPPLY:
                    supply += Integer.parseInt(lineData[VALUE_POSITION]);
                    break;
                case BUY:
                    buy += Integer.parseInt(lineData[VALUE_POSITION]);
                    break;
                default:
                    throw new RuntimeException("Unknown operation:  "
                            + lineData[OPERATION_POSITION]);
            }
        }
        int result = supply - buy;
        return new String[][]{{SUPPLY, Integer.toString(supply)},
                {BUY, Integer.toString(buy)},
                {RESULT, Integer.toString(result)}};
    }

    private String readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read line from file", e);
        }
    }

    private void writeToFile(String fileName, String[][] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String[] line = data[i];
            for (int j = 0; j < line.length; j++) {
                stringBuilder.append(line[j]);
                if (j < line.length - 1) {
                    stringBuilder.append(COLUMN_SEPARATOR);
                }
            }
            if (i < data.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        File myFile = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(myFile)) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
