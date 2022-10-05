package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private int buyAmount;
    private String[] statistics;
    private int supplyAmount;
    private int totalAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        readStatistic(fromFileName);
        addStatistic();

        File report = new File(toFileName);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(report, true));
            for (String stat : statistics) {
                bufferedWriter.write(stat);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Cannot write data to file", e);
            }
        }
    }

    public void readStatistic(String fromFileName) {
        File file = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String inputData = bufferedReader.readLine();

            while (inputData != null) {
                if (inputData.startsWith(SUPPLY)) {
                    supplyAmount = supplyAmount
                            + Integer.parseInt(inputData.substring(inputData.indexOf(",") + 1));
                } else {
                    buyAmount = buyAmount
                            + Integer.parseInt(inputData.substring(inputData.indexOf(",") + 1));
                }
                inputData = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
        totalAmount = supplyAmount - buyAmount;
    }

    public void addStatistic() {
        statistics = new String[3];
        StringBuilder builder = new StringBuilder();
        statistics[0] = builder.append(SUPPLY)
                .append(",")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .toString();
        StringBuilder builder1 = new StringBuilder();
        statistics[1] = builder1.append(BUY)
                .append(",")
                .append(buyAmount)
                .append(System.lineSeparator())
                .toString();
        StringBuilder builder2 = new StringBuilder();
        statistics[2] = builder2.append(RESULT)
                .append(",")
                .append(totalAmount)
                .toString();
    }
}
