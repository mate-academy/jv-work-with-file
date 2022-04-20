package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private int supplyInt = 0;
    private int buyInt = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textArray = fileTextToArray(fromFileName);
        countSupplyAndBuy(textArray);
        writeTextToFile(toFileName);
    }

    public String[] fileTextToArray(String fromFileName) {
        String text = "";
        try {
            text = Files.readString(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with the file to read" + e);
        }
        return text.split(System.lineSeparator());
    }

    public void countSupplyAndBuy(String[] textArray) {
        for (String element : textArray) {
            String[] elementSplit = element.split(",");
            if (elementSplit[0].equals("supply")) {
                supplyInt += Integer.parseInt(elementSplit[1]);
            } else {
                buyInt += Integer.parseInt(elementSplit[1]);
            }
        }
    }

    public void writeTextToFile(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(System.lineSeparator() + "supply," + supplyInt
                    + System.lineSeparator() + "buy," + buyInt
                    + System.lineSeparator() + "result," + (supplyInt - buyInt));
        } catch (IOException e) {
            throw new RuntimeException("Oops, something wrong with the file to write" + e);
        }
    }
}
