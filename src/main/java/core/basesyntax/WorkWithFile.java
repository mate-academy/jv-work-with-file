package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String currentLine : dataFromFile) {
            String[] reportArray = currentLine.split(",");
            if (reportArray[INDEX_OF_OPERATION_TYPE].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(reportArray[INDEX_OF_AMOUNT]);
            } else {
                buyAmount += Integer.parseInt(reportArray[INDEX_OF_AMOUNT]);
            }
        }
        int result = supplyAmount - buyAmount;
        String resultStatistic = SUPPLY + "," + supplyAmount + System.lineSeparator()
                + BUY + "," + buyAmount + System.lineSeparator()
                + "result," + result + System.lineSeparator();
        writeToFile(resultStatistic, toFileName);
    }

    private String[] readFromFile(String fileName) {
        File fileFrom = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(fileName))) {
            String stringFromFile = readerFromFile.readLine();
            while (stringFromFile != null) {
                builder.append(stringFromFile).append(System.lineSeparator());
                stringFromFile = readerFromFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
        String[] readData = {};
        if (!builder.toString().equals(null)) {
            readData = builder.toString().split(System.lineSeparator());
        }
        return readData;
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}