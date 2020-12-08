package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String COMA = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> writtenList = readFile(fromFileName);
        writeFile(toFileName, writtenList);
    }

    private List<String> readFile(String readFile) {
        List<String> list;
        try {
            list = Files.readAllLines(new File(readFile).toPath());
        } catch (IOException e) {
            throw new RuntimeException("File not found!", e);
        }

        String supply = "supply";
        int supplySum = 0;
        int buySum = 0;
        for (String string : list) {
            if (supply.equals(string.split(COMA)[INDEX_ZERO])) {
                supplySum += Integer.parseInt(string.split(COMA)[INDEX_ONE]);
            } else {
                buySum += Integer.parseInt(string.split(COMA)[INDEX_ONE]);
            }
        }

        List<String> writeToFile = new ArrayList<>();
        String buy = "buy";
        String result = "result";
        writeToFile.add(supply + COMA + supplySum);
        writeToFile.add(buy + COMA + buySum);
        writeToFile.add(result + COMA + (supplySum - buySum));
        return writeToFile;
    }

    private void writeFile(String writeFile, List<String> writerList) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(new File(writeFile)))) {
            for (String information : writerList) {
                bufferedWriter.write(information + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file!", e);
        }
    }
}
