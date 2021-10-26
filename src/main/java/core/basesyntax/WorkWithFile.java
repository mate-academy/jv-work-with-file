package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = makeReport(data);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder text = new StringBuilder();
        String value = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            value = bufferedReader.readLine();
            while (value != null) {
                text.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return text.toString();
    }

    private String makeReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] strings = data.split("\\r?\\n");
        StringBuilder report = new StringBuilder();
        for (String string: strings) {
            String[] splitedString = string.split(",");
            if (SUPPLY.equals(splitedString[0])) {
                totalSupply += Integer.parseInt(splitedString[1]);
            } else if (BUY.equals(splitedString[0])) {
                totalBuy += Integer.parseInt(splitedString[1]);
            }
        }
        return report.append(SUPPLY).append(COMMA).append(totalSupply)
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
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
