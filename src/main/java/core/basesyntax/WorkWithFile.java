package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFIle(toFileName, readFromFIle(fromFileName));
    }

    private int[] readFromFIle(String fromFile) {
        int[] sales = new int[2];
        try (BufferedReader readFile = new BufferedReader(new FileReader(fromFile))) {
            String lineText = readFile.readLine();
            while (lineText != null) {
                String[] line = lineText.split(",");
                if (line[0].equals(SUPPLY)) {
                    sales[0] += Integer.parseInt(line[1]);
                } else {
                    sales[1] += Integer.parseInt(line[1]);
                }
                lineText = readFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found - " + fromFile, e);
        }
        return sales;
    }

    private void writeToFIle(String toFile, int[] sales) {
        String reportToFile = new StringBuilder().append("supply,").append(sales[0])
                .append(System.lineSeparator()).append("buy,").append(sales[1])
                .append(System.lineSeparator()).append("result,")
                .append(sales[0] - sales[1]).toString();
        try (BufferedWriter writeFile = new BufferedWriter(new FileWriter(toFile + ""))) {
            writeFile.write(reportToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file!" + toFile, e);
        }
    }
}
