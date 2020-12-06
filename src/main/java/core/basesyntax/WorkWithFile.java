package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private List<String> writeToFile = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        readFile(fromFileName);
        writeFile(toFileName);
    }

    private void readFile(String readFile) {
        List<String> list;
        try {
            list = Files.readAllLines(new File(readFile).toPath());
        } catch (IOException e) {
            throw new RuntimeException("File not found!", e);
        }

        int supplySum = 0;
        int buySum = 0;
        for (String string : list) {
            if (SUPPLY.equals(string.split(",")[0])) {
                supplySum += Integer.parseInt(string.split(",")[1]);
            } else {
                buySum += Integer.parseInt(string.split(",")[1]);
            }
        }

        writeToFile.add(SUPPLY + "," + supplySum);
        writeToFile.add(BUY + "," + buySum);
        writeToFile.add(RESULT + "," + (supplySum - buySum));
    }

    private void writeFile(String writeFile) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(new File(writeFile)))) {
            for (String information : writeToFile) {
                bufferedWriter.write(information + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file!", e);
        }
    }
}
