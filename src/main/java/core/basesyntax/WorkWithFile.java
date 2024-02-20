package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class provides methods to read data from a file, process it, and write the
 * results to another file.
 */
public class WorkWithFile {
    private static final String READ_DATA_EXCEPTION = "Can't read data from the file ";
    private static final String WRITE_DATA_EXCEPTION = "Can't write data to the file ";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result,";
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
        writeToFile(content, toFileName);
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
            return prepareStatistic(stringBuilder.toString().split(SPACE));
        } catch (IOException e) {
            throw new RuntimeException(READ_DATA_EXCEPTION + fileName, e);
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
            throw new RuntimeException(WRITE_DATA_EXCEPTION + fileName, e);
        }
    }

    /**
     * Processes the raw data array and returns an array containing statistics.
     *
     * @param array The raw data array to process.
     * @return An array containing processed statistics.
     */
    private String[] prepareStatistic(String[] array) {
        int length = countUnicElements(array);
        String[] operationList = new String[length];
        int[] amountList = new int[length];

        for (String row : array) {
            String[] arrayElement = row.split(COMMA);
            String operation = arrayElement[INDEX_ZERO];
            int amount = Integer.parseInt(arrayElement[INDEX_ONE]);
            int elementIndex = findMatch(operationList, operation);

            if (elementIndex != -1) {
                amountList[elementIndex] += amount;
            } else {
                if (SUPPLY.equals(operation)) {
                    System.arraycopy(operationList, INDEX_ZERO, operationList,
                            INDEX_ONE, length - INDEX_ONE);
                    System.arraycopy(amountList, INDEX_ZERO, amountList,
                            INDEX_ONE, length - INDEX_ONE);
                    operationList[0] = operation;
                    amountList[0] = amount;
                } else {
                    int emptyIndex = findMatch(operationList, null);
                    operationList[emptyIndex] = operation;
                    amountList[emptyIndex] = amount;
                }
            }
        }
        return concatArrays(operationList, amountList);
    }

    /**
     * Calculates and returns the result based on the given array of statistics.
     *
     * @param array An array containing statistics.
     * @return The calculated result.
     */
    private int getResult(String[] array) {
        int supply = Integer.parseInt(array[INDEX_ZERO].split(COMMA)[INDEX_ONE]);
        int buy = Integer.parseInt(array[INDEX_ONE].split(COMMA)[INDEX_ONE]);
        return supply - buy;
    }

    /**
     * Concatenates arrays of keys and values into a single array.
     *
     * @param keys   An array of keys.
     * @param values An array of values.
     * @return A joined array of keys and values.
     */
    private String[] concatArrays(String[] keys, int[] values) {
        String[] joinedArray = new String[keys.length + INDEX_ONE];
        for (int i = 0; i < keys.length; i++) {
            joinedArray[i] = keys[i] + COMMA + values[i];
        }
        int lastSlot = joinedArray.length - INDEX_ONE;
        joinedArray[lastSlot] = RESULT + getResult(joinedArray);
        return joinedArray;
    }

    /**
     * Finds the index of the first occurrence of a given match in the array.
     *
     * @param list  The array to search for the match.
     * @param match The value to find in the array.
     * @return The index of the first occurrence of the match, or -1 if not found.
     */
    private int findMatch(String[] list, String match) {
        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(list[i], match)) {
                return i;
            }
        }
        return -1;
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
