package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            int supply = 0;
            int buy = 0;
            while (line != null) {
                String[] lineData = line.split(",");
                if (lineData[0].equals("supply")) {
                    supply += Integer.parseInt(lineData[1]);
                } else if (lineData[0].equals("buy")) {
                    buy += Integer.parseInt(lineData[1]);
                }
                line = reader.readLine();
            }
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from file", e);
        }

        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            String output = stringBuilder.toString();
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file", e);
        }
    }
}
