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

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String words = bufferedReader.readLine();
            while (words != null) {
                builder.append(words).append(" ");
                words = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] sortedReport = builder.toString().split(WHITESPACE);
        int sumSupply = 0;
        int sumBuy = 0;
        for (String data : sortedReport) {
            String[] reportData = data.split(COMA);
            if (reportData[0].equals(SUPPLY)) {
                sumSupply += Integer.parseInt(reportData[1]);
            } else if (reportData[0].equals(BUY)) {
                sumBuy += Integer.parseInt(reportData[1]);
            }
        }
        int result = sumSupply - sumBuy;
        String[] reportFinal = new String[3];
        reportFinal[0] = SUPPLY + COMA + sumSupply;
        reportFinal[1] = BUY + COMA + sumBuy;
        reportFinal[2] = RESULT + COMA + result;

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (String data : reportFinal) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
