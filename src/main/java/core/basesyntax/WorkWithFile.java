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
        int[] statistic = readFromFile(outputFile);
        int totalBuy = statistic[1];
        int totalSupply = statistic[0];
        File inputFile = new File(toFileName);
        writeToFile(totalSupply, totalBuy, inputFile);
    }

    private static int[] readFromFile(File inputFile) {
        try {
            List<String> lines = Files.readAllLines(inputFile.toPath());
            return countStatistic(lines);

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + inputFile.getName(), e);
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

    private void writeToFile(int supply, int buy, File outputFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(STRING_SUPPLY);
            stringBuilder.append(STRING_СOMA);
            stringBuilder.append(supply);
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(STRING_BUY);
            stringBuilder.append(STRING_СOMA);
            stringBuilder.append(buy);
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("result");
            stringBuilder.append(STRING_СOMA);
            stringBuilder.append((supply - buy));
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + outputFile.getName(), e);
        }
    }
}
