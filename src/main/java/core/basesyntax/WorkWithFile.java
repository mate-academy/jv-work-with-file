package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {

        String information = getInfoFromFile(fromFileName);
        String[] lines = information.split(" ");
        String countedAmount = calculateStatistic(lines);
        writeToFile(toFileName, countedAmount);
    }

    private String getInfoFromFile(String fileName) {
        List<String> informationList;
        try {
            informationList = Files.readAllLines(new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file " + fileName, e);
        }
        StringBuilder builder = new StringBuilder();
        for (String rowWithInfo : informationList) {
            builder.append(rowWithInfo).append(" ");
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String info) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write information into the file " + toFileName, e);
        }
    }

    private String calculateStatistic(String[] lines) {
        int buy = 0;
        int supply = 0;
        int result;
        for (String rowInfo : lines) {
            String[] splitRow = rowInfo.split(",");
            if (splitRow[0].equals(BUY)) {
                buy += Integer.parseInt(splitRow[1]);
            } else {
                supply += Integer.parseInt(splitRow[1]);
            }
        }
        result = supply - buy;
        return prepareReport(supply, buy, result);
    }

    private String prepareReport(int supply, int buy, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator());
        builder.append("buy,").append(buy).append(System.lineSeparator());
        builder.append("result,").append(result);
        return builder.toString();
    }
}
