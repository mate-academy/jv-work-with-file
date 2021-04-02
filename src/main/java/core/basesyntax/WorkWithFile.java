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

    public void getStatistic(String fromFileName, String toFileName) {
        File fileToRead = new File(fromFileName);
        File fileToWrite = new File(toFileName);
        writeToFile(fileToWrite, fileToRead);
    }

    public String getName(String lineFromFile) {
        return lineFromFile.split(",")[NAME_INDEX];
    }

    public int getAmount(String lineFromFile) {
        return Integer.parseInt(lineFromFile.split(",")[VALUE_INDEX]);
    }

    public int amountOfOneType(File file, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            int totalAmount = 0;
            while (value != null) {
                if (getName(value).equals(name)) {
                    totalAmount += getAmount(value);
                    value = reader.readLine();
                } else {
                    value = reader.readLine();
                }
            }
            return totalAmount;
        } catch (IOException e) {
            throw new RuntimeException("can't read from file", e);
        }
    }

    public StringBuilder buildInputToFile(File fileReader) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY)
                .append(',')
                .append(amountOfOneType(fileReader, SUPPLY))
                .append(System.lineSeparator())
                .append(BUY)
                .append(',')
                .append(amountOfOneType(fileReader, BUY))
                .append(System.lineSeparator())
                .append(RESULT)
                .append(',')
                .append((amountOfOneType(fileReader, SUPPLY) - amountOfOneType(fileReader, BUY)));
        return builder;
    }

    public void writeToFile(File fileWriter, File fileReader) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriter, true))) {
            bufferedWriter.write(buildInputToFile(fileReader).toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to file");
        }
    }
}
