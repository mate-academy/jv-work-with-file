package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int sumSupply = 0;
            int sumBuy = 0;
            String infoLine = reader.readLine();
            while (infoLine != null) {
                String[] marketDataArrays = infoLine.split(COMA);
                if (marketDataArrays[0].equals(SUPPLY)) {
                    sumSupply += Integer.parseInt(marketDataArrays[1]);
                } else if (marketDataArrays[0].equals(BUY)) {
                    sumBuy += Integer.parseInt(marketDataArrays[1]);
                }
                infoLine = reader.readLine();
            }
            writeFile(toFileName, sumSupply, sumBuy);
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private void writeFile(String toFileName, int supple, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(generateReport(supple, buy)));
        } catch (Exception e) {
            throw new RuntimeException("Can't write data from the file " + toFileName, e);
        }
    }

    private StringBuilder generateReport(int sumSupple, int sumBuy) {
        return new StringBuilder().append(SUPPLY).append(COMA).append(sumSupple)
                .append(System.lineSeparator()).append(BUY).append(COMA)
                .append(sumBuy).append(System.lineSeparator()).append(RESULT)
                .append(COMA).append(sumSupple - sumBuy);
    }
}
