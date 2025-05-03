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
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data from the file " + toFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        String[] dataFromMarket = dataFromFile.split(COMA);
        int sumSupple = Integer.parseInt(dataFromMarket[0]);
        int sumBuy = Integer.parseInt(dataFromMarket[1]);
        StringBuilder stringBuilder = new StringBuilder()
                .append(SUPPLY).append(COMA).append(sumSupple)
                .append(System.lineSeparator())
                .append(BUY).append(COMA).append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMA).append(sumSupple - sumBuy);
        return stringBuilder.toString();
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int sumSupply = 0;
            int sumBuy = 0;
            String infoLine = reader.readLine();
            while (infoLine != null) {
                String[] marketDataArrays = infoLine.split(COMA);
                String dataType = marketDataArrays[0];
                int dataPlace = Integer.parseInt(marketDataArrays[1]);
                if (dataType.equals(SUPPLY)) {
                    sumSupply += dataPlace;
                } else if (dataType.equals(BUY)) {
                    sumBuy += dataPlace;
                }
                infoLine = reader.readLine();
            }
            return sumSupply + COMA + sumBuy;
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file  " + fromFileName, e);
        }
    }
}
