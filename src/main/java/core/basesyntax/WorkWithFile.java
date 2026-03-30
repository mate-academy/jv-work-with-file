package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder("");
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                String typeOfOperation = parts[0];
                int sum = Integer.parseInt(parts[1]);
                if (typeOfOperation.equals("supply")) {
                    supply += sum;
                } else {
                    buy += sum;
                }
            }
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy)
                    .append(System.lineSeparator()).append("result,").append(supply - buy);
            String report = stringBuilder.toString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                try {
                    writer.write(report);
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data to file", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
    }
}
