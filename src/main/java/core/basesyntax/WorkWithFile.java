package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String value = readFile(fromFileName);
        String report = createReport(value);
        fileWrite(toFileName, report);
    }

    private static String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createReport(String value) {
        int supplyTotal = 0;
        int buyTotal = 0;
        int result = 0;
        String[] data = value.split(",");
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supplyTotal += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals("buy")) {
                buyTotal += Integer.parseInt(data[i + 1]);
            }
            result = supplyTotal - buyTotal;
        }
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder report = resultBuilder.append("supply").append(",").append(supplyTotal)
                .append(System.lineSeparator())
                .append("buy").append(",").append(buyTotal)
                .append(System.lineSeparator())
                .append("result").append(",").append(result);
        return String.valueOf(report);
    }

    private static void fileWrite(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(String.valueOf(report));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
