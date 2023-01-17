package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String value;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                } else if (line.contains("supply")) {
                    value = line.split(",")[1];
                    supplyTotal += Integer.parseInt(value);
                } else {
                    value = line.split(",")[1];
                    buyTotal += Integer.parseInt(value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + e);
        }
        report.append("supply,").append(supplyTotal)
                .append(System.lineSeparator())
                .append("buy,").append(buyTotal)
                .append(System.lineSeparator())
                .append("result,").append(supplyTotal - buyTotal);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }
}
