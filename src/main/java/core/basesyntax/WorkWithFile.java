package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int ONE_ARRAY_INDEX = 1;

    public WorkWithFile() {
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        writeToFile(toFileName, statistic);
    }

    private void writeToFile(String toFileName, String statistic) {
        File myFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + myFile, e);
        }
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        int supplyCounter = 0;
        int buyCounter = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String stringNumber = value.split(",")[ONE_ARRAY_INDEX];
                if (value.startsWith(SUPPLY)) {
                    supplyCounter += Integer.parseInt(stringNumber);
                }
                if (value.startsWith(BUY)) {
                    buyCounter += Integer.parseInt(stringNumber);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + file, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Data not found in file " + file, e);
        }
        return report(supplyCounter, buyCounter);
    }

    private String report(int supplyCounter, int buyCounter) {
        return "supply," + supplyCounter + System.lineSeparator()
                + "buy," + buyCounter + System.lineSeparator()
                + "result," + (supplyCounter - buyCounter);
    }
}
