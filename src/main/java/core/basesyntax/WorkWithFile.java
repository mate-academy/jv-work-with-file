package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final StatisticParser statisticParser = new StatisticParser();

    public void getStatistic(String fromFileName, String toFileName) {
        try (Csv file = readFile(fromFileName)) {
            Statistic statistic = statisticParser.parseCsv(file);
            String summary = statistic.stringSummary(Csv.DELIMITER);
            writeStatistic(summary, toFileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!" + e);
        } catch (Exception e) {
            System.out.println("Can't close the file!" + e);
        }
    }

    private Csv readFile(String fromFileName) throws FileNotFoundException {
        return new Csv(new FileReader(fromFileName));
    }

    private void writeStatistic(String statisticSummary, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statisticSummary);
        } catch (IOException e) {
            System.out.println("Can't write to file!" + e);
        }
    }
}
