package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] textArray = fileTextToArray(fromFileName);
        int[] supplyAndBuy = countSupplyAndBuy(textArray);
        writeTextToFile(toFileName, supplyAndBuy);
    }

    private String[] fileTextToArray(String fromFileName) {
        String text = "";
        try {
            text = Files.readString(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + fromFileName, e);
        }
        return text.split(System.lineSeparator());
    }

    private int[] countSupplyAndBuy(String[] textArray) {
        int supplyInt = 0;
        int buyInt = 0;
        for (String element : textArray) {
            String[] elementSplit = element.split(",");
            if (elementSplit[0].equals("supply")) {
                supplyInt += Integer.parseInt(elementSplit[1]);
            } else {
                buyInt += Integer.parseInt(elementSplit[1]);
            }
        }
        return new int[] {supplyInt, buyInt};
    }

    private void writeTextToFile(String toFileName, int[] supplyAndBuy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(System.lineSeparator() + "supply," + supplyAndBuy[0]
                    + System.lineSeparator() + "buy," + supplyAndBuy[1]
                    + System.lineSeparator() + "result," + (supplyAndBuy[0] - supplyAndBuy[1]));
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + toFileName, e);
        }
    }
}
