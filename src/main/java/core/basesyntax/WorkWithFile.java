package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE = 0;
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        int supplyCounter = 0;
        int buyCounter = 0;

        try (FileReader fileReader = new FileReader(fromFileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataFromFile = line.split(",");
                int amount = Integer.parseInt(dataFromFile[AMOUNT]);

                if (dataFromFile[OPERATION_TYPE].equals("supply")) {
                    supplyCounter += amount;
                } else {
                    buyCounter += amount;
                }

            }
        } catch (IOException e) {
            throw new IOException("File can not be open", e);
        }

        String[][] data = {{"supply,", String.valueOf(supplyCounter)},
                           {"buy,", String.valueOf(buyCounter)},
                           {"result,", String.valueOf(supplyCounter - buyCounter)}};

        File newFile = new File(toFileName);

        try (FileWriter fileWriter = new FileWriter(newFile)) {
            for (String[] string : data) {
                fileWriter.write(string[OPERATION_TYPE] + string[AMOUNT]);
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException("can not write to file", e);
        }
    }
}
