package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String COMA = "\\,";
    public static final int INDEX_ONE = 1;
    public static final int INDEX_ZERO = 0;
    public static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getResultOfFile(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + e);
        }
    }

    public String getResultOfFile(String fileName) {
        File file = new File(fileName);
        try {
            List<String> amount = Files.readAllLines(file.toPath());
            String[] allParth = amount.toArray(new String[0]);
            int buyNum = 0;
            int supplyNum = 0;
            for (String line : allParth) {
                if (line.split(COMA)[INDEX_ZERO].startsWith("supply")) {
                    supplyNum += Integer.parseInt(line.split(COMA)[INDEX_ONE]);
                } else if (line.split(COMA)[INDEX_ZERO].startsWith("buy")) {
                    buyNum += Integer.parseInt(line.split(COMA)[INDEX_ONE]);
                }
            }
            int result = supplyNum - buyNum;
            return "supply," + supplyNum + SEPARATOR + "buy," + buyNum
                    + SEPARATOR + "result," + result;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + e);
        }
    }

}
