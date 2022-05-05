package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createStatistic(readFromFile(fromFileName)), toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> fileData = new ArrayList<>();
        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return fileData;
    }

    private String createStatistic(List<String> lines) {
        int allSupply = 0;
        int allBuy = 0;
        for (String line : lines) {
            String[] tempData = line.split(",",0);
            if (tempData[0].equals(BUY)) {
                allBuy += Integer.valueOf(tempData[1]);
            }
            if (tempData[0].equals(SUPPLY)) {
                allSupply += Integer.valueOf(tempData[1]);
            }
        }
        int result = allSupply - allBuy;
        StringBuilder statisticBuilder = new StringBuilder();
        statisticBuilder.append(SUPPLY)
                .append(",")
                .append(allSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(allBuy)
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(result);
        return statisticBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
