package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String START_SUPPLY_STRING = "supply,";
    private static final String START_BUY_STRING = "buy,";
    private static final String START_RESULT_STRING = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                textFromFile.append(NEW_LINE).append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("You have an IOException", e);
        }
        String[] arrayOfLines = textFromFile.toString().split(NEW_LINE);
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : arrayOfLines) {
            if (line.startsWith(START_SUPPLY_STRING)) {
                supplyAmount += Integer.parseInt(line.substring(7));
            }
            if (line.startsWith(START_BUY_STRING)) {
                buyAmount += Integer.parseInt(line.substring(4));
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(START_SUPPLY_STRING + supplyAmount + NEW_LINE
                    + START_BUY_STRING + buyAmount + NEW_LINE
                    + START_RESULT_STRING + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("You have the IOException", e);
        }
    }
}
