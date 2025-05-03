package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] content = readFromFile(fromFileName).split(SPACE);
        List<String> report = generateReport(content);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPACE);
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    public void writeToFile(List<String> content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private List<String> generateReport(String[] content) {
        List<String> data = new ArrayList<>();
        int totalSupply = 0;
        int totalBuy = 0;

        for (String value : content) {
            String[] splitData = value.split(COMMA);
            int amount = Integer.parseInt(splitData[AMOUNT_INDEX]);

            if (splitData[SUPPLY_INDEX].equals(SUPPLY)) {
                totalSupply += amount;
            } else {
                totalBuy += amount;
            }
        }

        data.add(SUPPLY + COMMA + totalSupply);
        data.add(BUY + COMMA + totalBuy);
        data.add(RESULT + COMMA + (totalSupply - totalBuy));

        return data;
    }
}
