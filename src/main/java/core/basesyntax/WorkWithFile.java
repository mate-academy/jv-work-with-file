package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String DELIMITER_SCV = ",";
    public static final String DATA_FIRST = "buy";
    public static final String DATA_SECOND = "supply";
    public static final String DATA_THIRD = "result";
    public static final String DELIMITER_ARRAY = System.lineSeparator();
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from: " + fileName, e);
        }
    }

    public String generateReport(String file) {
        int resultBuy = 0;
        int resultSupply = 0;

        String[] splitReadFile = file.split(DELIMITER_ARRAY);
        for (String rowSplitReadFile: splitReadFile) {
            String[] splitInto = rowSplitReadFile.split(DELIMITER_SCV);
            if (splitInto[OPERATION_TYPE_INDEX].equals(DATA_FIRST)) {
                resultBuy = resultBuy + Integer.parseInt(splitInto[AMOUNT_INDEX]);
            }
            if (splitInto[OPERATION_TYPE_INDEX].equals(DATA_SECOND)) {
                resultSupply = resultSupply + Integer.parseInt(splitInto[AMOUNT_INDEX]);
            }
        }
        return buildReportString(resultSupply,resultBuy);
    }

    private String buildReportString(int supply, int buy) {
        return new StringBuilder().append(DATA_SECOND + DELIMITER_SCV)
                .append(supply)
                .append(DELIMITER_ARRAY)
                .append(DATA_FIRST + DELIMITER_SCV)
                .append(buy)
                .append(DELIMITER_ARRAY)
                .append(DATA_THIRD + DELIMITER_SCV)
                .append(supply - buy)
                .toString();
    }

    public void writeToFile(String writeFileName, String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to: " + writeFileName, e);
        }
    }
}
