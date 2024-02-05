package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int TYPE_OPERATION = 0;
    private static final int INDEX = 1;

    private String readToFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t to open this file!", e);
        }
        return builder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readToFile(fromFileName);
        String processFromFile = processData(dataFromFile);
        writeToFile(processFromFile, toFileName);
    }

    private String processData(String internalReport) {
        int totalPurchase = 0;
        int totalSupply = 0;
        StringBuilder builder = new StringBuilder();
        String[] array = internalReport.split(System.lineSeparator());
        for (String line : array) {
            String[] list = line.split(",");
            if (list.length == 2) {
                String word = list[TYPE_OPERATION].trim().toLowerCase();
                int amount = Integer.parseInt(list[INDEX].trim());

                if (BUY.equals(word)) {
                    totalPurchase += amount;
                } else if (SUPPLY.equals(word)) {
                    totalSupply += amount;
                }
            }
        }
        builder.append("supply,").append(totalSupply).append(System.lineSeparator());
        builder.append("buy,").append(totalPurchase).append(System.lineSeparator());
        builder.append("result,").append(totalSupply - totalPurchase);
        System.out.println(builder);
        return builder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t to write into the file!", e);
        }
    }
}
