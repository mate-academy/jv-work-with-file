package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        final String supply = "supply";
        final String buy = "buy";
        int supplySum = 0;
        int buySum = 0;

        String[] arrayFromFile = dataFromFile.split("\\W+");

        for (int i = 0; i < arrayFromFile.length; i++) {
            if (arrayFromFile[i].equals(supply)) {
                supplySum = supplySum + Integer.parseInt(arrayFromFile[i + 1]);
            } else if (arrayFromFile[i].equals(buy)) {
                buySum = buySum + Integer.parseInt(arrayFromFile[i + 1]);
            }
        }

        return supply + "," + supplySum + System.lineSeparator()
                + buy + "," + buySum + System.lineSeparator()
                + "result" + ","
                + (supplySum - buySum)
                + System.lineSeparator();
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName, e);
        }
    }
}
