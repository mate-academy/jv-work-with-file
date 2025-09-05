package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SPACE = " ";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int INITIAL_SUM = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int sumBuy = getSumByKeyWord(getDataFromFile(fromFileName), BUY);
        int sumSupply = getSumByKeyWord(getDataFromFile(fromFileName), SUPPLY);

        writeToFile(toFileName, getReport(sumBuy, sumSupply));
    }

    private String getDataFromFile(String fromFileName) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                data.append(value).append(SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        return data.toString();
    }

    private int getSumByKeyWord(String data, String keyWord) {
        int sum = INITIAL_SUM;
        String[] splittedData = data.split(SPACE);

        for (String element : splittedData) {
            if (element.contains(keyWord)) {
                String[] tempData = element.split(COMMA);
                int number = Integer.parseInt(tempData[1]);

                sum += number;
            }
        }

        return sum;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file");
        }
    }

    private String getReport(int sumBuy, int sumSupply) {
        int result = sumSupply - sumBuy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY)
                .append(COMMA)
                .append(sumSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(sumBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(result);

        return report.toString();
    }
}
