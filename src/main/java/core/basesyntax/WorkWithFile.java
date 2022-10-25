package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = "::";
    private static final String COMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        writeToFile(toFileName, generateReport(data));
    }

    private String generateReport(String[] data) {
        String[] tmpArrStr;
        int buy = 0;
        int supply = 0;
        for (String s : data) {
            tmpArrStr = s.split(",");
            switch (tmpArrStr[ACTION_INDEX]) {
                case BUY:
                    buy += Integer.parseInt(tmpArrStr[AMOUNT_INDEX]);
                    break;
                case SUPPLY:
                    supply += Integer.parseInt(tmpArrStr[AMOUNT_INDEX]);
                    break;
                default:
                    break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(SUPPLY).append(COMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(supply - buy);
        return stringBuilder.toString();
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String tmpStr = bufferedReader.readLine();
            while (tmpStr != null) {
                stringBuilder.append(tmpStr).append(SEPARATOR);
                tmpStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't find a file: " + fromFileName, e);
        }
        return stringBuilder.toString().split(SEPARATOR);
    }

    private void writeToFile(String toFileName, String toOut) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(toOut);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file" + toFileName, e);
        }
    }
}
