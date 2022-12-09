package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String report = createReport(data);
        writeReport(report, toFileName);
    }

    String[] readFile(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while ((value != null)) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(",");
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    String createReport(String[] data) {
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder create = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(data[i + 1]);
            }
            if (data[i].equals(BUY)) {
                sumBuy += Integer.parseInt(data[i + 1]);
            }
        }
        int result = sumSupply - sumBuy;
        return create.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to to file", e);
        }
    }
}
