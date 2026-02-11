package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private int supplyAmount;
    private int buyAmount;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        getDataFromFile(fromFileName);
        writeDataToFile(toFileName);
    }

    private void getDataFromFile(String fileName) {
        File incomingFile = new File(fileName);
        supplyAmount = 0;
        buyAmount = 0;
        result = 0;

        try {
            List<String> strings = Files.readAllLines(incomingFile.toPath());
            String[] array = strings.toArray(new String[0]);

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
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeDataToFile(String fileName) {
        File outcomingFile = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(outcomingFile);

            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
                try {
                    bufferedWriter.write("supply" + "," + supplyAmount);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data", e);
                }
                try {
                    bufferedWriter.write("buy" + "," + buyAmount);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data", e);
                }
                try {
                    bufferedWriter.write("result" + "," + result);
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data", e);
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't create FileWriter", e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
