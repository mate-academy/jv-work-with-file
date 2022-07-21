package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int sumBuy = 0;
        int sumSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splittedLine = line.split(SEPARATOR);
                if (splittedLine[INDEX_OF_ACTION].equals("buy")) {
                    sumBuy += Integer.parseInt(splittedLine[INDEX_OF_AMOUNT]);
                }
                if (splittedLine[INDEX_OF_ACTION].equals("supply")) {
                    sumSupply += Integer.parseInt(splittedLine[INDEX_OF_AMOUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File" + fromFileName + "cannot be read", e);
        }
        int result = sumSupply - sumBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(stringBuilder.append("supply")
                    .append(SEPARATOR).append(sumSupply).append(System.lineSeparator())
                    .append("buy").append(SEPARATOR).append(sumBuy)
                    .append(System.lineSeparator()).append("result")
                    .append(SEPARATOR).append(result)));
        } catch (IOException e) {
            throw new RuntimeException("File" + toFileName + "cannot be write", e);
        }
    }
}
