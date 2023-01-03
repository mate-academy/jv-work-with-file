package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String CSV_SEPARATOR = ",";
    public static final int OPERATION_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeStatisticToFile(readStatisticFromFile(fromFileName), toFileName);
    }

    private Statistic readStatisticFromFile(String fromFileName) {
        Statistic statistic = new Statistic();
        try (BufferedReader BufferedReader = new BufferedReader(
                        new FileReader(new File(fromFileName))
        )) {
            String record = BufferedReader.readLine();

            while (record != null) {
                String[] splitRecord = record.split(CSV_SEPARATOR);
                if (splitRecord[OPERATION_INDEX].equals(StatisticField.supply.name())) {
                    statistic.addSupply(Integer.parseInt(splitRecord[VALUE_INDEX]));
                }
                if (splitRecord[OPERATION_INDEX].equals(StatisticField.buy.name())) {
                    statistic.addBuy(Integer.parseInt(splitRecord[VALUE_INDEX]));
                }
                record = BufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File for reading: " + fromFileName + "does not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }

        return statistic;
    }

    private void writeStatisticToFile(Statistic statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(new File(toFileName))
        )) {
            String content = new StringBuilder()
                          .append(StatisticField.supply.name())
                          .append(CSV_SEPARATOR)
                          .append(statistic.getSupply())
                          .append(System.lineSeparator())
                          .append(StatisticField.buy.name())
                          .append(CSV_SEPARATOR)
                          .append(statistic.getBuy())
                          .append(System.lineSeparator())
                          .append(StatisticField.result.name())
                          .append(CSV_SEPARATOR)
                          .append(statistic.getResult())
                          .toString();

            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
}
