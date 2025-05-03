package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String FIRST_PARAMETER = "supply";
    private static final String SECOND_PARAMETER = "buy";
    private static final String THIRD_PARAMETER = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringsFromFile = readFromFile(fromFileName);
        String report = createReport(stringsFromFile);
        writeReport(report, toFileName);
    }

    private List<String> readFromFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName + ".", e);
        }
    }

    private String createReport(List<String> readFromFile) {
        int supplySum = 0;
        int buySum = 0;
        for (String element : readFromFile) {
            if (element.charAt(0) == FIRST_PARAMETER.charAt(0)) {
                supplySum += Integer.parseInt(element
                    .substring(FIRST_PARAMETER.length() + 1));
            }
            if (element.charAt(0) == SECOND_PARAMETER.charAt(0)) {
                buySum += Integer.parseInt(element
                    .substring(SECOND_PARAMETER.length() + 1));
            }
        }
        return FIRST_PARAMETER + SEPARATOR + supplySum + System.lineSeparator()
            + SECOND_PARAMETER + SEPARATOR + buySum + System.lineSeparator()
            + THIRD_PARAMETER + SEPARATOR + (supplySum - buySum);
    }

    private void writeReport(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName + ".", e);
        }
    }
}

