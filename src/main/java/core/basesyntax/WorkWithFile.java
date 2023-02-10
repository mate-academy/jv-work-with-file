package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private Integer buySum;
    private Integer supplySum;

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        getBuySupplySums(inputFile);
        writeReport(outputFile);
    }

    private void getBuySupplySums(File file) {
        buySum = 0;
        supplySum = 0;
        try (FileReader reader = new FileReader(file);
                BufferedReader bufReader = new BufferedReader(reader)) {
            while (bufReader.ready()) {
                String[] values = bufReader.readLine().split(",");
                if (values[0].equals("buy")) {
                    buySum += Integer.parseInt(values[1]);
                }
                if (values[0].equals("supply")) {
                    supplySum += Integer.parseInt(values[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file", e);
        }
    }

    private void writeReport(File file) {
        try (FileWriter writer = new FileWriter(file);
                BufferedWriter bufWriter = new BufferedWriter(writer)) {
            bufWriter.write("supply," + supplySum + System.lineSeparator());
            bufWriter.write("buy," + buySum + System.lineSeparator());
            bufWriter.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file", e);
        }
    }

}
