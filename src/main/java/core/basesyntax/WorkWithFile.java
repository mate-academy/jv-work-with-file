package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final int OPERATION_INDEX = 0;
    static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String input = readFile(fromFileName);
        String report = createReport(input);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder inputText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String inputString = bufferedReader.readLine();
            while (inputString != null) {
                inputText.append(inputString).append(System.lineSeparator());
                inputString = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return inputText.toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    public String createReport(String data) {
        String[] dataStrings = data.split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        for (String dataString : dataStrings) {
            String[] operationAmount = dataString.split(",");
            if (operationAmount[OPERATION_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(operationAmount[AMOUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(operationAmount[AMOUNT_INDEX]);
            }
        }
        int result = supplyCount - buyCount;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyCount).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return report.toString();
    }
}
