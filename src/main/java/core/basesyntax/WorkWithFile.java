package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String record = reader.readLine();
            while (record != null) {
                String[] split = record.split(",");
                record = reader.readLine();
                if (split[0].equals("buy")) {
                    buy = buy + Integer.parseInt(split[1]);
                } else {
                    supply = supply + Integer.parseInt(split[1]);
                }
            }
            System.out.println(buy);
            System.out.println(supply);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder str = new StringBuilder();
            str.append("supply");
            str.append(",");
            str.append(supply);
            str.append(System.lineSeparator());

            str.append("buy");
            str.append(",");
            str.append(buy);
            str.append(System.lineSeparator());

            str.append("result");
            str.append(",");
            str.append(supply - buy);
            str.append(System.lineSeparator());
            writer.write(str.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}

