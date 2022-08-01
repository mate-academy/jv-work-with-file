package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIVERED_GOODS = "supply";
    private static final String SOLD_GOODS = "buy";
    private static final String RESULT_OF_TRADE = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(makeReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(fromFileName));
            String line = lineReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SEPARATOR);
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + " " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String makeReport(String dataFromFile) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitData = dataFromFile.split(SEPARATOR);
        for (int i = 0; i < splitData.length; i++) {
            if (splitData[i].equals(DELIVERED_GOODS)) {
                totalSupply += Integer.parseInt(splitData[i + 1]);
            } else if (splitData[i].equals(SOLD_GOODS)) {
                totalBuy += Integer.parseInt(splitData[i + 1]);
            }
        }
        return stringBuilder.append(DELIVERED_GOODS + SEPARATOR)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(SOLD_GOODS + SEPARATOR)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT_OF_TRADE + SEPARATOR)
                .append(totalSupply - totalBuy).toString();
    }

    private void writeToFile(String dataReport, String toFileName) {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            dataWriter.write(dataReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + " " + toFileName, e);
        }
    }
}
