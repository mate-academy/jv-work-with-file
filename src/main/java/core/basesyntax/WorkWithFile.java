package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int buy = 0;
            int supply = 0;
            Path toPath = new File(toFileName).toPath();
            String line = reader.readLine();
            while (line != null) {
                switch (line.split(",")[OPERATION]) {
                    case "buy":
                        buy += Integer.parseInt(line.split(",")[AMOUNT]);
                        break;
                    case "supply":
                        supply += Integer.parseInt(line.split(",")[AMOUNT]);
                        break;
                    default:
                        break;
                }
                line = reader.readLine();
            }
            if (!Files.exists(toPath)) {
                Files.createFile(toPath);
            }
            Files.write(toPath, ("supply," + supply
                    + System.lineSeparator() + "buy," + buy
                    + System.lineSeparator() + "result,"
                    + (supply - buy)).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
