package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_KEYWORD = "buy";
    private static final String COMMA_SEPARATOR = ",";
    private static final String SEPARATOR = " ";
    private static final int AMOUNT_INDEX = 1;
    private static final int ACTION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(calculateData(readDataFromFile(fromFileName)), toFileName);
    }

    private String[] readDataFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                stringBuilder.append(str).append(SEPARATOR);
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: ", e);
        }
        return stringBuilder.toString().split(SEPARATOR);
    }

    private String calculateData(String[] stringsFromFile) {
        int supplyAllSum = 0;
        int buyAllSum = 0;
        for (String line : stringsFromFile) {
            String[] lineSeparation = line.split(COMMA_SEPARATOR);
            if (lineSeparation[ACTION_INDEX].equals(BUY_KEYWORD)) {
                buyAllSum += Integer.parseInt(lineSeparation[AMOUNT_INDEX]);
            } else {
                supplyAllSum += Integer.parseInt(lineSeparation[AMOUNT_INDEX]);
            }
        }
        return createReport(supplyAllSum, buyAllSum);
    }

    private String createReport(int supplyAllSum, int buyAllSum) {
        return "supply," + supplyAllSum
                + System.lineSeparator() + "buy,"
                + buyAllSum + System.lineSeparator() + "result," + (supplyAllSum - buyAllSum);
    }

    private void writeDataToFile(String calculatedData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculatedData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file: ", e);
        }
    }
}
