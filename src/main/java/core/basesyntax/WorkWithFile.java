package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, createReport(readFile(fromFileName)));
    }

    private List<String> readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                data.add(value);
                data.add(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return data;
    }

    private String createReport(List<String> data) {
        int sumSupply = 0;
        int sumBuy = 0;
        final int Result_Numbers;
        final int Comparison_Index = 0;
        final int Index_With_A_Number = 1;

        for (String datum : data) {
            if (datum.charAt(Comparison_Index) == 's') {
                String[] local1 = datum.split("\\D+");
                if (local1.length == 0) {
                    continue;
                }
                int local = Integer.parseInt(local1[Index_With_A_Number]);
                sumSupply += local;
            } else {
                String[] local1 = datum.split("\\D+");
                if (local1.length == 0) {
                    continue;
                }
                int local = Integer.parseInt(local1[Index_With_A_Number]);
                sumBuy += local;
            }
        }
        Result_Numbers = sumSupply - sumBuy;

        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + Result_Numbers;
    }

    private void writeFile(String toFileName, String text) {
        File finalFile = new File(toFileName);

        try (BufferedWriter writerToFinalFile = new BufferedWriter(
                new FileWriter(finalFile, true))) {
            writerToFinalFile.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
