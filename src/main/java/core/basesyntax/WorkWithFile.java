package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileFromFileName = new File(fromFileName);
        File fileToFileName = new File(toFileName);
        Map<String, Integer> result = new HashMap<>();

        if (!fileFromFileName.exists()) {
            return;
        }

        readFromFileAndPutDataToMap(fileFromFileName.getName(), result);

        writeToFileDataFromMap(fileToFileName.getName(), result);
    }

    private void writeToFileDataFromMap(String toFileName, Map<String, Integer> result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = result.getOrDefault("supply", 0);
            int buy = result.getOrDefault("buy", 0);
            int finalResult = supply - buy;

            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + finalResult);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't write data to the file " + toFileName, ioException);
        }
    }

    private void readFromFileAndPutDataToMap(String fromFileName, Map<String, Integer> result) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineData;

            while ((lineData = bufferedReader.readLine()) != null) {
                String[] wordsAndNumber = lineData.split(",");
                if (wordsAndNumber.length == 2) {
                    String word = wordsAndNumber[0];
                    int number = Integer.parseInt(wordsAndNumber[1]);
                    result.put(word, result.getOrDefault(word, 0) + number);
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Can't read data from the file "
                    + fromFileName, ioException);
        }
    }
}
