package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {

    private int result = 0;
    private int supplyResult = 0;
    private int buyResult = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));
            for (String line : lines) {
                String[] parts = line.split(",");
                String operation = parts[0];
                String amountStr = parts[1];
                int amount = Integer.parseInt(amountStr);
                if (operation.equals("supply")) {
                    supplyResult += amount;
                } else if (operation.equals("buy")) {
                    buyResult += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading file error" + " "
                    + fromFileName + " " + e);
        }
        result = supplyResult - buyResult;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyResult);
            writer.newLine();
            writer.write("buy," + buyResult);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Writing file error" + " "
                    + toFileName + " " + e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workwithfile = new WorkWithFile();
        workwithfile.getStatistic("banana.csv", "bananaResult.csv");
    }
}
