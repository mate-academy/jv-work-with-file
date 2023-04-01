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
    static final int INDEX_ZERO = 0;
    static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<String> strings = new ArrayList<>();
        readFile(strings, fromFileName);
        generateReport(strings);
        writeToFile(strings, toFileName);
    }

    public void readFile(ArrayList<String> array, String fileName) {
        try {
            try (InputStream inputStream = new FileInputStream(fileName);
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream))) {
                while (bufferedReader.ready()) {
                    array.add(bufferedReader.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    public void generateReport(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                String[] currentStrings = array.get(i).split(",");
                String[] nextString = array.get(j).split(",");
                if (currentStrings[INDEX_ZERO].equals(nextString[INDEX_ZERO])) {
                    int sum = (Integer.parseInt(currentStrings[INDEX_ONE])) +
                            (Integer.parseInt(nextString[1]));
                    array.set(i, currentStrings[INDEX_ZERO] + "," + sum);
                    array.remove(j);
                    j--;
                }
            }
        }
        if (array.get(INDEX_ZERO).length() < array.get(INDEX_ONE).length()) {
            Collections.swap(array, INDEX_ZERO, INDEX_ONE);
        }
        StringBuilder result = new StringBuilder();
        String[] supply = array.get(INDEX_ZERO).split(",");
        String[] buy = array.get(INDEX_ONE).split(",");
        int difference = Integer.parseInt(supply[INDEX_ONE]) - Integer.parseInt(buy[1]);
        result.append("result").append(",").append(difference);
        array.add(result.toString());
    }

    public void writeToFile(ArrayList<String> array, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            File file = new File(fileName);
            if (file.length() == 0) {
                for (String toWrite : array) {
                    bufferedWriter.write(toWrite + System.lineSeparator());
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
