package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String SPLIT_DELIMITER_SCV = ",";
    public static final String DATA_FIRST = "supply";
    public static final String DATA_SECOND = "buy";
    public static final String DATA_THIRD = "result";
    public static final String DELIMITER_ARRAY = System.lineSeparator();
    public static final int READ_FILE_DATA_INDEX_ONE = 0;
    public static final int READ_FILE_DATA_INDEX_TWO = 1;

    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from: " + fileName, e);
        }
    }

    public String generateReport(String readFile) {
        int resultBuy = 0;
        int resultSupply = 0;
        StringBuilder builderWriteFile = new StringBuilder();
        String[] splitReadFile = readFile.split(DELIMITER_ARRAY);
        for (String rowSplitReadFile: splitReadFile) {
            String[] splitInto = rowSplitReadFile.split(SPLIT_DELIMITER_SCV);
            if (splitInto[READ_FILE_DATA_INDEX_ONE].equals("buy")) {
                resultBuy = resultBuy + Integer.parseInt(splitInto[READ_FILE_DATA_INDEX_TWO]);
            }
            if (splitInto[READ_FILE_DATA_INDEX_ONE].equals("supply")) {
                resultSupply = resultSupply + Integer.parseInt(splitInto[READ_FILE_DATA_INDEX_TWO]);
            }
        }
        int resultWithSupplyAndBuy = resultSupply - resultBuy;
        builderWriteFile.append(DATA_FIRST + SPLIT_DELIMITER_SCV)
                .append(resultSupply)
                .append(DELIMITER_ARRAY)
                .append(DATA_SECOND + SPLIT_DELIMITER_SCV)
                .append(resultBuy)
                .append(DELIMITER_ARRAY)
                .append(DATA_THIRD + SPLIT_DELIMITER_SCV)
                .append(resultWithSupplyAndBuy);
        return builderWriteFile.toString();
    }

    public void writeToFile(String writeFileName, String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to: " + writeFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }
}
