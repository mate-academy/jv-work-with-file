package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WorkWithFile {
    public static final int LABEL_COLUMN_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = report(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String report(String dataFromFile) {
        String[] records = dataFromFile.split(",");
        int buyTotal = 0;
        int supplyTotal = 0;
        for (int i = 0; i < records.length; ++i) {
            if (records[i].equals("buy")) {
                buyTotal += Integer.parseInt(records[i + 1]);
            } else if (records[i].equals("supply")) {
                supplyTotal += Integer.parseInt(records[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator())
                .append("buy,").append(buyTotal).append(System.lineSeparator())
                .append("result,").append(supplyTotal - buyTotal).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (PrintWriter out = new PrintWriter(toFileName)) {
            out.println(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
