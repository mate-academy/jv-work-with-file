package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String rows = readFromFile(fromFileName);
        String report = createReport(rows);
        writeReportToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return builder.toString();
    }

    private String createReport(String rows) {
        String[] split = rows.split("\\s+");
        StringBuilder stringBuilder = new StringBuilder();
        int supplyInt = 0;
        int buyInt = 0;
        String buyConstant = "buy";
        String supplyConstant = "supply";
        for (String strings: split) {
            String[]record = strings.split(",");
            String count = record[1];
            if (record[0].equals(supplyConstant)) {
                supplyInt += Integer.parseInt(count);
            }
            if (record[0].equals(buyConstant)) {
                buyInt += Integer.parseInt(count);
            }
        }
        String resultTotal = "result";
        stringBuilder.append(supplyConstant).append(",").append(supplyInt).append("\n");
        stringBuilder.append(buyConstant).append(",").append(buyInt).append("\n");
        stringBuilder.append(resultTotal).append(",").append((supplyInt - buyInt)).append("\n");
        return stringBuilder.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        File fileTo = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
