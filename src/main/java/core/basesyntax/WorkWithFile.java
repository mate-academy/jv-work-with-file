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
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Statistic result = readStatistic(fromFileName);
        String report = buildReport(result);
        writeReport(toFileName, report);
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }

    private String buildReport(Statistic result) {
        return SUPPLY
                + SEPARATOR
                + result.supply()
                + System.lineSeparator()
                + BUY
                + SEPARATOR + result.buy()
                + System.lineSeparator()
                + RESULT
                + SEPARATOR
                + result.getResult();
    }

    private static Statistic readStatistic(String fromFileName) {
        Statistic result = new Statistic(0,0);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(SEPARATOR);

                int amount = Integer.parseInt(parts[1].trim());

                if (SUPPLY.equals(parts[0].trim())) {
                    result = result.addSupply(amount);
                } else if (BUY.equals(parts[0].trim())) {
                    result = result.addBuy(amount);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return result;
    }

    private record Statistic(int supply, int buy) {
        public Statistic addSupply(int amount) {
            return new Statistic(supply + amount, buy);
        }

        public Statistic addBuy(int amount) {
            return new Statistic(supply, buy + amount);
        }

        public int getResult() {
            return supply - buy;
        }
    }
}
