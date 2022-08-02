package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String information = getInfoFromFile(fromFileName);
        String[] delimitedInfo = information.split(" ");
        int supply = countSupply(delimitedInfo);
        int buy = countBuy(delimitedInfo);
        int result = supply - buy;
        writeToFile(toFileName, supply, buy, result);
    }

    private String getInfoFromFile(String fileName) {
        List<String> informationList;
        try {
            informationList = Files.readAllLines(new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
        StringBuilder builder = new StringBuilder();
        for (String s : informationList) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write information into a file", e);
        }
    }

    private int countSupply(String[] delimitedInfo) {
        int supply = 0;
        for (String rowInfo : delimitedInfo) {
            String[] delimitedRow = rowInfo.split(",");
            if (delimitedRow[0].equals(SUPPLY_OPERATION)) {
                supply += Integer.parseInt(delimitedRow[1]);
            }
        }
        return supply;
    }

    private int countBuy(String[] delimitedInfo) {
        int buy = 0;
        for (String rowInfo : delimitedInfo) {
            String[] delimitedRow = rowInfo.split(",");
            if (delimitedRow[0].equals(BUY_OPERATION)) {
                buy += Integer.parseInt(delimitedRow[1]);
            }
        }
        return buy;
    }
}
