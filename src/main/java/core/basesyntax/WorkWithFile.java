package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    protected static String[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> statistic = null;
        try {
            statistic = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        String[] array = new String[statistic.size()];
        array = statistic.toArray(array);
        return array;
    }

    protected static String createReport(String fromFileName) {
        String[] statistic = readFromFile(fromFileName);
        int countBuy = 0;
        int countSupply = 0;

        for (String word : statistic) {
            if (word.startsWith("b")) {
                countBuy += Integer.parseInt(word.substring(4));
            } else if (word.startsWith("s")) {
                countSupply += Integer.parseInt(word.substring(7));
            }
        }
        countBuy /= 2;
        countSupply /= 2;
        int result = countSupply - countBuy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(countSupply).append(System.lineSeparator())
                     .append("buy,").append(countBuy).append(System.lineSeparator())
                     .append("result,").append(result);
        return reportBuilder.toString();
    }

    public static void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file " + toFileName, e);
        }
        String report = createReport(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file " + toFileName, e);
        }
    }
}
