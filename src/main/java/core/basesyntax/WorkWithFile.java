package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DATA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private int supplyAmount;
    private int buyAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        this.supplyAmount = 0;
        this.buyAmount = 0;
        readFile(fromFileName);
        writeFile(toFileName);

    }

    private void readFile(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(DATA_SEPARATOR);
                if (data[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                } else if (data[OPERATION_TYPE_INDEX].equals(BUY)) {
                    buyAmount += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File: " + fromFile + " not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fromFile, e);
        }
    }

    private void writeFile(String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file))) {
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator());
            bufferedWriter.write("buy," + buyAmount + System.lineSeparator());
            bufferedWriter.write("result," + (supplyAmount - buyAmount) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: " + toFile, e);
        }
    }
}
