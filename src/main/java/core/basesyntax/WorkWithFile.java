package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName,getReport(getInformation(fromFileName)));
    }

    private int[] getInformation(String fileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String[] line = bufferedReader.readLine().split(COMMA_SEPARATOR);
                if (line[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(line[1]);
                } else {
                    buy += Integer.parseInt(line[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file",e);
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
            throw new RuntimeException("Can not write information in this file",e);
        }
    }
}
