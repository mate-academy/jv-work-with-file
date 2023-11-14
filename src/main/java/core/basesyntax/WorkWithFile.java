package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SPLIT_DELIMITER_SCV = ",";
    public static final String DATA_FIRST = "supply";
    public static final String DATA_SECOND = "buy";
    public static final String DATA_THIRD = "result";
    public static final int READ_FILE_DATA_INDEX_ONE = 0;
    public static final int READ_FILE_DATA_INDEX_TWO = 1;

    public String[][] readeFile(String fileName) {
        long counterReadLines = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String readOneLine = bufferedReader.readLine();
            while (readOneLine != null) {
                counterReadLines++;
                readOneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from: " + fileName, e);
        }
        String[][] readAllLines = new String[(int) counterReadLines][];
        int arrayIndex = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String readOneLine = bufferedReader.readLine();
            while (readOneLine != null) {
                String[] arrayToSplit = readOneLine.split(SPLIT_DELIMITER_SCV);
                readAllLines[arrayIndex] = arrayToSplit;
                arrayIndex++;
                readOneLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read data from: " + fileName, e);
        }
        return readAllLines;
    }

    public String generateReport(String [][] arrayFromReadFile) {
        int resultSupply = 0;
        int resultBuy = 0;

        for (int i = 0; i < arrayFromReadFile.length; i++) {
            int dataValue = Integer.parseInt(arrayFromReadFile[i][READ_FILE_DATA_INDEX_TWO]);
            for (int j = 1; j < arrayFromReadFile[i].length; j++) {
                if (arrayFromReadFile[i][READ_FILE_DATA_INDEX_ONE].equals(DATA_FIRST)) {
                    resultSupply += dataValue;
                }
                if (arrayFromReadFile[i][READ_FILE_DATA_INDEX_ONE].equals(DATA_SECOND)) {
                    resultBuy += dataValue;
                }
            }
        }
        int resultWithSupplyAndBuy = resultSupply - resultBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(DATA_FIRST + SPLIT_DELIMITER_SCV)
                .append(resultSupply)
                .append(System.lineSeparator())
                .append(DATA_SECOND + SPLIT_DELIMITER_SCV)
                .append(resultBuy)
                .append(System.lineSeparator())
                .append(DATA_THIRD + SPLIT_DELIMITER_SCV)
                .append(resultWithSupplyAndBuy);
        return builder.toString();
    }

    public void writeToFile(String writeFileName, String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to: " + writeFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {

        String[][] data = readeFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName,report);
    }
}
