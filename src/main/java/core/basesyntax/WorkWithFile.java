package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String text = "";
        try {
            text = Files.readString(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] textMassive = text.split(System.lineSeparator());
        int supplyInt = 0;
        int buyInt = 0;
        for (String element : textMassive) {
            String[] elementSplit = element.split(",");
            if (elementSplit[0].equals("supply")) {
                supplyInt += Integer.parseInt(elementSplit[1]);
            } else {
                buyInt += Integer.parseInt(elementSplit[1]);
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(System.lineSeparator() + "supply," + supplyInt
                    + System.lineSeparator() + "buy," + buyInt
                    + System.lineSeparator() + "result," + (supplyInt - buyInt));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
