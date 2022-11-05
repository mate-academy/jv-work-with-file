package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String REGEX = ",";

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                builder.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        String[] elements = data.split(System.lineSeparator());
        for (String nums : elements) {
            String[] split = nums.split(",");
            if (split[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                supplySum = supplySum + Integer.parseInt(split[AMOUNT_INDEX]);
            } else if (split[OPERATION_TYPE_INDEX].equals(BUY)) {
                buySum = buySum + Integer.parseInt(split[AMOUNT_INDEX]);
            }
        }
        return SUPPLY + REGEX + supplySum + System.lineSeparator()
                + BUY + REGEX + buySum + System.lineSeparator()
                + RESULT + REGEX + (supplySum - buySum);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }
}
