package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String value = bufferedReader.readLine();

            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file ", e);
        }
        String[] readFile = builder.toString().split(System.lineSeparator());
        String[] names = new String[readFile.length];
        Integer[] numbers = new Integer[readFile.length];
        for (int i = 0; i < readFile.length; i++) {
            String[] divided = readFile[i].split(",");
            names[i] = divided[0];
            numbers[i] = Integer.parseInt(divided[1]);
        }
        String defaultName = names[0];
        int sum = 0;
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < readFile.length; i++) {
            for (int j = 0; j < readFile.length; j++) {
                if (names[j].equals(defaultName)) {
                    sum += numbers[i];

                }
            }
            builder.append(names[i]).append(",").append(sum).append(System.lineSeparator());
            defaultName = names[i];
        }
        String result = builder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
