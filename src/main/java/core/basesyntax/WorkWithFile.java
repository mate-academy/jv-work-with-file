package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String supply = "supply";
    public static final String buy = "buy";
    public static final int ZERO_LENGTH = 0;
    public static final int firstElement = 0;
    public static final int secondElement = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File destinationFile = new File(toFileName);
        int supplyCounter = 0;
        int buyCounter = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(destinationFile, true))) {
            if (destinationFile.length() != ZERO_LENGTH) {
                return;
            }
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] elements = value.split(",");
                if (elements[firstElement].equals(supply)) {
                    supplyCounter += Integer.parseInt(elements[secondElement]);
                }
                if (elements[firstElement].equals(buy)) {
                    buyCounter += Integer.parseInt(elements[secondElement]);
                }
                value = bufferedReader.readLine();
            }
            int result = supplyCounter - buyCounter;
            bufferedWriter.write(supply + "," + supplyCounter + System.lineSeparator());
            bufferedWriter.write(buy + "," + buyCounter + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }
    }
}
