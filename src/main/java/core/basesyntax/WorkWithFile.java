package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }

    private String readFromFile(String fileName) {
        File fromFile = new File(fileName);
        stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String readedString) {
        int supplyInt = 0;
        int buyInt = 0;
        String[] arrayOfStrings = readedString.split(System.lineSeparator());
        for (String string : arrayOfStrings) {
            String[] stringSplit = string.split(COMMA);
            if (stringSplit[0].equals(SUPPLY)) {
                supplyInt += Integer.parseInt(stringSplit[1]);
            } else {
                buyInt += Integer.parseInt(stringSplit[1]);
            }
        }
        int resultInt = supplyInt - buyInt;
        stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supplyInt).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyInt).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultInt);
        return stringBuilder.toString();
    }
}
