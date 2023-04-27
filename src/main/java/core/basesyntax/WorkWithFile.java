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

    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultSupplyBuy = readFile(fromFileName);
        int sumSupply = calculateSum(resultSupplyBuy, SUPPLY);
        int sumBuy = calculateSum(resultSupplyBuy, BUY);
        int resultInt = sumSupply - sumBuy;
        writeToFile(toFileName, sumSupply, sumBuy, resultInt);
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString().split("\r\n");
    }

    public int calculateSum(String[] array, String type) {
        StringBuilder builder = new StringBuilder();
        for (String test : array) {
            if (test.startsWith(type)) {
                String[] parts = test.split(",");
                builder.append(parts[1]).append(" ");
            }
        }
        String[] arrayString = builder.toString().split(" ");
        int sum = 0;
        for (String testString : arrayString) {
            sum += Integer.parseInt(testString);
        }
        return sum;
    }

    private void writeToFile(String toFileName, int sumSupply, int sumBuy, int resultInt) {
        File fileResult = new File(toFileName);
        StringBuilder stringBuilder;
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new BufferedWriter(new FileWriter(fileResult, false)))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY)
                    .append(COMMA)
                    .append(sumSupply)
                    .append("\r\n")
                    .append(BUY)
                    .append(COMMA)
                    .append(sumBuy)
                    .append("\r\n")
                    .append(RESULT)
                    .append(COMMA)
                    .append(resultInt);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
