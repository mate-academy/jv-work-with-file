package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = createReport(fileContent);
        writeToFile(toFileName, report);
    }

    public static String readFileContent(String filePath) {
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

    public static String createReport(String fileContent) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] lines = fileContent.split(System.lineSeparator());
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String action = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());

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

    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}

