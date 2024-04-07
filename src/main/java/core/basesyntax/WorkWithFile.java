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
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = calculateSupply(fromFileName);
        int buy = calculateBuy(fromFileName);
        int result = supply - buy;
        writeToFile(toFileName, supply, buy, result);
        printToConsole(supply, buy, result);
    }

    private int calculateSupply(String fileName) {
        return calculate(fileName, SUPPLY);
    }

    private int calculateBuy(String fileName) {
        return calculate(fileName, BUY);
    }

    private int calculate(String fileName, String operation) {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line = reader.readLine();
            while (line != null) {
                String[] separatedLine = line.split(COMMA);
                if (separatedLine[0].equals(operation)) {
                    total += Integer.parseInt(separatedLine[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return total;
    }

    private void writeToFile(String fileName, int supply, int buy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
            writer.write(SUPPLY + COMMA + supply + "\n");
            writer.write(BUY + COMMA + buy + "\n");
            writer.write(RESULT + COMMA + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private void printToConsole(int supply, int buy, int result) {
        System.out.println("Supply: " + supply + "\n" + "Buy: " + buy + "\n" + "Result: " + result);
    }
}
