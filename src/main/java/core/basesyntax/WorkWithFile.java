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
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readedData = readFile(fromFileName);
        String[] readyReport = generateReport(readedData);
        writeToFile(readyReport, toFileName);
    }

    public String[] readFile(String fromFileName) {
        StringBuilder readedData = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
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

    public String[] generateReport(String[] readedData) {
        int sumSupply = ZERO;
        int sumBuy = ZERO;
        for (String data : readedData) {
            String[] reportData = data.split(COMA);
            if (reportData[ZERO].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(reportData[ONE]);
            } else if (reportData[ZERO].equals(BUY)) {
                sumBuy += Integer.parseInt(reportData[1]);
            }
        }
        int result = sumSupply - sumBuy;
        String[] reportFinal = new String[3];
        reportFinal[ZERO] = SUPPLY + COMA + sumSupply;
        reportFinal[ONE] = BUY + COMA + sumBuy;
        reportFinal[TWO] = RESULT + COMA + result;
        return reportFinal;
    }

    public void writeToFile(String[] readyReport, String toFileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (String data : readyReport) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
