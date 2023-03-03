package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from file" + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String readString) {
        int supplyCount = 0;
        int buyCount = 0;
        String[] lines = readString.split(System.lineSeparator());
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(splittedLine[AMMOUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(splittedLine[AMMOUNT_INDEX]);
            }
        }
        int result = supplyCount - buyCount;
        return SUPPLY
                + "," + supplyCount
                + System.lineSeparator()
                + BUY + ","
                + buyCount
                + System.lineSeparator()
                + RESULT + ","
                + result;
    }

    private void writeToFile(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
