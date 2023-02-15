package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String NAME_OF_SUPPLY = "supply";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String REGEX_SPLIT = "[,\\s]";
    public static final String STATISTIC_PATTERN = "supply,%d%n"
            + "buy,%d%n"
            + "result,%d%n";
    public static final int INITIAL_SUPPLY_AMOUNT = 0;
    public static final int INITIAL_BUY_AMOUNT = 0;
    private String toWrite;
    private int supplyAmount;
    private int buyAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        read(fromFileName);
        calculateStatistic();
        write(toFileName);
    }

    private void read(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            supplyAmount = INITIAL_SUPPLY_AMOUNT;
            buyAmount = INITIAL_BUY_AMOUNT;
            while (value != null) {
                String[] values = value.split(REGEX_SPLIT);
                switch (values[OPERATION_INDEX]) {
                    case NAME_OF_SUPPLY:
                        supplyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                        break;
                    default:
                        buyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                        break;
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file" + fromFileName, e);
        }
    }

    private void calculateStatistic() {
        int result = supplyAmount - buyAmount;
        toWrite = String.format(STATISTIC_PATTERN, supplyAmount, buyAmount, result);
    }

    private void write(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(toWrite);
        } catch (IOException d) {
            throw new RuntimeException("Cannot write data to file" + toFileName, d);
        }
    }
}
