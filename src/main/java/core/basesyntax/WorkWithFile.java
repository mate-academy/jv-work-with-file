package core.basesyntax;

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
            String[] linesArray = Files.readAllLines(path).toArray(new String[0]);
            for (int i = 0; i < linesArray.length; i++) {
                if (linesArray[i].split(",")[0].equals("supply")) {
                    supplyCounter += Integer.parseInt(linesArray[i].split(",")[1]);
                } else if (linesArray[i].split(",")[0].equals("buy")) {
                    buyCounter += Integer.parseInt(linesArray[i].split(",")[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
        String report = "supply," + supplyCounter + "\n"
                + "buy," + buyCounter + "\n" + "result," + (supplyCounter - buyCounter);
        try {
            Files.writeString(pathToSave, report);
        } catch (IOException e) {
            throw new RuntimeException(" Can't write to File");
        }
    }
}
