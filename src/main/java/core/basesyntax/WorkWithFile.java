package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getData(fromFileName);
        writeData(data,toFileName);
    }

    public String getData(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder report = new StringBuilder();
        int supValue;
        int buyValue;
        int fullBuyValue = 0;
        int fullSupValue = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int value = bufferedReader.read();
            StringBuilder dataFromFile = new StringBuilder();
            while (value != -1) {
                dataFromFile.append((char) value);
                value = bufferedReader.read();
            }
            String[] array = dataFromFile.toString().split("\\W+");
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
        } catch (Exception e) {
            throw new RuntimeException("Something wen wrong", e);
        }
        return report.toString();
    }

    public void writeData(String data, String toFile) {
        File file = new File(toFile);
        try {
            file.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("can't create file");
        }
        try {
            Files.write(file.toPath(),data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}

