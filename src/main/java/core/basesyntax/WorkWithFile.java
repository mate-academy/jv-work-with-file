package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final char COMMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            StringBuilder builder = new StringBuilder();
            builder.append(SUPPLY)
                    .append(COMMA)
                    .append(amountOfOneType(fileToRead, SUPPLY))
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(COMMA)
                    .append(amountOfOneType(fileToRead, BUY))
                    .append(System.lineSeparator())
                    .append(RESULT)
                    .append(COMMA)
                    .append((amountOfOneType(fileToRead, SUPPLY)
                            - amountOfOneType(fileToRead, BUY)));
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to file");
        }
    }

    private String getName(String lineFromFile) {
        return lineFromFile.split(",")[NAME_INDEX];
    }

    private int getAmount(String lineFromFile) {
        return Integer.parseInt(lineFromFile.split(",")[VALUE_INDEX]);
    }

    private int amountOfOneType(File file, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            int totalAmount = 0;
            while (value != null) {
                if (getName(value).equals(name)) {
                    totalAmount += getAmount(value);
                }
                value = reader.readLine();
            }
            return totalAmount;
        } catch (IOException e) {
            throw new RuntimeException("can't read from file", e);
        }
    }
}
