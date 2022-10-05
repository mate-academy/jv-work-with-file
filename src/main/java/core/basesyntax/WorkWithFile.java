package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String BUY = "buy";
    private static final String COMMA_SEPARATOR = ",";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private int buyAmount;
    private String[] statistics;
    private int supplyAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        readStatistic(fromFileName);
        calculateStatistic();
        createReport(toFileName);
    }

    public void readStatistic(String fromFileName) {
        File file = new File(fromFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String input = bufferedReader.readLine();

            while (input != null) {
                if (input.startsWith(SUPPLY)) {
                    supplyAmount = supplyAmount
                            + Integer.parseInt(input.substring(input.indexOf(COMMA_SEPARATOR) + 1));
                } else {
                    buyAmount = buyAmount
                            + Integer.parseInt(input.substring(input.indexOf(COMMA_SEPARATOR) + 1));
                }
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
    }

    public void calculateStatistic() {
        statistics = new String[3];
        StringBuilder builder = new StringBuilder();
        statistics[0] = builder.append(SUPPLY)
                .append(COMMA_SEPARATOR)
                .append(supplyAmount)
                .append(System.lineSeparator())
                .toString();
        StringBuilder builder1 = new StringBuilder();
        statistics[1] = builder1.append(BUY)
                .append(COMMA_SEPARATOR)
                .append(buyAmount)
                .append(System.lineSeparator())
                .toString();
        StringBuilder builder2 = new StringBuilder();
        statistics[2] = builder2.append(RESULT)
                .append(COMMA_SEPARATOR)
                .append(supplyAmount - buyAmount)
                .toString();
    }

    public void createReport(String toFileName) {
        File report = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report, true))) {
            for (String stat : statistics) {
                bufferedWriter.write(stat);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file", e);
        }
    }
}
