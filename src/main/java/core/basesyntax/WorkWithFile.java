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

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readFromFile(fromFileName);
        writeToFile(toFileName, statistic);
    }

    private void writeToFile(String toFileName, String statistic) {
        File myFile = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(myFile));
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + myFile, e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close BufferedWriter", e);
            }
        }
    }

    private String readFromFile(String fromFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        File file = new File(fromFileName);
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();

            if (value == null) {
                System.out.println("Data not found");
            }

            while (value != null) {

                if (value.startsWith(SUPPLY)) {
                    supplyCounter += Integer.parseInt(value.split(",")[ONE_ARRAY_INDEX]);
                }

                if (value.startsWith(BUY)) {
                    buyCounter += Integer.parseInt(value.split(",")[ONE_ARRAY_INDEX]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + file, e);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close BufferedReader", e);
            }
        }
        return "supply," + supplyCounter + System.lineSeparator()
                + "buy," + buyCounter + System.lineSeparator()
                + "result," + (supplyCounter - buyCounter);
    }
}
