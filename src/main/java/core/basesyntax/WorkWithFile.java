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

    public static String readFile(String fromFileName) {
        File fl = new File(fromFileName);
        try {
            BufferedReader brd = new BufferedReader(new FileReader(fl));
            StringBuilder strBuilder = new StringBuilder();
            String value = brd.readLine();
            while (value != null) {
                strBuilder.append(value).append(",");
                value = brd.readLine();
            }
            return strBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private static String createReport(String value) {
        int supplyResult = 0;
        int buyResult = 0;
        int result = 0;
        String[] data = value.split(",");
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supplyResult += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals("buy")) {
                buyResult += Integer.parseInt(data[i + 1]);
            }
            result = supplyResult - buyResult;
        }
        StringBuilder resultBld = new StringBuilder();
        StringBuilder report = resultBld.append("supply").append(",").append(supplyResult)
                .append(System.lineSeparator())
                .append("buy").append(",").append(buyResult)
                .append(System.lineSeparator())
                .append("result").append(",").append(result);
        return String.valueOf(report);
    }

    private static void fileWrite(String toFileName, String report) {
        File fl = new File(toFileName);
        try {
            BufferedWriter bwrt = new BufferedWriter(new FileWriter(fl));
            bwrt.write(String.valueOf(report));
            bwrt.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
