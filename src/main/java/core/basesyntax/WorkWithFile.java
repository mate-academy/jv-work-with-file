package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String content = readFile(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                data.append(value).append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        return data.toString();
    }

    private String createReport(String string) {
        String[] split = string.split(SEPARATOR);
        StringBuilder finalResult = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;

        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("supply")) {
                totalSupply += Integer.parseInt(split[i + 1]);
            } else if (split[i].equals("buy")) {
                totalBuy += Integer.parseInt(split[i + 1]);
            }
        }

        finalResult.append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(totalSupply - totalBuy);

        return finalResult.toString();
    }

    private void writeToFile(String toFileName, String finalResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(finalResult);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
