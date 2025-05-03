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
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileFromFileName = new File(fromFileName);
        Map<String, Integer> result = new HashMap<>();

        if (!fileFromFileName.exists()) {
            return;
        }

        readFromFile(fileFromFileName.getName(), result);

        writeToFileDataFromMap(toFileName, result);
    }

    private void writeToFileDataFromMap(String toFileName, Map<String, Integer> result) {
        File fileToFileName = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToFileName))) {
            int supply = result.getOrDefault("supply", OPERATION_TYPE_INDEX);
            int buy = result.getOrDefault("buy", OPERATION_TYPE_INDEX);
            int finalResult = supply - buy;

            bufferedWriter.write("supply" + SEPARATOR + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy" + SEPARATOR + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result" + SEPARATOR + finalResult);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't write data to the file "
                    + fileToFileName.getName(), ioException);
        }
    }

    private void readFromFile(String fromFileName, Map<String, Integer> result) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String lineData;

            while ((lineData = bufferedReader.readLine()) != null) {
                putDataToMap(result, lineData);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Can't read data from the file "
                    + fromFileName, ioException);
        }
    }

    private void putDataToMap(Map<String, Integer> result, String lineData) {
        String[] wordsAndNumber = lineData.split(SEPARATOR);
        if (wordsAndNumber.length == 2) {
            String word = wordsAndNumber[OPERATION_TYPE_INDEX];
            int number = Integer.parseInt(wordsAndNumber[OPERATION_VALUE_INDEX]);
            result.put(word, result.getOrDefault(word, OPERATION_TYPE_INDEX) + number);
        }
    }
}
