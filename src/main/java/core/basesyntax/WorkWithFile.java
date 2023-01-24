package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
                } else {
                    supply += Integer.parseInt(data[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cant read from file", e);
        } catch (IOException e) {
            throw new RuntimeException("Cant read line", e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
