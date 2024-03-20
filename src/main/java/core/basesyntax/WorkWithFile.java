package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    private static final int ARRAY_VALUE_INDEX = 1;
    private static final int ARRAY_NAME_INDEX = 0;
    private static final int ARRAY_SUPPLY_INDEX = 0;
    private static final int ARRAY_BUY_INDEX = 1;

    public static void getStatistic(String fromFileName, String toFileName) {
        File fileCopy = new File(fromFileName);
        File fileWrite = new File(toFileName);
        int[] arrayResultSupply = new int[2];
        String[] array;

        try (Scanner scanner = new Scanner(fileCopy)) {
            while (scanner.hasNext()) {
                String str = scanner.next();
                array = str.split(",");
                if (array[ARRAY_NAME_INDEX].equals("supply")) {
                    arrayResultSupply[ARRAY_SUPPLY_INDEX]
                            += Integer.parseInt(array[ARRAY_VALUE_INDEX]);
                } else {
                    arrayResultSupply[ARRAY_BUY_INDEX]
                            += Integer.parseInt(array[ARRAY_VALUE_INDEX]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite))) {
            bufferedWriter.write("supply," + arrayResultSupply[ARRAY_SUPPLY_INDEX]
                    + System.lineSeparator());
            bufferedWriter.write("buy," + arrayResultSupply[ARRAY_BUY_INDEX]
                    + System.lineSeparator());
            bufferedWriter.write("result," + (arrayResultSupply[ARRAY_SUPPLY_INDEX]
                    - arrayResultSupply[ARRAY_BUY_INDEX])
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
