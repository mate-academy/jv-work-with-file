package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CATEGORY_IN_FILE_SUPPLY = "supply";
    private static final String CATEGORY_IN_FILE_BUY = "buy";
    private static final String CATEGORY_IN_FILE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeInFile(getResult(readFile(fromFileName)), toFileName);
    }

    public void writeInFile(String resultInString, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultInString);
        } catch (IOException e) {
            throw new RuntimeException("The file at path " + toFileName + " was not written" + e);
        }
    }

    public String getResult(StringBuilder readFile) {
        String[] readFileToStringArray = readFile.toString().split(System.lineSeparator());
        String[] oneCategoryAndPrice;
        StringBuilder stringBuilder = new StringBuilder();
        int sumSupply = 0;
        int sumBuy = 0;
        for (String oneLine: readFileToStringArray) {
            oneCategoryAndPrice = oneLine.split(",");
            if (oneCategoryAndPrice[0].equals(CATEGORY_IN_FILE_SUPPLY)) {
                sumSupply += Integer.parseInt(oneCategoryAndPrice[1]);
            } else if (oneCategoryAndPrice[0].equals(CATEGORY_IN_FILE_BUY)) {
                sumBuy += Integer.parseInt(oneCategoryAndPrice[1]);
            }
        }

        return stringBuilder.append(CATEGORY_IN_FILE_SUPPLY).append(",").append(sumSupply)
                .append(System.lineSeparator())
                .append(CATEGORY_IN_FILE_BUY)
                .append(",").append(sumBuy)
                .append(System.lineSeparator())
                .append(CATEGORY_IN_FILE_RESULT)
                .append(",").append(sumSupply - sumBuy).toString();
    }

    public StringBuilder readFile(String fromFileName) {
        StringBuilder readFile = new StringBuilder();
        String oneLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            oneLine = bufferedReader.readLine();
            while (oneLine != null) {
                readFile.append(oneLine).append(System.lineSeparator());
                oneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("The file at path" + fromFileName + " was not read : " + e);
        }
        return readFile;
    }
}
