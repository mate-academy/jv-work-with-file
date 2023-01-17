package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TITLE_POSITION = 0;
    private static final int NUM_POSITION = 1;
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        String report = calculateData(inputData);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder inputData = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                inputData.append(inputLine).append(System.lineSeparator());
            }
            return inputData.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String calculateData(String dataFromFile) {
        StringBuilder report = new StringBuilder();
        String[] splitData = dataFromFile.split(System.lineSeparator());
        int result;
        int supply = 0;
        int buy = 0;

        for (String line : splitData) {
            String[] splitLine = line.split(SEPARATOR);
            switch (splitLine[TITLE_POSITION]) {
                case SUPPLY:
                    supply += Integer.parseInt(splitLine[NUM_POSITION]);
                    break;
                case BUY:
                    buy += Integer.parseInt(splitLine[NUM_POSITION]);
                    break;
                default:
                    System.out.println("There is no acceptable option");
            }
        }
        result = supply - buy;
        report.append(SUPPLY).append(SEPARATOR).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
