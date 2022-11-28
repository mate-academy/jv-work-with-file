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
        String text = readFromFileName(fromFileName);
        writeToFileName(text, toFileName);
    }

    public String readFromFileName(String fromFileName) {
        int resultSupply = 0;
        int resultBuy = 0;
        try {
            File file = new File(fromFileName);
            List<String> input = Files.readAllLines(file.toPath());
            String[] lines = input.toArray(new String[input.size()]);
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
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public void writeToFileName(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            //FileWriter output = new FileWriter(toFileName);
            bufferedWriter.write(result);
            //bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
