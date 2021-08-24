package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_WORD_OF_LINE = 0;
    private static final int SECOND_WORD_OF_LINE = 1;
    private static final String FIRST_UNIQUE_CATEGORY = "supply";
    private static final String SECOND_UNIQUE_CATEGORY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {

        String[] arrayOfCategories = new String[] {FIRST_UNIQUE_CATEGORY, SECOND_UNIQUE_CATEGORY};
        int[] arrayOfSumsOfCategories = new int[arrayOfCategories.length];
        File fileToRead = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String readLineOfFile = reader.readLine();
            String[] readLineSplitTwoParts;
            while (readLineOfFile != null) {
                readLineSplitTwoParts = readLineOfFile.split(",");
                for (int i = 0; i < arrayOfCategories.length; i++) {
                    if (arrayOfCategories[i].equals(readLineSplitTwoParts[FIRST_WORD_OF_LINE])) {
                        arrayOfSumsOfCategories[i]
                                += Integer.parseInt(readLineSplitTwoParts[SECOND_WORD_OF_LINE]);
                    }
                }
                readLineOfFile = reader.readLine();
            }
        } catch (IOException exc) {
            throw new RuntimeException("Problems with file reading" + fileToRead, exc);
        }
        String toWriteString = new String(arrayOfCategories[FIRST_WORD_OF_LINE] + ","
                + arrayOfSumsOfCategories[FIRST_WORD_OF_LINE] + System.lineSeparator()
                + arrayOfCategories[SECOND_WORD_OF_LINE] + ","
                + arrayOfSumsOfCategories[SECOND_WORD_OF_LINE] + System.lineSeparator()
                + "result," + (arrayOfSumsOfCategories[FIRST_WORD_OF_LINE]
                - arrayOfSumsOfCategories[SECOND_WORD_OF_LINE]));

        File fileToWrite = new File(toFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite, true));
            writer.write(toWriteString);
            writer.close();
        } catch (IOException exc) {
            throw new RuntimeException("Can't write to file" + fileToWrite, exc);
        }
    }
}
