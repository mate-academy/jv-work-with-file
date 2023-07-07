package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        String[] data;
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file " + fromFileName,e);
        }
        data = stringBuilder.toString().split(System.lineSeparator());
        return data;
    }

    private String createReport(String[] data) {
        int supplyInt = 0;
        String supply = "supply";
        int buyInt = 0;
        String buy = "buy";
        int resultInt = 0;
        String result = "result";
        final String coma = ",";
        int quantity = 0;
        for (String line : data) {
            int signPosition = line.indexOf(coma);
            quantity = Integer.parseInt(line.substring(signPosition + 1));
            if (line.substring(0, signPosition).equals(supply)) {
                supplyInt += quantity;
            } else {
                buyInt += quantity;
            }
        }
        resultInt = supplyInt - buyInt;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(supply).append(coma)
                .append(supplyInt)
                .append(System.lineSeparator())
                .append(buy)
                .append(coma)
                .append(buyInt)
                .append(System.lineSeparator())
                .append(result)
                .append(coma).append(resultInt)
                .append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file " + toFileName,e);
        }
    }
}
