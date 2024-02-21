package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides methods to read data from a file, process it, and write the
 * results to another file.
 */
public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final int INDEX_ONE = 1;
    private static final int INDEX_ZERO = 0;

    /**
     * Reads data from the specified file, processes the statistics, and writes the
     * result to another file.
     *
     * @param fromFileName The name of the file to read data from.
     * @param toFileName   The name of the file to write the result to.
     */
    public void getStatistic(String fromFileName, String toFileName) {
        String[] content = readFromFile(fromFileName);
        String[] report = generateReport(content);
        writeToFile(report, toFileName);
    }

    /**
     * Reads content from the specified file and processes it to return an array
     * containing statistics.
     *
     * @param fileName The name of the file to read data from.
     * @return An array containing processed statistics.
     * @throws RuntimeException If an error occurs while reading the file.
     */
    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPACE);
                value = reader.readLine();
            }
            return stringBuilder.toString().split(SPACE);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    /**
     * Writes the given content to the specified file.
     *
     * @param content  The content to write to the file.
     * @param fileName The name of the file to write data to.
     * @throws RuntimeException If an error occurs while writing to the file.
     */
    public void writeToFile(String[] content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    /**
     * Generates a report based on the input array containing supply and buy data.
     * <p>
     * This method takes an array of strings, where each string represents a data entry
     * in the format "TYPE, VALUE". It calculates the total supply, total buy, and the
     * resulting difference between supply and buy. The generated report is returned as
     * an array of strings in the format:
     *  - "SUPPLY, totalSupply"
     *  - "BUY, totalBuy"
     *  - "RESULT, totalSupply - totalBuy"
     *
     * @param array The input array containing supply and buy data in the format "TYPE, VALUE".
     * @return An array of strings representing the generated report.
     * @throws NumberFormatException If the numeric values in the input array cannot be parsed.
     */
    private String[] generateReport(String[] array) {
        int length = countUnicElements(array);
        String[] result = new String[length + INDEX_ONE];
        int totalSupply = 0;
        int totalBuy = 0;

        for (String value : array) {
            String[] splitData = value.split(COMMA);
            int amount = Integer.parseInt(splitData[INDEX_ONE]);

            if (splitData[INDEX_ZERO].equals(SUPPLY)) {
                totalSupply += amount;
            } else {
                totalBuy += amount;
            }
        }

        result[0] = SUPPLY + COMMA + totalSupply;
        result[1] = BUY + COMMA + totalBuy;
        result[2] = RESULT + COMMA + (totalSupply - totalBuy);

        return result;
    }

    /**
     * Counts the number of unique elements in the array.
     *
     * @param list The array to count unique elements from.
     * @return The number of unique elements in the array.
     */
    private int countUnicElements(String[] list) {
        Set<String> uniqueElements = new HashSet<>();
        for (String row : list) {
            String[] arrayElement = row.split(COMMA);
            String operation = arrayElement[INDEX_ZERO];
            uniqueElements.add(operation);
        }
        return uniqueElements.size();
    }
}
