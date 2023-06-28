package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFromFile(fromFileName);
            String report = createReport(fileContent);
            writeToFile(report, toFileName);
        } catch (RuntimeException e) {
            System.out.println("An error occurred while processing the file: " + e.getMessage());
        }
    }

    private String readFromFile(String fromFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return sb.toString();
    }

    private String createReport(String fileContent) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder sb = new StringBuilder();
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] splitLine = line.split(",");
            String operation = splitLine[0];
            int amount = Integer.parseInt(splitLine[1]);
            if (operation.equalsIgnoreCase(OPERATION_SUPPLY)) {
                supplySum += amount;
            } else if (operation.equalsIgnoreCase(OPERATION_BUY)) {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        sb.append(OPERATION_SUPPLY).append(",").append(supplySum).append(System.lineSeparator());
        sb.append(OPERATION_BUY).append(",").append(buySum).append(System.lineSeparator());
        sb.append("result,").append(result).append(System.lineSeparator());
        return sb.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}



