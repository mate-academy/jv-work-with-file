package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static String res;
    private String report;
    private final StringBuilder builder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {

        readFile(fromFileName);
        createReport(report);
        writeFile(res,toFileName);
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
        int intSupply = 0;
        int intBuy = 0;
        int result;
        String[] innerArray = report.split(" ");
        for (String s : innerArray) {

            String[] inside = s.split(COMMA);

            if (inside[0].equals(SUPPLY)) {
                intSupply += Integer.parseInt(inside[1]);
            }
            if (inside[0].equals(BUY)) {
                intBuy += Integer.parseInt(inside[1]);
            }
            result = intSupply - intBuy;
            res = SUPPLY + COMMA + intSupply + System.lineSeparator()
                    + BUY + COMMA + intBuy + System.lineSeparator()
                    + "result" + COMMA + result;
        }
    }

    private void writeFile(String res,String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(res);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
