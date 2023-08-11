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

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToTheFile(toFileName, createReport(readDataFromFile(fromFileName)));
    }

    private String[] readDataFromFile(String nameOfFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameOfFile))) {
            String temporary;
            while ((temporary = bufferedReader.readLine()) != null) {
                stringBuilder.append(temporary).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Have some problems to read the file: ", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private String createReport(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] temporaryArray;
        for (String partOfData : data) {
            temporaryArray = partOfData.split(",");
            if (temporaryArray[0].equals(BUY_WORD)) {
                buyCounter += Integer.parseInt(temporaryArray[1]);
            } else if (temporaryArray[0].equals(SUPPLY_WORD)) {
                supplyCounter += Integer.parseInt(temporaryArray[1]);
            }
        }
        return SUPPLY_WORD + "," + supplyCounter + System.lineSeparator()
                + BUY_WORD + "," + buyCounter + System.lineSeparator()
                + "result," + (supplyCounter - buyCounter);
    }

    private void writeDataToTheFile(String nameOfFile, String data) {
        if (new File(nameOfFile).length() != 0) {
            deleteContentsOfFile(nameOfFile);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nameOfFile, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Have some problems to write to the file: ", e);
        }
    }

    private void deleteContentsOfFile(String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile)) {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException("Have some problems with deleting contents of file: ", e);
        }
    }
}
