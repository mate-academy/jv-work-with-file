package core.basesyntax;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
            int buySum = 0;
            int supplySum = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            String[] lineData;

            while (value != null) {
                lineData = value.split(",");
                if (lineData[0].equals("buy")) {
                    buySum += Integer.parseInt(lineData[1]);
                } else {
                    supplySum += Integer.parseInt(lineData[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("file does not exist", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write("supply," + supplySum + '\n');
            bufferedWriter.write("buy," + buySum + '\n');
            bufferedWriter.write("result," + (supplySum - buySum) + '\n');
        } catch (IOException e) {
            throw new RuntimeException("file does not exist", e);
        }

    }
}
