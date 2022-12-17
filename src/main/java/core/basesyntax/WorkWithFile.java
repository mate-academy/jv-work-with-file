package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY = "supply,";
    static final String BUY = "buy,";
    static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromMyFile = new File(fromFileName);
        File toMyFile = new File(toFileName);
        try {
            FileReader fileReader = new FileReader(fromMyFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String report = createReport(bufferedReader);

            FileWriter fileWriter = new FileWriter(toMyFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            createFile(bufferedWriter, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(BufferedReader reader) {
        StringBuilder resultBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int resultSum;

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }

            String[] filter = stringBuilder.toString().split(System.lineSeparator());
            int indexSupply = 7;
            int indeBuy = 4;
            for (String result : filter) {
                if (result.startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(result.substring(indexSupply));
                }
                if (result.startsWith(BUY)) {
                    buySum += Integer.parseInt(result.substring(indeBuy));
                }
            }
            resultSum = supplySum - buySum;

            return resultBuilder
                    .append(SUPPLY)
                    .append(supplySum)
                    .append(System.lineSeparator())
                    .append(BUY).append(buySum)
                    .append(System.lineSeparator()).append(RESULT)
                    .append(resultSum).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile(BufferedWriter bufferedWriter, String report) {
        try (bufferedWriter) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
