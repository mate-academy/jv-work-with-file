package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final byte VALUE_INDEX = 1;
    private static final byte TYPE_OF_OPERATION = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String data = getSumData(fromFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        try {
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }

    public String[][] getArrayData(String nameFile) {
        File file = new File(nameFile);
        List<String> stringList;
        try {
            stringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        String[][] arrayData = new String[stringList.size()][2];
        try {
            stringList = Files.readAllLines(file.toPath());
            for (int i = 0; i < stringList.size(); i++) {
                arrayData[i] = stringList.get(i).split(",");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return arrayData;
    }

    public String getSumData(String nameFile) {
        String[][] data = getArrayData(nameFile);
        int buy = 0;
        int supply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i][TYPE_OF_OPERATION].equals("supply")) {
                supply = supply + Integer.parseInt(data[i][VALUE_INDEX]);
            } else {
                buy = buy + Integer.parseInt(data[i][VALUE_INDEX]);
            }
        }
        int result = supply - buy;
        stringBuilder.append("supply,")
                .append(supply).append(System.lineSeparator())
                .append("buy,").append(buy)
                .append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }
}
