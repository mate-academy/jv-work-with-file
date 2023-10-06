package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String DIVIDER = ",";
    private static final String SEPARATING_REGEX = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readCsv(fromFileName);
        String[] resultData = processData(data);
        writeCsv(toFileName, resultData);
    }

    private String[] readCsv(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(SEPARATING_REGEX);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString().split(SEPARATING_REGEX);
    }

    private void writeCsv(String fileName, String[] strings) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String str : strings) {
                StringBuilder builder = new StringBuilder(str).append(System.lineSeparator());
                bufferedWriter.write(builder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + fileName, e);
        }
    }

    private String [] processData(String[] data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String str : data) {
            String [] splittedString = str.split(DIVIDER);
            String inputData = splittedString[0];
            int inputAmount = Integer.parseInt(splittedString[1]);
            if (inputData.equals(SUPPLY)) {
                supplyAmount += inputAmount;
            }
            if (inputData.equals(BUY)) {
                buyAmount += inputAmount;
            }
        }
        String supplyData = SUPPLY + DIVIDER + supplyAmount;
        String buyData = BUY + DIVIDER + buyAmount;
        String resultData = RESULT + DIVIDER + (supplyAmount - buyAmount);
        return new String []{supplyData, buyData, resultData};
    }
}
