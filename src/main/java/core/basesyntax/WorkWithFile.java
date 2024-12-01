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
            String readedFile = String.valueOf(Files.readAllLines(path))
                    .replaceAll("[^sa-zA-Z0-9]", ",");
            String[] separatedFile = readedFile.split(",");
            for (int i = 0; i < separatedFile.length; i++) {
                if (separatedFile[i].trim().equals("supply")) {
                    supplyCounter += Integer.parseInt(separatedFile[ i + 1]);
                } else if (separatedFile[i].trim().equals("buy")) {
                    buyCounter += Integer.parseInt(separatedFile[ i + 1]);
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
