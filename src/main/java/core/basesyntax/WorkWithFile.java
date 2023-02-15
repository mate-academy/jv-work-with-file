package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int WORD_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String originalString = readFromFile(fromFileName);
        String createReport = createReport(originalString);
        writeToFile(createReport, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                stringBuilder.append(reader + System.lineSeparator());
                reader = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String originalString) {
        int amountOfSupply = 0;
        int amountOfBuy = 0;
        for (String string : originalString.split(System.lineSeparator())) {
            String[] splited = string.split(",");
            if (splited[WORD_INDEX].equals(SUPPLY)) {
                amountOfSupply += Integer.parseInt(splited[AMOUNT_INDEX]);
            } else if (splited[WORD_INDEX].equals(BUY)) {
                amountOfBuy += Integer.parseInt(splited[AMOUNT_INDEX]);
            } else {
                throw new RuntimeException("Wrong data format " + splited[WORD_INDEX]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY + ",")
               .append(amountOfSupply)
               .append(System.lineSeparator())
               .append(BUY + ",")
               .append(amountOfBuy)
               .append(System.lineSeparator())
               .append("result,")
               .append(amountOfSupply - amountOfBuy)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String createReport, String toFileName) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite))) {
            bufferedWriter.write(createReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write any information to the file! " + e);
        }
    }
}
