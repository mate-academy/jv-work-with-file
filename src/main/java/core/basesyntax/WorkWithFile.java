package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final int BUY_SUM_INDEX = 0;
    private static final int SUPPLY_SUM_INDEX = 1;
    private final int[] resultArray = new int[2];
    private File file = null;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    private void writeToFile(String toFileName) {
        file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            String reportString = getReport();
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + file);
        }
    }

    private String getReport() {
        int reportResult = resultArray[SUPPLY_SUM_INDEX] - resultArray[BUY_SUM_INDEX];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply");
        stringBuilder.append(COMMA_CHARACTER);
        stringBuilder.append(resultArray[SUPPLY_SUM_INDEX]);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("buy");
        stringBuilder.append(COMMA_CHARACTER);
        stringBuilder.append(resultArray[BUY_SUM_INDEX]);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("result");
        stringBuilder.append(COMMA_CHARACTER);
        stringBuilder.append(reportResult);
        return stringBuilder.toString();
    }

    private void readFromFile(String fromFileName) {
        file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String temporaryString = bufferedReader.readLine();
            while (temporaryString != null) {
                String[] temporaryArray = temporaryString.split(COMMA_CHARACTER);
                if (temporaryArray[0].equals("buy")) {
                    resultArray[BUY_SUM_INDEX] += Integer.parseInt(temporaryArray[1]);
                } else if (temporaryArray[0].equals("supply")) {
                    resultArray[SUPPLY_SUM_INDEX] += Integer.parseInt(temporaryArray[1]);
                }
                temporaryString = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + file);
        }
    }
}
