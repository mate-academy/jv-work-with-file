package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ARRAY_SIZE = 100;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String[] names = new String[ARRAY_SIZE];
            int[] sums = new int[ARRAY_SIZE];

            int size = 0;

            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] parts = readLine.split("\\W+");
                String name = parts[0];
                int value = Integer.parseInt(parts[1]);

                boolean nameFound = false;
                for (int i = 0; i < size; i++) {
                    if (names[i] != null && names[i].equals(name)) {
                        sums[i] += value;
                        nameFound = true;
                        break;
                    }
                }
                if (!nameFound) {
                    names[size] = name;
                    sums[size] = value;
                    size++;
                }
                readLine = bufferedReader.readLine();
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                for (int i = 0; i < sums.length; i++) {
                    String contName = names[i];
                    int contSum = sums[i];
                    for (int j = i + 1; j < sums.length; j++) {
                        if (sums[i] < sums[j]) {
                            names[i] = names[j];
                            names[j] = contName;

                            sums[i] = sums[j];
                            sums[j] = contSum;
                        }
                    }
                }

                for (int i = 0; i < size; i++) {
                    bufferedWriter.write(names[i] + "," + sums[i] + System.lineSeparator());
                }
                int sumResult = sums[0] - sums[1];
                bufferedWriter.write("result," + sumResult);
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file " + toFileName + " " + e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName + " " + e);
        }
    }
}
