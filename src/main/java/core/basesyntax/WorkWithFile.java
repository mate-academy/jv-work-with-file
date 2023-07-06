package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPACE = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int INFO_ARRAY_LENGTH = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = extractData(data);
        writeToFile(report,toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private String extractData(String data) {
        String[] allInfo = data.split("\\W+");
        int totalSupply = 0;
        int totalBuy = 0;
        for (int i = 0; i < allInfo.length; i += 2) {
            if (allInfo[i].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(allInfo[i + 1]);
            } else {
                totalBuy += Integer.parseInt(allInfo[i + 1]);
            }
        }
        int [] operationInfo = new int[INFO_ARRAY_LENGTH];
        return createReportMessage(operationInfo, totalSupply, totalBuy, totalSupply - totalBuy);
    }

    private String createReportMessage(int[] operationInfo, int totalSupply,
                                       int totalBuy, int result) {
        operationInfo[SUPPLY_INDEX] = totalSupply;
        operationInfo[BUY_INDEX] = totalBuy;
        operationInfo[RESULT_INDEX] = result;
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(COMMA).append(operationInfo[SUPPLY_INDEX]).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(operationInfo[BUY_INDEX]).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(operationInfo[RESULT_INDEX])
                .append(LINE_SEPARATOR);
        return sb.toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
