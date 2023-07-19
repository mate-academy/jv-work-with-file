package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder dataFromFile = new StringBuilder();
            String elementFromData = bufferedReader.readLine();
            while (elementFromData != null) {
                dataFromFile.append(elementFromData).append(" ");
                elementFromData = bufferedReader.readLine();
            }

            String[] rows = dataFromFile.toString().split(" ");
            String[] uniqueWords = new String[rows.length];
            int[] sums = new int[rows.length];
            int uniqueCount = 0;
            int supplySum = 0;
            int buySum = 0;

            for (String row : rows) {
                String[] parts = row.split(",");
                String word = parts[0].trim();
                int value = Integer.parseInt(parts[1]);
                int index = -1;

                for (int i = 0; i < uniqueCount; i++) {
                    if (uniqueWords[i].equals(word)) {
                        index = i;
                    }
                }
                if (index < 0) {
                    uniqueWords[uniqueCount] = word;
                    sums[uniqueCount] = value;
                    uniqueCount++;
                } else {
                    sums[index] += value;
                }
                if (word.equals("supply")) {
                    supplySum += value;
                } else if (word.equals("buy")) {
                    buySum += value;
                }
            }
            int difference = supplySum - buySum;
            for (int i = 0; i < uniqueCount - 1; i++) {
                for (int j = 0; j < uniqueCount - i - 1; j++) {
                    if (sums[j] < sums[j + 1]) {
                        int tempSum = sums[j];
                        sums[j] = sums[j + 1];
                        sums[j + 1] = tempSum;

                        String tempWord = uniqueWords[j];
                        uniqueWords[j] = uniqueWords[j + 1];
                        uniqueWords[j + 1] = tempWord;
                    }
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (int i = 0; i < uniqueCount; i++) {
                bufferedWriter.write(uniqueWords[i] + "," + sums[i]);
                bufferedWriter.newLine();
            }
            bufferedWriter.write("result," + difference);
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException("Houston, we have a problems", e);
        }
    }
}
