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
    private static final char SEPARATOR = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        Statistic statistic = readStatistic(fromFileName);
        writeStatistic(statistic, toFileName);
    }

    private Statistic readStatistic(String fromFileName) {
        Statistic statistic = new Statistic();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String row = bufferedReader.readLine();
            while (row != null) {
                if (row.startsWith(SUPPLY)) {
                    String amountColumn = row.substring(SUPPLY.length() + 1);
                    statistic.addSupply(Integer.parseInt(amountColumn));
                } else if (row.startsWith(BUY)) {
                    String amountColumn = row.substring(BUY.length() + 1);
                    statistic.addBuy(Integer.parseInt(amountColumn));
                }
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't read the file!" + e);
        }
        return statistic;
    }

    private void writeStatistic(Statistic statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + SEPARATOR + statistic.getSupply());
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + SEPARATOR + statistic.getBuy());
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + SEPARATOR + statistic.getResult());
        } catch (IOException e) {
            System.out.println("Can't write to file!" + e);
        }
    }
}
