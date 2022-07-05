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
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        calculateReportData(fromFileName);
        outputReportToFile(toFileName);
    }

    private void calculateReportData(String fromFileName) {
        File fileInput = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileInput));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                switch (splitLine[OPERATION_TYPE_INDEX]) {
                    case "supply": {
                        supplyTotal += Integer.parseInt(splitLine[AMOUNT_INDEX]);
                        break;
                    }
                    case "buy": {
                        buyTotal += Integer.parseInt(splitLine[AMOUNT_INDEX]);
                        break;
                    }
                    default:
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open Input file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from Input file", e);
        }
    }

    private void outputReportToFile(String toFileName) {
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
