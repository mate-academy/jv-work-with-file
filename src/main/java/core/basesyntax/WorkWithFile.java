package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private String readFromFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedLine = line.split(COMMA);
                if (line.contains("supply")) {
                    supplySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                } else {
                    buySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e);
        }
        return makeReport(buySum, supplySum);
    }

    private String makeReport(int buySum, int supplySum) {
        return "supply" + "," + supplySum + System.lineSeparator()
                + "buy" + "," + buySum + System.lineSeparator()
                + "result" + "," + (supplySum - buySum) + System.lineSeparator();
    }

    private void writeToFile(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName + e);
        }
    }
}
