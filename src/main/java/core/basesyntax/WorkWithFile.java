package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";

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
                if (everyLine[0].equals(SUPPLY_WORD)) {
                    resultSupply += Integer.parseInt(everyLine[1]);
                } else {
                    resultBuy += Integer.parseInt(everyLine[1]);
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
        try {
            FileWriter output = new FileWriter(toFileName);
            output.write(result);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
