package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(getResultOfFile(fromFileName));
            bufferedWriter.close();
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
                if (line.split("\\,")[0].startsWith("supply")) {
                    supplyNum += Integer.parseInt(line.split("\\,")[1]);
                } else if (line.split("\\,")[0].startsWith("buy")) {
                    buyNum += Integer.parseInt(line.split("\\,")[1]);
                }
            }
            int result = supplyNum - buyNum;
            return "supply," + supplyNum + System.lineSeparator() + "buy," + buyNum
                    + System.lineSeparator() + "result," + result;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + e);
        }
    }
}
