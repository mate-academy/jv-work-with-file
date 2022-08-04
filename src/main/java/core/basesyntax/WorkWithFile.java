package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_СOMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File outputFile = new File(fromFileName);
        int totalBuy = 0;
        int totalSupply = 0;
        totalSupply = getSupplyAndBuyFromFile(outputFile)[0];
        totalBuy = getSupplyAndBuyFromFile(outputFile)[1];
        File inputFile = new File(toFileName);
        writeToFile(totalSupply, totalBuy, inputFile);
    }

    private static int[] getSupplyAndBuyFromFile(File fileReader) {
        try {
            List<String> lines = Files.readAllLines(fileReader.toPath());
            return countStatistic(lines);

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }

    private static int[] countStatistic(List<String> lines) {
        int[] statistic = new int[2];
        for (String line : lines) {
            String[] split = line.split(",");
            switch (split[OPERATION_INDEX]) {
                case STRING_SUPPLY :
                    statistic[0] += Integer.parseInt(split[AMOUNT_INDEX]);
                    break;
                case STRING_BUY :
                    statistic[1] += Integer.parseInt(split[AMOUNT_INDEX]);
                    break;
                default:
            }
        }
        return statistic;
    }

    private void writeToFile(int supply, int buy, File fileWriter) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriter))) {
            bufferedWriter.write(STRING_SUPPLY + STRING_СOMA + supply);
            bufferedWriter.write(System.lineSeparator() + STRING_BUY + STRING_СOMA + buy);
            bufferedWriter.write(System.lineSeparator() + "result" + STRING_СOMA + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file", e);
        }
    }
}
