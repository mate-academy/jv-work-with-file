package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = makingReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        String value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file" + e);
        }
        return builder.toString();
    }

    private String makingReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] lines = data.split("\\r?\\n");
        StringBuilder reportBuilder = new StringBuilder();
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            if (SUPPLY.equals(splittedLine[0])) {
                totalSupply += Integer.parseInt(splittedLine[1]);
            } else if (BUY.equals(splittedLine[0])) {
                totalBuy += Integer.parseInt(splittedLine[1]);
            }
        }
        return reportBuilder.append(SUPPLY).append(COMMA).append(totalSupply)
                            .append(System.lineSeparator())
                            .append(BUY).append(COMMA).append(totalBuy)
                            .append(System.lineSeparator())
                            .append(RESULT).append(COMMA).append(totalSupply - totalBuy)
                            .toString();
    }

    private void writeToFile(String data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + e);
        }
    }
}
