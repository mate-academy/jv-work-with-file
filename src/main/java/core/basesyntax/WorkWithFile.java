package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String WHITESPACE = " ";
    private static final String COMA = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readedData = readFile(fromFileName);
        String readyReport = generateReport(readedData);
        writeToFile(readyReport, toFileName);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder readedData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String words = bufferedReader.readLine();
            while (words != null) {
                readedData.append(words).append(" ");
                words = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return readedData.toString().split(WHITESPACE);
    }

    private String generateReport(String[] readedData) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (String data : readedData) {
            String[] reportData = data.split(COMA);
            if (reportData[INDEX_ZERO].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(reportData[INDEX_ONE]);
            } else if (reportData[INDEX_ZERO].equals(BUY)) {
                sumBuy += Integer.parseInt(reportData[INDEX_ONE]);
            }
        }
        int result = sumSupply - sumBuy;

        return SUPPLY + COMA + sumSupply + "\n" + BUY + COMA + sumBuy + "\n"
                + RESULT + COMA + result;
    }

    private void writeToFile(String readyReport, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            {
                bufferedWriter.write(readyReport);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
