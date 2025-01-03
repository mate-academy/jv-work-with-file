package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFromFile(fromFileName);
        String report = createReport(infoFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(LINE_SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String infoFromFile) {
        int supply = 0;
        int buy = 0;
        String[] data = infoFromFile.split(LINE_SEPARATOR);
        for (String infoFromLine : data) {
            String[] information = infoFromLine.split(COMMA);
            int amount = Integer.parseInt(information[AMOUNT_INDEX]);
            switch (information[OPERATION_TYPE_INDEX]) {
                case SUPPLY:
                    supply += amount;
                    break;
                case BUY:
                default:
                    buy += amount;
            }
        }
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supply).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(buy).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(supply - buy)
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to " + toFileName, e);
        }
    }
}
