package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int AMOUNT_POSITION_IN_ARRAY = 0;
    public static final int OPERATION_TYPE_POSITION_IN_ARRAY = 1;
    private static final String LINE_SUPPLY = "supply";
    private static final String LINE_BUY = "buy";
    private static final String LINE_COMMA = ",";
    private static final String LINE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String dataFromFile) {
        StringBuilder report = new StringBuilder();
        int result = 0;
        int supply = 0;
        int buy = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] words = line.split(LINE_COMMA);
            if (words[AMOUNT_POSITION_IN_ARRAY].equals(LINE_SUPPLY)) {
                supply += Integer.parseInt(words[OPERATION_TYPE_POSITION_IN_ARRAY]);
                result += Integer.parseInt(words[OPERATION_TYPE_POSITION_IN_ARRAY]);
            } else {
                buy += Integer.parseInt(words[OPERATION_TYPE_POSITION_IN_ARRAY]);
                result -= Integer.parseInt(words[OPERATION_TYPE_POSITION_IN_ARRAY]);
            }
        }
        report.append(LINE_SUPPLY).append(LINE_COMMA).append(supply).append(System.lineSeparator())
                .append(LINE_BUY).append(LINE_COMMA).append(buy).append(System.lineSeparator())
                .append(LINE_RESULT).append(LINE_COMMA).append(result);
        return report.toString();
    }

    public String readFromFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataFromFile.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from file" + fromFileName, e);
        }

        return dataFromFile.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file" + toFileName, e);
        }
    }
}
