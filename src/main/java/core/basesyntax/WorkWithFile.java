package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    public int[] readFile(String fromFileName) {
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
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file" + fromFileName, e);
        }
        return new int[] {supply, buy};
    }

    public String createReport(int[] data) {
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(data[0]).append(System.lineSeparator())
                .append("buy,").append(data[1]).append(System.lineSeparator())
                .append("result,").append(data[0] - data[1]);
        return result.toString();
    }

    public void writeToFile(String report, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName, e);
        }
    }
}

