package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SYMBOL = ",";
    private static final int CSV_VALUE_INDEX = 1;
    private static final int CSV_KEY_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> data = getData(fromFileName);
        String report = createReport(data);
        writeResultToFile(toFileName, report);
    }

    private HashMap<String, Integer> getData(String fileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String currentString = bufferedReader.readLine();
            while (currentString != null) {
                String[] splitedLine = currentString.split(COMMA_SYMBOL);
                int operationAmount = Integer.parseInt(splitedLine[CSV_VALUE_INDEX]);
                String operation = splitedLine[CSV_KEY_INDEX];
                if (operation.equals(SUPPLY)) {
                    supply += operationAmount;
                } else {
                    buy += operationAmount;
                }
                currentString = bufferedReader.readLine();
            }
            int finalSupply = supply;
            int finalBuy = buy;
            return new HashMap<>() {
                {
                    this.put("supply", finalSupply);
                    this.put("buy", finalBuy);
                }
            };
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private String createReport(HashMap<String, Integer> data) {
        StringBuilder report = new StringBuilder();

        int supply = data.get("supply");
        int buy = data.get("buy");
        int result = supply - buy;
        report.append(SUPPLY)
                .append(COMMA_SYMBOL)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA_SYMBOL)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA_SYMBOL)
                .append(result);

        return report.toString();
    }

    private void writeResultToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
