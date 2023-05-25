package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA_SEPARATOR = ",";
    private static final String REGEX_PATTERN = "[^0-9]";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileContent = readFromFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String file) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return builder.toString().split(" ");
    }

    private String generateReport(String[] array) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder builder = new StringBuilder(SUPPLY);

        for (String value: array) {
            if (value.contains(SUPPLY)) {
                supplySum += Integer.parseInt(value.replaceAll(REGEX_PATTERN, ""));
            }
            if (value.contains(BUY)) {
                buySum += Integer.parseInt(value.replaceAll(REGEX_PATTERN, ""));
            }
        }

        return builder.append(COMA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(COMA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(COMA_SEPARATOR).append(supplySum - buySum).toString();
    }

    private void writeToFile(String file, String data) {
        try {
            Files.deleteIfExists(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException("Can't delete a file" + file, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + file, e);
        }
    }
}
