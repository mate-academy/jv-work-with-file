package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int indexOfOperation = 0;
    private static final int indexOfNumber = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int result;
        int supplyCount = 0;
        int buyCount = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = fileReader.readLine();
            while (line != null) {
                String[] splited = line.split(SEPARATOR);
                if (splited[indexOfOperation].equals("supply")) {
                    supplyCount += Integer.parseInt(splited[indexOfNumber]);
                } else if (splited[indexOfOperation].equals("buy")) {
                    buyCount += Integer.parseInt(splited[indexOfNumber]);
                }
                line = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = supplyCount - buyCount;
        String report = "supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator() + "result," + result;
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
