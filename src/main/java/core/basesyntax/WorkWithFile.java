package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File file = new File(fromFileName);
            Object[] strings = Files.readAllLines(file.toPath()).toArray();

            int supplyCount = 0;
            int buyCount = 0;
            for (int i = 0; i < strings.length;i++) {
                if (strings[i].toString().contains("supply")) {
                    supplyCount += Integer.parseInt(strings[i].toString().split(",")[1]);
                } else {
                    buyCount += Integer.parseInt(strings[i].toString().split(",")[1]);
                }
            }

            int result = supplyCount - buyCount;
            try (BufferedWriter fileResult = new BufferedWriter(new FileWriter(toFileName,true))) {
                fileResult.write("supply," + supplyCount + System.lineSeparator());
                fileResult.write("buy," + buyCount + System.lineSeparator());
                fileResult.write("result," + result + System.lineSeparator());
                fileResult.flush();
            } catch (IOException err) {
                throw new RuntimeException("Can not write data to file" + toFileName, err);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can not read data form file: " + fromFileName, e);
        }
    }
}
