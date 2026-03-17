package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File report = new File(toFileName);
        StringBuilder stb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else {
                    buy += Integer.parseInt(parts[1]);
                }
                value = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        result = supply - buy;
        stb.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report, true));) {
            writer.write(stb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
