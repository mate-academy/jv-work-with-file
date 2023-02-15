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
    public static final int NAME_INDEX = 0;
    public static final int VALUE_INDEX = 1;
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> readDataList = readDataFromFile(fromFileName);
        String dataToWrite = countValues(readDataList);
        writeDataInFile(toFileName, dataToWrite);
    }

    private List<String> readDataFromFile(String dataFile) {
        File file = new File(dataFile);
        List<String> readList;
        try {
            readList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + dataFile, e);
        }
        return readList;
    }

    private void writeDataInFile(String resultFile, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file " + resultFile, e);
        }
    }

    private String countValues(List<String> linesFromFile) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String line : linesFromFile) {
            String[] operations = line.split(DELIMITER);
            if (operations[NAME_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(operations[VALUE_INDEX ]);
            } else if (operations[NAME_INDEX].equals(BUY)) {
                buyCounter += Integer.parseInt(operations[VALUE_INDEX ]);
            } else {
                throw new RuntimeException("Can't read file.");
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
