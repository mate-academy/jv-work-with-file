package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] FILE_STRING_VALUES = {"supply", "buy", "result"};
    private static final String COMA = ",";
    private static final int[] FILE_INT_VALUES = {0, 0, 0};
    private static final int MINIMUM_FILE_LENGTH = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileText = readFromFile(fromFileName);
        String[] fileTextArray = fileText.toString().split(COMA);
        calculateAmountsForValues(fileTextArray);
        writeToFile(toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                fileText.append(line).append(COMA);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read from this file: " + fileName, e);
        }
        return fileText.toString();
    }

    private void calculateAmountsForValues(String[] fileTextArray) {
        FILE_INT_VALUES[0] = 0;
        FILE_INT_VALUES[1] = 0;
        for (int i = 0; i < fileTextArray.length; i += 2) {
            if (fileTextArray[i].equals(FILE_STRING_VALUES[0])) {
                FILE_INT_VALUES[0] += Integer.valueOf(fileTextArray[i + 1]);
            } else {
                FILE_INT_VALUES[1] += Integer.valueOf(fileTextArray[i + 1]);
            }
        }
        FILE_INT_VALUES[2] = FILE_INT_VALUES[0] - FILE_INT_VALUES[1];
    }

    private void writeToFile(String fileName) {
        File fileToWrite = new File(fileName);
        StringBuilder textToWrite = new StringBuilder();
        if (fileToWrite.length() < MINIMUM_FILE_LENGTH) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                for (int i = 0; i < FILE_STRING_VALUES.length; i++) {
                    textToWrite.append(FILE_STRING_VALUES[i])
                            .append(COMA)
                            .append(FILE_INT_VALUES[i])
                            .append(System.lineSeparator());
                }
                writer.write(textToWrite.toString());
            } catch (IOException e) {
                throw new RuntimeException("can't write to this file " + fileName, e);
            }
        }
    }
}
