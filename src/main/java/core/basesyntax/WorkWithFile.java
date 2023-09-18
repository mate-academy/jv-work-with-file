package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME = 0;
    private static final int NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = getResult(fileContent);
        writeToFile(toFileName, report);
    }

    private static String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            StringBuilder builder = new StringBuilder();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String contentFile = builder.toString().trim();
            return contentFile;

        } catch (IOException e) {
            throw new RuntimeException("Cannot read data");
        }
    }

    private static String getResult(String contentFile) {
        String [] inputValues = contentFile.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (int i = 0;i < inputValues.length;i++) {
            String [] parts = inputValues[i].split(",");
            if (parts[NAME].equals("supply")) {
                supplySum += Integer.parseInt(parts[NUMBER]);
            }
            if (parts[NAME].equals("buy")) {
                buySum += Integer.parseInt(parts[NUMBER]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplySum).append(System.lineSeparator());
        builder.append("buy,").append(buySum).append(System.lineSeparator());
        builder.append("result,").append(result);
        String resultReport = builder.toString();
        return resultReport;

    }

    private void writeToFile(String toFileName, String resultReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}
