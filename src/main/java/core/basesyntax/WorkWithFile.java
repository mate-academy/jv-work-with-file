package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder report = new StringBuilder();
        int totalBought = 0;
        int totalSupplied = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader((fromFileName)))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.toLowerCase(Locale.ROOT)
                        .trim().split(",");
                String operation = words[0];
                int amountOperation = Integer.parseInt(words[1]);
                if (operation.equals("buy")) {
                    totalBought += amountOperation;
                } else if (operation.equals("supply")) {
                    totalSupplied += amountOperation;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
        report.append("supply,").append(totalSupplied)
                .append(System.lineSeparator())
                .append("buy,").append(totalBought)
                .append(System.lineSeparator())
                .append("result,").append(totalSupplied - totalBought);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((toFileName)))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
