package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final int ZERO_INDEX = 0;
    private static final int ONE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String result = createReport(readFromFileName(fromFileName));
        writeToFile(result, toFileName);
    }

    public String[] readFromFileName(String fromFileName) {
        try {
            List<String> input = Files.readAllLines(new File(fromFileName).toPath());
            return input.toArray(new String[input.size()]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public String createReport(String[] lines) {
        int resultSupply = 0;
        int resultBuy = 0;
        for (String line : lines) {
            String[] everyLine = line.split(",");
            if (everyLine[ZERO_INDEX].equals(SUPPLY_WORD)) {
                resultSupply += Integer.parseInt(everyLine[ONE_INDEX]);
            } else {
                resultBuy += Integer.parseInt(everyLine[ONE_INDEX]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_WORD).append(",").append(resultSupply);
        builder.append(System.lineSeparator());
        builder.append(BUY_WORD).append(",").append(resultBuy).append(System.lineSeparator());
        builder.append(RESULT_WORD).append(",").append(resultSupply - resultBuy);
        return builder.toString();
    }

    public void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
