package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final StatisticCalculator statisticCalculator = new StatisticCalculator();

    public void getStatistic(String fromFileName, String toFileName) {
        statisticCalculator.getCalculations(fromFileName);

        String text = generateString(statisticCalculator.getSupply(), statisticCalculator.getBuy());

        writeInFile(text, toFileName);

        statisticCalculator.setBuy(0);
        statisticCalculator.setSupply(0);
    }

    private String generateString(int supply, int buy) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeInFile(String text, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write " + toFileName, e);
        }
    }
}
