package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        File fileInput = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splittedLine = line.split(",");
                if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                    supplyTotal += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                } else {
                    buyTotal += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open Input file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from Input file", e);
        }
        writeToFile(toFileName, supplyTotal, buyTotal);
    }

    private void writeToFile(String toFileName, int supplyTotal, int buyTotal) {
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write("supply," + supplyTotal + System.lineSeparator());
            bufferedWriter.write("buy," + buyTotal + System.lineSeparator());
            bufferedWriter.write("result," + (supplyTotal - buyTotal) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't create Output file or write to file", e);
        }
    }
}
