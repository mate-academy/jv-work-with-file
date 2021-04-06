package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String KEYWORD_SUPPLY = "supply";
    private static final String KEYWORD_BUY = "buy";
    private static final String KEYWORD_RESULT = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] statistic = line.split(REGEX);
                if (statistic[OPERATION_TYPE_INDEX].equals(KEYWORD_SUPPLY)) {
                    sumOfSupply += Integer.parseInt(statistic[AMOUNT_INDEX]);
                } else if (statistic[OPERATION_TYPE_INDEX].equals(KEYWORD_BUY)) {
                    sumOfBuy += Integer.parseInt(statistic[AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file!" + fromFileName, e);
        }
        writeToFile(toFileName, getReport(sumOfSupply, sumOfBuy));
    }

    private String getReport(int sumOfSupply, int sumOfBuy) {
        StringBuilder report = new StringBuilder();
        report.append(KEYWORD_SUPPLY)
                .append(REGEX)
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append(KEYWORD_BUY)
                .append(REGEX)
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append(KEYWORD_RESULT)
                .append(REGEX)
                .append(sumOfSupply - sumOfBuy);
        return report.toString();
    }

    private void writeToFile(String fileName, String output) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file " + fileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName, e);
        }
    }
}
