package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        int[] statistic = countStatistic(dataFromFile);
        String report = prepareReport(statistic);
        try {
            Files.write(new File(toFileName).toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    public List<String> readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            List<String> data = new ArrayList<>();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
    }

    public int[] countStatistic(List<String> data) {
        int[] statistic = new int[2];
        String[] splittedLine;
        for (String line : data) {
            splittedLine = line.split(",");
            if (splittedLine[OPERATION_INDEX].equals(BUY_OPERATION)) {
                statistic[0] += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                statistic[1] += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        return statistic;
    }

    public String prepareReport(int[] statistic) {
        StringBuilder builder = new StringBuilder();
        int totalSupply = statistic[1];
        int totalBuy = statistic[0];
        int result = totalSupply - totalBuy;
        builder.append(SUPPLY_OPERATION).append(",").append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(",").append(totalBuy)
                .append(System.lineSeparator())
                .append("result,").append(result);
        return builder.toString();
    }
}
