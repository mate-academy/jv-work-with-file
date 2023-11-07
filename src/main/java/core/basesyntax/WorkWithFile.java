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
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_NAME = 0;
    private static final int OPERATION_VALUE = 1;

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
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String report) {
        int intSupply = 0;
        int intBuy = 0;
        int result;
        String[] arrayFromFile = report.split(DELIMITER);
        for (String operation : arrayFromFile) {

            String[] infoByOperation = operation.split(COMMA);

            if (infoByOperation[OPERATION_NAME].equals(SUPPLY)) {
                intSupply += Integer.parseInt(infoByOperation[OPERATION_VALUE]);
            }
            if (infoByOperation[OPERATION_NAME].equals(BUY)) {
                intBuy += Integer.parseInt(infoByOperation[OPERATION_VALUE]);
            }
            result = intSupply - intBuy;
            resultString = SUPPLY + COMMA + intSupply + LINE_SEPARATOR
                    + BUY + COMMA + intBuy + LINE_SEPARATOR
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
