package core.basesyntax;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileFromFileName = new File(fromFileName);
        File fileToFileName = new File(toFileName);
        Map<String, Integer> result = new HashMap<>();

        if (!fileFromFileName.exists() || !fileToFileName.exists()) {
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineData;

            while ((lineData = bufferedReader.readLine()) != null) {
                String[] parts = lineData.split(" ");
                for (String part : parts) {
                    String[] wordsAndNumber = part.split(",");
                    if (wordsAndNumber.length == 2) {
                        String word = wordsAndNumber[0];
                        int number = Integer.parseInt(wordsAndNumber[1]);
                        if (result.containsKey(word)) {
                            int numberInMap = result.get(word);
                            result.put(word, numberInMap + number);
                        } else {
                            result.put(word, number);
                        }
                    }
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, ioException);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                bufferedWriter.write(entry.getKey() + "," + entry.getValue());
                bufferedWriter.newLine();
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Can't write data to the file " + toFileName, ioException);
        }
    }
}
