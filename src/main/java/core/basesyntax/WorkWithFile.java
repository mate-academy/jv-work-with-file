package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File incomingFile = new File(fromFileName);

        try {
            List<String> strings = Files.readAllLines(incomingFile.toPath());
            String[] array = strings.toArray(new String[0]);

            int supplyAmount = 0;
            int buyAmount = 0;
            int result = 0;

            for (String data : array) {
                String[] arrayData = data.split(",");

                if (arrayData[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(arrayData[1]);
                }
                if (arrayData[0].equals("buy")) {
                    buyAmount += Integer.parseInt(arrayData[1]);
                }
            }

            result = supplyAmount - buyAmount;

            File outcomingFile = new File(toFileName);
            FileWriter fileWriter = new FileWriter(outcomingFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            try {
                bufferedWriter.write("supply" + "," + supplyAmount);
                bufferedWriter.newLine();
                bufferedWriter.write("buy" + "," + buyAmount);
                bufferedWriter.newLine();
                bufferedWriter.write("result" + "," + result);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file");
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }
}
