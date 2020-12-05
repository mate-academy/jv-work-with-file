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

        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            String line = reader.readLine();
            int supply = 0;
            int buy = 0;
            while (line != null) {
                String[] lineData = line.split(",");
                if (lineData[0].equals("supply")) {
                    supply += Integer.parseInt(lineData[1]);
                } else {
                    buy += Integer.parseInt(lineData[1]);
                }
                line = reader.readLine();
            }
            writeReportToFile(supply,buy,toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from file", e);
        }
    }
    public void writeReportToFile(int supply, int buy, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fileTo = new File(toFileName);
        stringBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            String output = stringBuilder.toString();
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file", e);
        }
    }
}
