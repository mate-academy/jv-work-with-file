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
    private static final String DELIMITER = " ";
    private static String resultString;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        String data = readFile(fromFileName);
        String report = createReport(data);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while (bufferedReader.ready()) {
                builder.append(DELIMITER);
                builder.append(bufferedReader.readLine());
            }
            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String report) {
        int intSupply = 0;
        int intBuy = 0;
        int result;
        String[] innerArray = report.split(DELIMITER);
        for (String s : innerArray) {

            String[] inside = s.split(COMMA);

            if (inside[0].equals(SUPPLY)) {
                intSupply += Integer.parseInt(inside[ONE]);
            }
            if (inside[0].equals(BUY)) {
                intBuy += Integer.parseInt(inside[ONE]);
            }
            result = intSupply - intBuy;
            resultString = SUPPLY + COMMA + intSupply + System.lineSeparator()
                    + BUY + COMMA + intBuy + System.lineSeparator()
                    + "result" + COMMA + result;
        }
        return resultString;
    }

    private void writeFile(String res, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(res);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}
