package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readeFile(fromFileName);
        String reportSend = report(data);
        writeToFile(toFileName, reportSend);
    }

    private String readeFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String row = reader.readLine();
            while (row != null) {
                builder.append(row).append(",");
                row = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString();
    }

    private String report(String textReaded) {
        int supplyCount = 0;
        int buyCount = 0;
        StringBuilder concate = new StringBuilder();
        String[] array = textReaded.split(",");
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("supply")) {
                supplyCount += Integer.parseInt(array[i + 1]);
            }
            if (array[i].equals("buy")) {
                buyCount += Integer.parseInt(array[i + 1]);
            }
        }
        return (concate.append("supply,").append(supplyCount).append(System.lineSeparator())
                .append("buy,").append(buyCount).append(System.lineSeparator())
                .append("result,").append(supplyCount - buyCount)).toString();
    }

    private void writeToFile(String toFileName, String reportPrint) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(toFileName, true))) {
            write.write(reportPrint);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
