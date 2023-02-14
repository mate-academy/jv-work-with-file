package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "///";
    private static final int INDEX_0 = 0;
    private static final int INDEX_1 = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] text = readFile(fromFileName);
        int[] data = calculateDataForReport(text);
        String dataForPrint = createReport(data);
        putDataToFile(dataForPrint,toFileName);
    }

    private String[] readFile(String fileName) {
        StringBuilder string = new StringBuilder();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String text = bufferedReader.readLine();
            while (text != null) {
                string.append(text).append(DELIMITER);
                text = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file!" + fileName, e);
        }
        if (string.toString().isEmpty()) {
            String[] empty = {};
            return empty;
        }
        return string.toString().split(DELIMITER);
    }

    private int[] calculateDataForReport(String[] str) {
        int[] result = new int[]{0, 0};
        for (int i = 0; i < str.length; i++) {
            String[] strings = str[i].split(",");
            if (strings[INDEX_0].equals("supply")) {
                result[INDEX_0] += Integer.parseInt(strings[INDEX_1]);
            } else if (strings[INDEX_0].equals("buy")) {
                result[INDEX_1] += Integer.parseInt(strings[INDEX_1]);
            }
        }
        return result;
    }

    private String createReport(int[] data) {
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(data[0]).append("\n");
        result.append("buy,").append(data[1]).append("\n");
        result.append("result,").append(data[0] - data[1]).append("\n");
        return result.toString();
    }

    private void putDataToFile(String text, String putFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(putFile))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can`t put data to file!" + putFile,e);
        }
    }
}
