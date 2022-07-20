package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String[][] strings = getSumData(fromFileName);
        String result = "result," + (Integer.parseInt(strings[0][1])
                - Integer.parseInt(strings[1][1]));
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        for (int i = 0; i < strings.length; i++) {
            try {
                Files.write(file.toPath(), (strings[i][0] + "," + strings[i][1]
                        + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        }
        try {
            Files.write(file.toPath(),result.getBytes(),StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }

    public int getCounterLine(String nameFile) {
        File file = new File(nameFile);
        int counter;
        try {
            List<String> reader = Files.readAllLines(file.toPath());
            counter = reader.size();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return counter;
    }

    public String[][] getArrayData(String nameFile) {
        File file = new File(nameFile);
        List<String> stringList;
        String[][] arrayData = new String[getCounterLine(nameFile)][2];
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

    public String[] getUniqueName(String nameFile) {
        String[][] arrayData = getArrayData(nameFile);
        StringBuilder stringBuilder = new StringBuilder();
        String[] array;
        for (int i = 0; i < arrayData.length; i++) {
            for (int j = 0; j < arrayData.length; j++) {
                if (!arrayData[i][0].equals(arrayData[j + 1][0])) {
                    stringBuilder.append(arrayData[i][0]).append(" ");
                    break;
                }
            }
        }
        array = stringBuilder.toString().split(" ");
        Arrays.sort(array, Collections.reverseOrder());
        StringBuilder stringBuilder1 = new StringBuilder(array[0]);
        for (int i = 0; i < array.length - 1; i++) {
            if (!array[i].equals(array[i + 1])) {
                stringBuilder1.append(" ").append(array[i + 1]);
            }
        }
        return stringBuilder1.toString().split(" ");
    }

    public String[][] getSumData(String nameFile) {
        String[][] data = getArrayData(nameFile);
        String[] uniqueData = getUniqueName(nameFile);
        String[][] sumData = new String[uniqueData.length][2];
        for (int i = 0; i < uniqueData.length; i++) {
            sumData[i][0] = uniqueData[i];
            sumData[i][1] = String.valueOf(0);
            for (int j = 0; j < data.length; j++) {
                if (sumData[i][0].equals(data[j][0])) {
                    int sum = Integer.parseInt(sumData[i][1]) + Integer.parseInt(data[j][1]);
                    sumData[i][1] = String.valueOf(sum);
                }
            }
        }
        return sumData;
    }
}
