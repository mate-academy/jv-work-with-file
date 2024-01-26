package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";
    private static final String RESULT_TYPE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        statistic = calculateStatistics(statistic);
        writeToFile(toFileName, statistic);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                fileContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName + ": " + e);
        }
        return fileContent.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        File file = new File(toFileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't make new file " + toFileName + ": " + e);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName + ": " + e);
        }
    }

    private String calculateStatistics(String statistic) {
        String[] lines = statistic.split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : lines) {
            String[] parsedLine = line.split(",");
            if (parsedLine[OPERATION_TYPE].equals(SUPPLY_TYPE)) {
                totalSupply += Integer.parseInt(parsedLine[AMOUNT]);
            } else if (parsedLine[OPERATION_TYPE].equals(BUY_TYPE)) {
                totalBuy += Integer.parseInt(parsedLine[AMOUNT]);
            }
        }
        return SUPPLY_TYPE + "," + totalSupply + System.lineSeparator()
                + BUY_TYPE + "," + totalBuy + System.lineSeparator()
                + RESULT_TYPE + "," + (totalSupply - totalBuy);
    }
}
