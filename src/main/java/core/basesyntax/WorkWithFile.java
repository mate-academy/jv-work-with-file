package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] records = readFromFile(fromFileName).split(System.lineSeparator());

        for (String record : records) {
            if (record.contains("supply")) {
                totalSupply += Integer.parseInt(record.substring(record.indexOf(",") + 1));
            }
            if (record.contains("buy")) {
                totalBuy += Integer.parseInt(record.substring(record.indexOf(",") + 1));
            }
        }
        StringBuilder builderReport = createReport(totalSupply, totalBuy);
        writeToFile(toFileName, builderReport);
    }

    private void writeToFile(String fileName, StringBuilder stringBuilder) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(fileName)))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private StringBuilder createReport(int totalSupply, int totalBuy) {
        return new StringBuilder().append("supply,").append(totalSupply)
                .append(System.lineSeparator()).append("buy,")
                .append(totalBuy).append(System.lineSeparator()).append("result,")
                .append(totalSupply - totalBuy).append(System.lineSeparator());
    }
}
