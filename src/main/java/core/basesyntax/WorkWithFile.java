package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DATA_SEPARATOR = ",";
    private static final String KEY_S = "supply";
    private static final String KEY_B = "buy";
    private String report;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readingFromFile(fromFileName).toString().split(DATA_SEPARATOR);
        createReport(data);
        writeToFile(toFileName);
    }

    private StringBuilder readingFromFile(String fileName) {
        File fileIn = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine).append(DATA_SEPARATOR);
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file.", e);
        }
        return stringBuilder;
    }

    private void createReport(String[] arrayData) {
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < arrayData.length; i += 2) {
            if (arrayData[i].equals(KEY_S)) {
                supply += getInt(arrayData[i + 1]);
            } else {
                buy += getInt(arrayData[i + 1]);
            }
        }

        report = KEY_S + DATA_SEPARATOR + supply + LINE_SEPARATOR
                + KEY_B + DATA_SEPARATOR + buy + LINE_SEPARATOR
                + "result" + DATA_SEPARATOR + (supply - buy);
    }

    private int getInt(String stringNumber) {
        int amount;

        try {
            amount = Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Number Format is wrong", e);
        }
        return amount;
    }

    private void writeToFile(String fileName) {
        File fileOut = new File(fileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
