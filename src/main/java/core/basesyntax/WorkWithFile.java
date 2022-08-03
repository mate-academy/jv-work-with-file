package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = "[\\D]";

    public void getStatistic(String fromFileName, String toFileName) {
        String supplyData = readFromFile(fromFileName);
        writeToFile(toFileName, getTotalSupplyReport(supplyData));
    }

    private void writeToFile(String toFileName, String resultData) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String getTotalSupplyReport(String supplyReport) {
        int countBuy = 0;
        int countSupply = 0;

        String[] lines = supplyReport.split(System.lineSeparator());
        for (String line : lines) {
            if (line.contains("buy")) {
                countBuy += Integer.parseInt(line.replaceAll(REGEX, ""));
            }
            if (line.contains("supply")) {
                countSupply += Integer.parseInt(line.replaceAll(REGEX, ""));
            }
        }
        int result = countSupply - countBuy;

        return new StringBuilder()
                .append("supply,").append(countSupply).append(System.lineSeparator())
                .append("buy,").append(countBuy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }
}
