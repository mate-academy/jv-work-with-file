package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        String[] readRecords = readRecordsFromFile(fromFile);
        String[] report = getReport(readRecords);
        write(report, toFile);
    }

    private String[] getReport(String[] readRecords) {
        int buySum = 0;
        int supplySum = 0;
        int result;
        for (int i = 0; i < readRecords.length; i++) {
            if (readRecords[i].equals(SUPPLY)) {
                supplySum += Integer.parseInt(readRecords[i + 1]);
            }
            if (readRecords[i].equals(BUY)) {
                buySum += Integer.parseInt(readRecords[i + 1]);
            }
        }
        result = supplySum - buySum;
        return getReport(supplySum, buySum, result);
    }

    private String[] getReport(int supplySum, int buySum, int result) {
        String[] array = new String[3];
        array[0] = SUPPLY + COMA + supplySum;
        array[1] = BUY + COMA + buySum;
        array[2] = RESULT + COMA + result;
        return array;
    }

    private void write(String[] records, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            for (String record : records) {
                bufferedWriter.write(record + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String[] readRecordsFromFile(File fromFile) {
        StringBuilder recordBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String string = reader.readLine();
            while (string != null) {
                recordBuilder.append(string).append(COMA);
                string = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFile.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFile.getName(), e);
        }
        return recordBuilder.toString().split(COMA);
    }
}
