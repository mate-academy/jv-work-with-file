package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SEPARATOR = " ";
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int AMOUNT_INDEX = 1;
    private static final int TYPE_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayOfLines = readFromFile(fromFileName);
        String report = createReport(arrayOfLines);
        writeToFile(report, toFileName);
    }

    public static String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return new String[0];
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
        String[] arrayOfLines = stringBuilder.toString().split(SEPARATOR);
        return arrayOfLines;
    }

    public static String createReport(String[] arrayOfLines) {
        String report;
        int totalSupply = 0;
        int totalBuy = 0;
        int result;
        for (String string : arrayOfLines) {
            String[] splittedString = string.split(COMMA);
            int sum = Integer.parseInt(splittedString[AMOUNT_INDEX]);
            if (splittedString[TYPE_INDEX].equalsIgnoreCase(SUPPLY)) {
                totalSupply += sum;
            } else if (splittedString[0].equalsIgnoreCase("buy")) {
                totalBuy += sum;
            }
        }
        result = totalSupply - totalBuy;
        report = SUPPLY + COMMA + totalSupply + System.lineSeparator()
                + BUY + COMMA + totalBuy + System.lineSeparator()
                + "result" + COMMA + result;
        return report;
    }

    public static void writeToFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
