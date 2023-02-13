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
        String dataToWrite = valuesCounter(readDataList);
        writeDataInFile(toFileName, dataToWrite);
    }

    private List<String> readDataFromFile(String dataFile) {
        File file = new File(dataFile);
        List<String> readList = null;
        try {
            readList = Files.readAllLines(file.toPath());
            System.out.println(readList);

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return readList;
    }

    private void writeDataInFile(String fileForResult, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForResult))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String valuesCounter(List<String> list) {
        StringBuilder builder = new StringBuilder();
        int supplyCounter = 0;
        int buyCounter = 0;
        for (int i = 0; i < list.size(); i++) {
            String[] pointsArray = list.get(i).split(DELIMITER);
            System.out.println(list.get(i));
            if (pointsArray[NAME_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(pointsArray[VALUE_INDEX]);
            } else if (pointsArray[NAME_INDEX].equals(BUY)) {
                buyCounter += Integer.parseInt(pointsArray[VALUE_INDEX]);
            } else {
                System.out.println("Invalid data.");
            }
        }
        return builder.append(SUPPLY).append(DELIMITER).append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT).append(DELIMITER).append(supplyCounter - buyCounter).toString();
    }
}
