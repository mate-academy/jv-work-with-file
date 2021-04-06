package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final int KIND_OF_ACTION = 0;
    private static final int ACTION_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getReport(getInformation(fromFileName)));
    }

    private int[] getInformation(String fileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(SEPARATOR);
                if (line[KIND_OF_ACTION].equals(SUPPLY)) {
                    supply += Integer.parseInt(line[ACTION_VALUE]);
                } else {
                    buy += Integer.parseInt(line[ACTION_VALUE]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read " + fileName, e);
        }
        return new int[]{supply, buy};
    }

    private String getReport(int[] data) {
        StringBuilder result = new StringBuilder()
                .append("supply,").append(data[0]).append(System.lineSeparator())
                .append("buy,").append(data[1]).append(System.lineSeparator())
                .append("result,").append(data[0] - data[1]);
        return result.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write information to file " + fileName, e);
        }
    }
}
