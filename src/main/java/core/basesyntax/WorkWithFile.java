package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_REGEX = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_TYPE_SUPPLY = "supply";
    private static final String OPERATION_TYPE_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] strings = readFile(fromFileName);
        String report = generateReport(strings);
        writeFile(toFileName, report);
    }

    private String generateReport(String[] strings) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String string : strings) {
            String[] splitString = string.split(SPLIT_REGEX);
            if (splitString[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_SUPPLY)) {
                totalSupply += Integer.parseInt(splitString[AMOUNT_INDEX]);
            }
            if (splitString[OPERATION_TYPE_INDEX].equals(OPERATION_TYPE_BUY)) {
                totalBuy += Integer.parseInt(splitString[AMOUNT_INDEX]);
            }
        }

        int result = totalSupply - totalBuy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(result);

        return stringBuilder.toString();
    }

    private String[] readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from the file", e);
        }

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            toFile.createNewFile();
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data in the file", e);
        }
    }
}
