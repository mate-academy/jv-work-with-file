package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] values = readFromFile(fromFileName).split(" ");
        Arrays.sort(values, (a, b) -> b.length() - a.length());
        String[] words = new String[]{};
        List<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            String[] value = values[i].split(",");
            if (!list.contains(value[0])) {
                list.add(value[0]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            for (int i = 0; i < values.length; i++) {
                String[] value = values[i].split(",");
                if (s.equals(value[0])) {
                    count += Integer.parseInt(value[1]);
                }
            }
            stringBuilder.append(s).append(",").append(count).append(System.lineSeparator());
            count = 0;

        }

        String result = String.valueOf(calculateResult(stringBuilder.toString()));
        stringBuilder.append("result").append(",").append(result);
        writeToFile(toFileName, stringBuilder.toString());

    }

    public String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
        return stringBuilder.toString();
    }

    public void writeToFile(String fileName, String data) {
        File file = new File(fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }

    public int calculateResult(String data) {

        String[] values = data.split(System.lineSeparator());
        int result1 = Integer.parseInt(values[0].substring(values[0].indexOf(',') + 1));
        int result2 = Integer.parseInt(values[1].substring(values[1].indexOf(',') + 1));
        int result = result1 >= result2 ? result1 - result2 : result2 - result1;
        return result;
    }
}
