package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] text = openFile(fromFileName);
        int[] data = calcutData(text);
        String[] dataForPrint = printData(data);
        putDataToFile(dataForPrint,toFileName);
    }

    private String[] openFile(String fileName) {
        StringBuilder string = new StringBuilder();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String text = bufferedReader.readLine();
            while (text != null) {
                string.append(text).append("///");
                text = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file!", e);
        }
        if (string.toString().isEmpty()) {
            String[] empty = {};
            return empty;
        } else {
            return string.toString().split("///");
        }
    }

    private int[] calcutData(String[] str) {
        int[] result = new int[]{0,0};
        for (int i = 0; i < str.length; i++) {
            String[] strings = str[i].split(",");
            if (strings[0].equals("supply")) {
                result[0] += Integer.parseInt(strings[1]);
            } else if (strings[0].equals("buy")) {
                result[1] += Integer.parseInt(strings[1]);
            }
        }
        return result;
    }

    private String[] printData(int[] arr) {
        String[] text = new String[3];
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(arr[0]);
        text[0] = result.toString();
        result.setLength(0);
        result.append("buy,").append(arr[1]);
        text[1] = result.toString();
        result.setLength(0);
        result.append("result,").append(arr[0] - arr[1]);
        text[2] = result.toString();
        return text;
    }

    private void putDataToFile(String[] text, String putFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(putFile))) {
            /*File file = new File(putFile);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);*/

            for (int i = 0; i < text.length; i++) {
                bufferedWriter.write(text[i]);
                bufferedWriter.write("\n");
            }
            /*bufferedWriter.close();*/
        } catch (IOException e) {
            throw new RuntimeException("Can`t put data to file!",e);
        }
    }
}
