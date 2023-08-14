package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WorkWithFile {
    private static final String BUY_WORD = "buy";
    private static final String SUPPLY_WORD = "supply";
    private static final int INDEX_OF_WORD = 0;
    private static final int INDEX_OF_NUMBER = 1;
    private static final String WHITESPACE_SEPARATOR = " ";
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readDataFromFile(fromFileName);
        String report = createReport(data);
        writeDataToTheFile(toFileName, report);
    }

    private String[] readDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String temporary;
            while ((temporary = bufferedReader.readLine()) != null) {
                stringBuilder.append(temporary).append(WHITESPACE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fileName, e);
        }
        return stringBuilder.toString().split(WHITESPACE_SEPARATOR);
    }

    private String createReport(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] temporaryArray;
        for (String partOfData : data) {
            temporaryArray = partOfData.split(COMMA_SEPARATOR);
            if (temporaryArray[INDEX_OF_WORD].equals(BUY_WORD)) {
                buyCounter += Integer.parseInt(temporaryArray[INDEX_OF_NUMBER]);
            } else if (temporaryArray[INDEX_OF_WORD].equals(SUPPLY_WORD)) {
                supplyCounter += Integer.parseInt(temporaryArray[INDEX_OF_NUMBER]);
            }
        }
        return SUPPLY_WORD + COMMA_SEPARATOR + supplyCounter + System.lineSeparator()
                + BUY_WORD + COMMA_SEPARATOR + buyCounter + System.lineSeparator()
                + "result," + (supplyCounter - buyCounter);
    }

    private void writeDataToTheFile(String fileName, String data) {
        if (new File(fileName).length() != 0) {
            deleteContentsOfFile(fileName);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + fileName, e);
        }
    }

    private void deleteContentsOfFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException("Can't delete content of the file: " + fileName, e);
        }
    }
}
