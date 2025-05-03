package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeData(getData(fromFileName),toFileName);
    }

    private String getData(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder report = new StringBuilder();
        int fullBuyValue = 0;
        int fullSupValue = 0;
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                dataFromFile.append((char) value);
                value = bufferedReader.read();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while reading from file", e);
        }
        String[] array = dataFromFile.toString().split("\\W+");
        int supValue;
        int buyValue;
        for (int j = 0; j < array.length - 1; j += 2) {
            if ((array[j]).equals("supply")) {
                supValue = Integer.parseInt(array[j + 1]);
                fullSupValue += supValue;
            } else if ((array[j]).equals("buy")) {
                buyValue = Integer.parseInt(array[j + 1]);
                fullBuyValue += buyValue;
            }
        }
        report.append("supply,")
                .append(fullSupValue)
                .append(System.lineSeparator())
                .append("buy,")
                .append(fullBuyValue)
                .append(System.lineSeparator())
                .append("result,")
                .append(fullSupValue - fullBuyValue);
        return report.toString();
    }

    private void writeData(String data, String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("can't create file");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("can't write data");
        }
    }
}

