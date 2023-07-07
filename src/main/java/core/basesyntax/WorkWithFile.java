package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String STRING_SEPARATOR = ",";
    private static final String SHOP_SUPPLIER = "supply";
    private static final String SHOP_BUY = "buy";
    private static final String RESULT_COUNT = "result";
    private static final int ARGUMENT = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String storetable = readDataFromFile(fromFileName);
        String report = calculateData(storetable);
        writeNewData(report, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String calculateData(String storetable) {
        String[] datastore = storetable.split(System.lineSeparator());
        int sumSupply = 0;
        int sumBuy = 0;
        for (String data : datastore) {
            String[] store = data.split(STRING_SEPARATOR);
            if (store[ARGUMENT].equals("supply")) {
                sumSupply += Integer.parseInt(store[1]);
            } else {
                sumBuy += Integer.parseInt(store[1]);
            }
        }
        return createReportFile(sumSupply, sumBuy);
    }

    private String createReportFile(int sumSupply, int sumBuy) {
        int result = sumSupply - sumBuy;
        StringBuilder reportBuilder = new StringBuilder(SHOP_SUPPLIER).append(STRING_SEPARATOR);
        reportBuilder.append(sumSupply)
                .append(System.lineSeparator())
                .append(SHOP_BUY)
                .append(STRING_SEPARATOR)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT_COUNT)
                .append(STRING_SEPARATOR)
                .append(result);
        return reportBuilder.toString();
    }

    private void writeNewData(String createReport, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName + e);
        }
    }
}
