package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String stringSupply = "supply";
    private static final String stringBuy = "buy";
    private static final char ch = ',';
    private static int intSupply = 0;
    private static int intBuy = 0;
    private static int result = 0;

    private String report;
    private final StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        createReport(report);
        writeFile(toFileName);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while (bufferedReader.ready()) {
                builder.append(" ");
                builder.append(bufferedReader.readLine());
            }
            System.out.println(builder);
            report = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
    }

    private void createReport(String report) {
        intSupply = 0;
        intBuy = 0;
        String[] innerArray = report.split(" ");
        for (String s : innerArray) {
            String[] inside = s.split(",");

            if (inside[0].equals(stringSupply)) {
                intSupply += Integer.parseInt(inside[1]);
            }
            if (inside[0].equals(stringBuy)) {
                intBuy += Integer.parseInt(inside[1]);
            }
        }
        result = intSupply - intBuy;

    }

    private void writeFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringSupply + ch + intSupply);
            bufferedWriter.newLine();
            bufferedWriter.write(stringBuy + ch + intBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result" + ch + result);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
