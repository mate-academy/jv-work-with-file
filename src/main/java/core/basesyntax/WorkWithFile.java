package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_PATTERN = "supply";
    private static final String BUY_PATTERN = "buy";
    private static final String RESULT_PATTERN = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        StringBuilder readFile = readFile(fromFileName);
        String[] lines = readFile.toString().split(System.lineSeparator());
        for (String line : lines) {
            String[] split = line.split(",");
            if (split[0].equals(SUPPLY_PATTERN)) {
                supplyCounter += Integer.parseInt(split[1]);
            } else {
                buyCounter += Integer.parseInt(split[1]);
            }
        }
        int result = supplyCounter - buyCounter;
        writeFile(toFileName, new String[]{SUPPLY_PATTERN + "," + supplyCounter,
                BUY_PATTERN + "," + buyCounter, RESULT_PATTERN + "," + result});
    }

    private StringBuilder readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found with name " + fileName);
        }
        return stringBuilder;
    }

    private void writeFile(String toFile, String[] lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(toFile)))) {
            for (String line : lines) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
