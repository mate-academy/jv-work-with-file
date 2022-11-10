package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringFromFile = readData(fromFileName);
        String[] data = createData(stringFromFile);
        writeData(data, toFileName);
    }

    private List<String> readData(String fromFileName) {
        File file = new File(fromFileName);
        List<String> stringList;
        try {
            stringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return stringList;
    }

    private String[] createData(List<String> stringList) {
        String[] data = {"supply,", "buy,", "result,"};
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue = 0;

        for (String string : stringList) {
            String[] split = string.split(",");
            if (split[0].equals("supply")) {
                supplyValue = supplyValue + Integer.parseInt(split[1]);
            }
            if (split[0].equals("buy")) {
                buyValue = buyValue + Integer.parseInt(split[1]);
            }
        }
        resultValue = supplyValue - buyValue;
        data[0] = data[0] + supplyValue;
        data[1] = data[1] + buyValue;
        data[2] = data[2] + resultValue;
        return data;
    }

    private void writeData(String[] data, String toFileName) {
        File file = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();

        for (String stringData : data) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(stringBuilder.append(stringData)
                        .append(System.lineSeparator()).toString());
                stringBuilder.setLength(0);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "test.csv");
    }
}
