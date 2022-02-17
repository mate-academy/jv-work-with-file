package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
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
        try {
            FileWriter writer = new FileWriter(toFileName);
            writer.append("supply");
            writer.append(",");
            writer.append(Integer.toString(supply));
            writer.append(System.lineSeparator());

            writer.append("buy");
            writer.append(",");
            writer.append(Integer.toString(buy));
            writer.append(System.lineSeparator());

            writer.append("result");
            writer.append(",");
            writer.append(Integer.toString(supply - buy));
            writer.append(System.lineSeparator());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}

