package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultSupplyBuy = readFile(fromFileName);
        int sumSupply = calculateSum(resultSupplyBuy, SUPPLY);
        int sumBuy = calculateSum(resultSupplyBuy, BUY);
        writeToFile(toFileName, sumSupply, sumBuy, sumSupply - sumBuy);
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private int calculateSum(String[] arraySupplyBuy, String typeSupplyBuy) {
        StringBuilder builder = new StringBuilder();
        for (String test : arraySupplyBuy) {
            if (test.startsWith(typeSupplyBuy)) {
                String[] parts = test.split(COMMA);
                builder.append(parts[1]).append(SPACE);
            }
        }
        String[] arrayString = builder.toString().split(SPACE);
        int sum = 0;
        for (String testString : arrayString) {
            sum += Integer.parseInt(testString);
        }
        return sum;
    }

    private void writeToFile(String toFileName, int sumSupply, int sumBuy, int resultInt) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new BufferedWriter(new FileWriter(toFileName, false)))) {
            bufferedWriter.write(createReport(sumSupply, sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String createReport(int sumSupply, int sumBuy) {
        return new StringBuilder().append(SUPPLY)
                .append(COMMA)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(sumSupply - sumBuy).toString();
    }
}
