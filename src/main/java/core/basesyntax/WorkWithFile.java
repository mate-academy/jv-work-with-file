package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        Path path = Path.of(fromFileName);
        Path pathToSave = Path.of(toFileName);
        try {
            BufferedReader readedFile = new BufferedReader(new FileReader(String.valueOf(path)));
            String[] linesArra = Files.readAllLines(path).toArray(new String[0]);
            for (int i = 0; i < linesArra.length; i++) {
                if (linesArra[i].split(",")[0].equals("supply")) {
                    supplyCounter += Integer.parseInt(linesArra[i].split(",")[1]);
                } else {
                    buyCounter += Integer.parseInt(linesArra[i].split(",")[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String report = "supply," + supplyCounter + "\n"
                + "buy," + buyCounter + "\n" + "result," + (supplyCounter - buyCounter);
        try {
            Files.writeString(pathToSave, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
