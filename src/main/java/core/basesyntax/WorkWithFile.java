package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> strings = new ArrayList<>();
        int sum;
        try {
            try (InputStream inputStream = new FileInputStream(fromFileName);
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream))) {
                while (bufferedReader.ready()) {
                    strings.add(bufferedReader.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                String[] first = strings.get(i).split(",");
                String[] second = strings.get(j).split(",");
                if (first[0].equals(second[0])) {
                    sum = (Integer.parseInt(first[1])) + (Integer.parseInt(second[1]));
                    strings.set(i, first[0] + "," + sum);
                    strings.remove(j);
                    j--;
                }
            }
        }
        if (strings.get(0).length() < strings.get(1).length()) {
            Collections.swap(strings, 0, 1);
        }
        StringBuilder result = new StringBuilder();
        String[] supply = strings.get(0).split(",");
        String[] buy = strings.get(1).split(",");
        int difference = Integer.parseInt(supply[1]) - Integer.parseInt(buy[1]);
        result.append("result").append(",").append(difference);
        strings.add(result.toString());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            File file = new File(toFileName);
            if (file.length() == 0) {
                for (String toWrite : strings) {
                    bufferedWriter.write(toWrite + System.lineSeparator());
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
