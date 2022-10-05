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
    static final String COMMA = ",";
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
            String[] operationAndAmount = dataString.split(COMMA);
            if (operationAndAmount[OPERATION_INDEX].equals(SUPPLY)) {
                supplyCount += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            } else {
                buyCount += Integer.parseInt(operationAndAmount[AMOUNT_INDEX]);
            }
        }
        int result = supplyCount - buyCount;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supplyCount).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyCount).append(System.lineSeparator())
                .append("result").append(COMMA).append(result);
        return report.toString();
    }
}
