package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int FIRST_PART = 0;
    public static final int SECOND_PART = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayNames = new String[] {"supply", "buy"};
        int[] arrayOfSums = new int[arrayNames.length];
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String readValue = reader.readLine();
            String[] readArray = readValue.split(",");
            while (readValue != null) {
                readArray = readValue.split(",");
                for (int i = 0; i < arrayNames.length; i++) {
                    if (arrayNames[i].equals(readArray[FIRST_PART])) {
                        arrayOfSums[i] += Integer.parseInt(readArray[SECOND_PART]);
                    }
                }
                readValue = reader.readLine();
            }
        } catch (IOException exc) {
            throw new RuntimeException("Problems with file reading", exc);
        }
        String toWriteStr = "";
        File fileWrite = new File(toFileName);
        toWriteStr = new String(arrayNames[FIRST_PART] + ","
                + arrayOfSums[FIRST_PART] + System.lineSeparator()
                + arrayNames[SECOND_PART] + "," + arrayOfSums[SECOND_PART] + System.lineSeparator()
                + "result," + (arrayOfSums[FIRST_PART] - arrayOfSums[SECOND_PART]));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite, true));
            writer.write(toWriteStr);
            writer.close();
        } catch (IOException exc) {
            throw new RuntimeException("Can't write file", exc);
        }
    }
}
