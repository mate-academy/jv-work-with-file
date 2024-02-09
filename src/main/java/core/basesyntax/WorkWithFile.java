package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report,toFileName);
    }

    private String readFromFile(String fileName) {
        String line;
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Problems with file: " + fileName, e);
        }
        return fileContent.toString();
    }

    private String createReport(String data) {
        int supplyCount = 0;
        int buyCount = 0;
        String [] lines = data.split(LINE_SEPARATOR);
        for (String line : lines) {
            String [] input = line.split(DELIMITER);
            switch (input[OPERATION_INDEX]) {
                case "supply":
                    supplyCount += Integer.parseInt(input[AMOUNT_INDEX]);
                    break;
                case "buy":
                    buyCount += Integer.parseInt(input[AMOUNT_INDEX]);
                    break;
                default:
                    break;
            }
        }
        return "supply" + DELIMITER + supplyCount + LINE_SEPARATOR
                + "buy" + DELIMITER + buyCount + LINE_SEPARATOR
                + "result" + DELIMITER + (supplyCount - buyCount);
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Problems with file: " + fileName, e);
        }
    }
}
