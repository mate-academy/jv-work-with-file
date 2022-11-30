package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String FROM_FILE = "banana.csv";
    private static final String RESULT_FILE = "test1.csv";
    private static final int SIMBOLS_ARRAY = 9;
    private static final int REST = 3;

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        try {
            Files.deleteIfExists(Path.of(RESULT_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly clear result files after test ", e);
        }
        workWithFile.getStatistic(FROM_FILE, RESULT_FILE);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String[] array = workWithFile.readFromFile(fromFileName);
        workWithFile.writeToFile(toFileName, array);
    }

    private void writeToFile(String toFileName, String[] array) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            for (String word : array) {
                i++;
                bufferedWriter.write(word);
                if (i % REST == 0 && i != SIMBOLS_ARRAY) {
                    bufferedWriter.write("\r\n");
                }
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                value = value.toLowerCase();
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("No such file");
        }
        String[] split = stringBuilder.toString().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        int result;
        for (String word : split) {
            String[] arrayValues = word.split(",");
            String stringValue = arrayValues[1];
            int value = Integer.parseInt(stringValue);
            if (word.startsWith("s")) {
                supply += value;
            } else if (word.startsWith("b")) {
                buy += value;
            }
        }
        result = supply - buy;
        String[] array = new String[SIMBOLS_ARRAY];
        array[0] = "supply";
        array[1] = ",";
        array[2] = String.valueOf(supply);
        array[3] = "buy";
        array[4] = ",";
        array[5] = String.valueOf(buy);
        array[6] = "result";
        array[7] = ",";
        array[8] = String.valueOf(result);
        return array;
    }
}
