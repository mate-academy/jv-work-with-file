package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileCopy = new File(fromFileName);
        File fileWrite = new File(toFileName);
        int[] arrayResultSupply = new int[2];
        String[] array;

        try (Scanner scanner = new Scanner(fileCopy)) {
            while (scanner.hasNext()) {
                String str = scanner.next();
                array = str.split(",");
                if (array[0].equals("supply")) {
                    arrayResultSupply[0] += Integer.parseInt(array[1]);
                } else {
                    arrayResultSupply[1] += Integer.parseInt(array[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite))) {
            bufferedWriter.write("supply," + arrayResultSupply[0] + System.lineSeparator());
            bufferedWriter.write("buy," + arrayResultSupply[1] + System.lineSeparator());
            bufferedWriter.write("result," + (arrayResultSupply[0] - arrayResultSupply[1])
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
