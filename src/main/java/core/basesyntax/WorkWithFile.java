package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String [] infoFromFile = getInfo(fromFileName).split(" ");
        String countedAmount = getCount(infoFromFile);
        writeToFile(toFileName, countedAmount);
    }

    private String getInfo(String fileName) {
        List<String> infoFromList;
        try {
            infoFromList = Files.readAllLines(new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file " + fileName, e);
        }
        StringBuilder builder = new StringBuilder();
        for (String info : infoFromList) {
            builder.append(info).append(" ");
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write information into the file " + toFileName, e);
        }
    }

    private String getCount(String [] info) {
        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        for (String rowInfo : info) {
            String[] line = rowInfo.split(DELIMITER);
            if (line[OPERATION_INDEX].equals(BUY_OPERATION)) {
                buy += Integer.parseInt(line[NUMBER_INDEX]);
            } else {
                supply += Integer.parseInt(line[NUMBER_INDEX]);
            }
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return stringBuilder.toString();
    }
}
