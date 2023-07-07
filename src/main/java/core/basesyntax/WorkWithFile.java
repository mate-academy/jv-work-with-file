package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int CORRECT_PARTS_SIZE = 2;
    private static final int ACTION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = createReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String filePath) {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + filePath, e);
        }

        return fileContent.toString();
    }

    private String createReport(String fileContent) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] lines = fileContent.split(System.lineSeparator());
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length == CORRECT_PARTS_SIZE) {
                String action = parts[ACTION_INDEX].trim();
                int value = Integer.parseInt(parts[VALUE_INDEX].trim());

                if (action.equalsIgnoreCase("supply")) {
                    totalSupply += value;
                } else if (action.equalsIgnoreCase("buy")) {
                    totalBuy += value;
                }
            }
        }

        result.append("supply,").append(totalSupply).append(System.lineSeparator());
        result.append("buy,").append(totalBuy).append(System.lineSeparator());
        result.append("result,").append(totalSupply - totalBuy);

        return result.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}

