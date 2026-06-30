package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Statistic statistic = readFile(fromFileName);
        writeReport(statistic, toFileName);
    }

    private Statistic readFile(String fileName) {
        Statistic statistic = new Statistic();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                calculateStatistics(line, statistic);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }

        return statistic;
    }

    private void calculateStatistics(String line, Statistic statistic) {
        String[] elements = line.split(",");
        String operation = elements[0];
        int amount = Integer.parseInt(elements[1]);

        switch (operation) {
            case "supply":
                statistic.supply += amount;
                break;
            case "buy":
                statistic.buy += amount;
                break;
            default:
                throw new RuntimeException("Unknown operation: " + operation);
        }
    }

    private void writeReport(Statistic statistic, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            writer.write("supply," + statistic.supply);
            writer.newLine();

            writer.write("buy," + statistic.buy);
            writer.newLine();

            writer.write("result," + (statistic.supply - statistic.buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }

    private static class Statistic {
        private int supply;
        private int buy;
    }
}