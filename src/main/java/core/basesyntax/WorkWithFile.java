package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.split(SEPARATOR)[OPERATION_TYPE_INDEX].equals("buy")) {
                    buySum += Integer.parseInt(line.split(SEPARATOR)[OPERATION_VALUE_INDEX]);
                } else {
                    supplySum += Integer.parseInt(line.split(SEPARATOR)[OPERATION_VALUE_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file named: " + fromFileName, e);
        }
        String report = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
