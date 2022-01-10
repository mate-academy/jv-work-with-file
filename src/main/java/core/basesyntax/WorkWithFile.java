package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String[] data;

    public void getStatistic(String fromFileName, String toFileName) {
        data = readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    public String[] readFromFile(String fromFile) {
        File file = new File(fromFile);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            data = stringBuilder.toString().split("[;\n]");
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file ", e);
        }
    }

    public void writeToFile(String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            int sumBuy = 0;
            int sumSupply = 0;
            for (String datum : data) {
                String[] split = datum.split(",");
                if (split[0].equals("buy")) {
                    sumBuy = sumBuy
                            + Integer.parseInt(split[1].substring(0, split[1].length() - 1));
                }
                if (split[0].equals("supply")) {
                    sumSupply = sumSupply
                            + Integer.parseInt(split[1].substring(0, split[1].length() - 1));
                }
            }
            String report = "supply," + sumSupply + System.lineSeparator()
                    + "buy," + sumBuy + System.lineSeparator()
                    + "result," + (sumSupply - sumBuy);
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Report didn't create");
        }
    }
}
