package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    private List<String> readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> dataFromFile;
        try {
            dataFromFile = Files.readAllLines(file.toPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Сan't read from file: " + fromFileName, e);
        }
        return dataFromFile;
    }

    private String generateReport(List<String> dataFromFile) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (String line : dataFromFile) {
            String[] lineSplit = line.split(DELIMITER);
            if (lineSplit[OPERATION_INDEX].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(lineSplit[AMOUNT_INDEX]);
            } else {
                sumOfBuy += Integer.parseInt(lineSplit[AMOUNT_INDEX]);
            }
        }
        return new StringBuilder().append(SUPPLY + DELIMITER)
            .append(sumOfSupply).append(System.lineSeparator())
            .append(BUY + DELIMITER).append(sumOfBuy).append(System.lineSeparator())
            .append(RESULT + DELIMITER).append(sumOfSupply - sumOfBuy).toString();
    }
}
