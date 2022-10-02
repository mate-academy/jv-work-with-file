package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            String result = builder.toString();
            return result.split(",");
        } catch (IOException e) {
            System.out.println("Can`t read a file");
        }
        return new String[0];
    }

    public String createReport(String[] data) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder builder = new StringBuilder("supply,");
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supplyCount += Integer.parseInt(data[i + 1]);
            }
            if (data[i].equals("buy")) {
                buyCount += Integer.parseInt(data[i + 1]);
            }
        }
        builder.append(supplyCount).append("\nbuy,").append(buyCount)
                .append("\nresult,").append(supplyCount - buyCount);
        return builder.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Can`t write to a file");
        }
    }
}
