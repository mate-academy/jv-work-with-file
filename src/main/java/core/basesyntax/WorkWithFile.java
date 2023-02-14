package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = "\n";
    private static final String COMMA = ",";
    private static final String FILE_IS_EMPTY_MESSAGE = "File is empty";
    private static final String CANT_READ_FILE_MESSAGE = "Can't read file";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CANT_WRITE_TO_THE_FILE_MESSAGE = "can't write to the file";
    private static final String RESULT = "result";

    private StringBuilder inputData;

    public void getStatistic(String fromFileName, String toFileName) {
        inputData = new StringBuilder();
        readFromFile(fromFileName);
        createReport(toFileName);
    }

    private void readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String readerFile = reader.readLine();
            if (readerFile == null) {
                throw new RuntimeException(FILE_IS_EMPTY_MESSAGE);
            }
            while (readerFile != null) {
                inputData.append(readerFile).append(System.lineSeparator());
                readerFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(CANT_READ_FILE_MESSAGE, e);
        }
    }

    private void createReport(String toFileName) {
        String[] splitInfo = inputData.toString().split(NEW_LINE);
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < splitInfo.length; i++) {
            String[] row = splitInfo[i].split(COMMA);
            if (SUPPLY.equals(row[0])) {
                supplyCount += Integer.parseInt(row[1]);
            }
            if (BUY.equals(row[0])) {
                buyCount += Integer.parseInt(row[1]);
            }
        }
        int result = supplyCount - buyCount;
        writeToFile(toFileName, supplyCount, buyCount, result);
    }

    private void writeToFile(String toFileName, int supplyCount, int buyCount, int result) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(SUPPLY + COMMA + supplyCount + System.lineSeparator()
                    + BUY + COMMA + buyCount + System.lineSeparator() + RESULT + COMMA + result);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(CANT_WRITE_TO_THE_FILE_MESSAGE, e);
        }
    }
}
