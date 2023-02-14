package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> readDataList = readDataFromFile(fromFileName);
        String dataToWrite = countOperationsAmount(readDataList);
        writeDataInFile(toFileName, dataToWrite);
    }

    private List<String> readDataFromFile(String dataFile) {
        File file = new File(dataFile);
        List<String> readList = null;
        try {
            readList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return readList;
    }

    private void writeDataInFile(String resultFile, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String countOperationsAmount(List<String> linesFromFile) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (int i = 0; i < linesFromFile.size(); i++) {
            String[] operations = linesFromFile.get(i).split(DELIMITER);
            if (operations[OPERATION_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(operations[AMOUNT_INDEX]);
            } else if (operations[OPERATION_INDEX].equals(BUY)) {
                buyCounter += Integer.parseInt(operations[AMOUNT_INDEX]);
            } else {
                throw new RuntimeException("Invalid data to read.");
            }
        }
        return createResultString(supplyCounter, buyCounter);
    }

    private String createResultString(int supplyCounter, int buyCounter) {
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY).append(DELIMITER).append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(supplyCounter - buyCounter).toString();
    }
}
