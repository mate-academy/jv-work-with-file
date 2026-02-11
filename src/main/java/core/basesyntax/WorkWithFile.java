package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = getDataFromFile(fromFileName);
        writeDataToFile(toFileName, data);
    }

    private int[] getDataFromFile(String fileName) {
        File incomingFile = new File(fileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        int result = 0;

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

            return new int[]{supplyAmount, buyAmount, result};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeDataToFile(String fileName, int[] data) {
        File outcomingFile = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(outcomingFile);

            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
                try {
                    bufferedWriter.write("supply" + "," + data[SUPPLY_INDEX]);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data", e);
                }
                try {
                    bufferedWriter.write("buy" + "," + data[BUY_INDEX]);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data", e);
                }
                try {
                    bufferedWriter.write("result" + "," + data[RESULT_INDEX]);
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
