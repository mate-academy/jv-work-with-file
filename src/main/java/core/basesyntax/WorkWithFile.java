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
    public static final String BUY_NANE = "buy";
    public static final String SUPPLY_NANE = "supply";
    public static final String RESULT_NANE = "result";

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
                if (splitRecord[OPERATION_INDEX].equals(SUPPLY_NANE)) {
                    statistic.addSupply(Integer.parseInt(splitRecord[VALUE_INDEX]));
                }
                if (splitRecord[OPERATION_INDEX].equals(BUY_NANE)) {
                    statistic.addBuy(Integer.parseInt(splitRecord[VALUE_INDEX]));
                }
                record = BufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File for reading: " + fromFileName + "does not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException when work with " + fromFileName, e);
        }

        return statistic;
    }

    private void writeStatisticToFile(Statistic statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(new File(toFileName))
        )) {
            String content = new StringBuilder()
                          .append(SUPPLY_NANE)
                          .append(CSV_SEPARATOR)
                          .append(statistic.getSupply())
                          .append(System.lineSeparator())
                          .append(BUY_NANE)
                          .append(CSV_SEPARATOR)
                          .append(statistic.getBuy())
                          .append(System.lineSeparator())
                          .append(RESULT_NANE)
                          .append(CSV_SEPARATOR)
                          .append(statistic.getResult())
                          .toString();

            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("IOException when write to " + toFileName, e);
        }
    }
}
