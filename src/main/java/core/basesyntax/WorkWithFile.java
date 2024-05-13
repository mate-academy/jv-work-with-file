package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public static final String REGEX_TO_SPLIT_LINE = ",";
    public static final String SUPPLY_VALUE = "supply";
    public static final String BUY_VALUE = "buy";
    public static final String RESULT_VALUE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        ResultsDto resultsDto = readDataFromFile(fromFileName);
        writeDataToFile(toFileName, resultsDto.supplyResult(), resultsDto.buyResult());
    }

    private ResultsDto readDataFromFile(String fromFileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        return splitLinesAndGetResults(lines);
    }

    private ResultsDto splitLinesAndGetResults(List<String> lines) {
        int supplyResult = 0;
        int buyResult = 0;

        for (String line : lines) {
            String[] words = line.split(REGEX_TO_SPLIT_LINE);
            if (words[0].equals(SUPPLY_VALUE)) {
                supplyResult += Integer.parseInt(words[1]);
            } else {
                buyResult += Integer.parseInt(words[1]);
            }
        }

        return new ResultsDto(supplyResult, buyResult);
    }

    private void writeDataToFile(String toFileName, int supplyResult, int buyResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            processWritingData(bufferedWriter, supplyResult, buyResult);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private void processWritingData(BufferedWriter bufferedWriter,
                                    int supplyResult, int buyResult) throws IOException {
        bufferedWriter.write(SUPPLY_VALUE + REGEX_TO_SPLIT_LINE + supplyResult + "\n");
        bufferedWriter.write(BUY_VALUE + REGEX_TO_SPLIT_LINE + buyResult + "\n");
        bufferedWriter.write(RESULT_VALUE + REGEX_TO_SPLIT_LINE + (supplyResult - buyResult));
    }
}
