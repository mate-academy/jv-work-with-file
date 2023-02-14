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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String strings = bufferedReader.readLine();
            while (strings != null) {
                String[] data = strings.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
                }
                strings = bufferedReader.readLine();
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
                StringBuilder result = new StringBuilder();
                result.append("supply,").append(supply).append(System.lineSeparator())
                        .append("buy,").append(buy).append(System.lineSeparator())
                        .append("result,").append(supply - buy);
                writer.write(result.toString());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }
}
