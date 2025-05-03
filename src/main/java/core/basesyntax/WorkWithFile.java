package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = " ";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int AMOUNT_INDEX = 1;
    private static final int TYPE_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String textFromFile = readFromFile(fromFileName);
        String report = createReport(textFromFile);
        writeToFile(report, toFileName);
    }

    private static String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return "";
            }
            while (line != null) {
                stringBuilder.append(line).append(SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
        return stringBuilder.toString();
    }

    private static String createReport(String textFromFile) {
        String[] arrayOfLines = textFromFile.split(SEPARATOR);
        int totalSupply = 0;
        int totalBuy = 0;
        int result;
        for (String string : arrayOfLines) {
            String[] splittedString = string.split(COMMA);
            int sum = Integer.parseInt(splittedString[AMOUNT_INDEX]);
            if (splittedString[TYPE_INDEX].equalsIgnoreCase(SUPPLY)) {
                totalSupply += sum;
            } else if (splittedString[TYPE_INDEX].equalsIgnoreCase(BUY)) {
                totalBuy += sum;
            }
        }
        result = totalSupply - totalBuy;
        String report = SUPPLY + COMMA + totalSupply + System.lineSeparator()
                + BUY + COMMA + totalBuy + System.lineSeparator()
                + RESULT + COMMA + result;
        return report;
    }

    private static void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
