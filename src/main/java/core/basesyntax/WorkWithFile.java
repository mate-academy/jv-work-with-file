package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    static final int ZEROELEMENT = 0;
    static final int FIRSTELEMENT = 1;
    static final String SUPPLY = "supply";
    static final String BUY = "buy";
    static final String RESULT = "result";
    static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = prepareReport(data);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String toFileName, String statistic) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(statistic);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
    }

    private String prepareReport(List<String> data) {
        int supplySum = 0;
        int buySum = 0;
        final String separator = System.lineSeparator();
        for (String element : data) {
            String[] elements = element.split(COMA);
            if (elements[ZEROELEMENT].equals(SUPPLY)) {
                supplySum += Integer.parseInt(elements[FIRSTELEMENT]);
            } else if (elements[ZEROELEMENT].equals(BUY)) {
                buySum += Integer.parseInt(elements[FIRSTELEMENT]);
            }
        }
        int result = supplySum - buySum;
        return (SUPPLY + COMA + supplySum + separator
                + BUY + COMA + buySum + separator
                + RESULT + COMA + result);
    }
}

