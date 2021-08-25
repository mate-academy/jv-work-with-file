package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CSV_SEPARATOR = ",";



    public void getStatistic(String fromFileName, String toFileName) {

        String[] arrayOfCategories = new String[] {SUPPLY, BUY};
        int[] arrayOfSumsOfCategories = new int[arrayOfCategories.length];
        File fileToRead = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String readLineOfFile = reader.readLine();
            String[] readLineSplitTwoParts;
            while (readLineOfFile != null) {
                readLineSplitTwoParts = readLineOfFile.split(CSV_SEPARATOR);
                for (int i = 0; i < arrayOfCategories.length; i++) {
                    if (arrayOfCategories[i].equals(readLineSplitTwoParts[OPERATION_TYPE])) {
                        arrayOfSumsOfCategories[i]
                                += Integer.parseInt(readLineSplitTwoParts[AMOUNT]);
                    }
                }
                readLineOfFile = reader.readLine();
            }
        } catch (IOException exc) {
            throw new RuntimeException("Problems with file reading" + fileToRead, exc);
        }
        StringBuilder buildString = new StringBuilder();
        String toWriteString = buildString.append(arrayOfCategories[OPERATION_TYPE])
                .append(CSV_SEPARATOR).append(arrayOfSumsOfCategories[OPERATION_TYPE])
                .append(System.lineSeparator())
                .append(arrayOfCategories[AMOUNT]).append(CSV_SEPARATOR)
                .append(arrayOfSumsOfCategories[AMOUNT]).append(System.lineSeparator())
                .append("result,")
                .append((arrayOfSumsOfCategories[OPERATION_TYPE] - arrayOfSumsOfCategories[AMOUNT]))
                .toString();
        writeToFile(toFileName, toWriteString);
    }

    private void writeToFile(String toWriteFile, String toWriteData) {

        File fileToWrite = new File(toWriteFile);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite, true));
            writer.write(toWriteData);
            writer.close();
        } catch (IOException exc) {
            throw new RuntimeException("Can't write to file:" + fileToWrite, exc);
        }
    }
}
