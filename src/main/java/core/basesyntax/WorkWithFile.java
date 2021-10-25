package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String VALUES_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFile = readFromFile(fromFileName);
        String report = createReport(infoFromFile);
        writeToFile(report, toFileName);
    }

    public String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return builder.toString().split(" ");
    }

    public void writeToFile(String whatToWrite, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(whatToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    public String createReport(String[] incomeArray) {
        int totalSupply = 0;
        int totalBuy = 0;
        int result;
        String[] tempStringArray;
        for (String s : incomeArray) {
            tempStringArray = s.split(VALUES_SEPARATOR);
            if (tempStringArray[0].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(tempStringArray[1]);
            } else if (tempStringArray[0].equals(BUY)) {
                totalBuy += Integer.parseInt(tempStringArray[1]);
            }
        }
        result = totalSupply - totalBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator())
                .append(BUY).append(",").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return builder.toString();
    }
}
