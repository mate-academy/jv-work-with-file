package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String text = readFromFile(fromFileName);
        String resultReport = createReport(text);
        writeToFile(toFileName, resultReport);
    }

    private String readFromFile(String fromFileName) {
        File fileRead = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileRead));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(COMMA);
                value = reader.readLine();
            }
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }

    private String createReport(String line) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] data = line.split(COMMA);
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                sumSupply = sumSupply + Integer.parseInt(data[i + 1]);
            }
            if (data[i].equals("buy")) {
                sumBuy = sumBuy + Integer.parseInt(data[i + 1]);
            }
        }
        int result = sumSupply - sumBuy;
        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName, String report) {
        File fileWrite = new File(toFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite));
            writer.write(report);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
