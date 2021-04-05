package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "b";
    private static final String SUPPLY = "s";
    private static final String WHITE_SPACE = " ";

    protected static int[] readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        int countBuy = 0;
        int countSupply = 0;
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(WHITE_SPACE);
                for (String word : words) {
                    if (word.startsWith(BUY)) {
                        countBuy += Integer.parseInt(word.substring(4));
                    } else if (word.startsWith(SUPPLY)) {
                        countSupply += Integer.parseInt(word.substring(7));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        return new int[]{countBuy, countSupply};
    }

    protected static String createReport(String fromFileName) {
        int[] valueBuySupply = readFromFile(fromFileName);
        valueBuySupply[0] /= 2;
        valueBuySupply[1] /= 2;
        int result = valueBuySupply[1] - valueBuySupply[0];
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(valueBuySupply[1]).append(System.lineSeparator())
                     .append("buy,").append(valueBuySupply[0]).append(System.lineSeparator())
                     .append("result,").append(result);
        return reportBuilder.toString();
    }

    protected static void getStatistic(String fromFileName, String toFileName) {
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
