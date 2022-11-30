package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("banana.csv", "test1.csv");
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
                if (i % 3 == 0 && i != 9) {
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
        String[] split = stringBuilder.toString().split("\r\n"); //\\W+
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String word : split) {
            int value = Integer.parseInt(word.substring(word.indexOf(",") + 1));
            if (word.startsWith("s")) {
                supply += value;
            } else if (word.startsWith("b")) {
                buy += value;
            }
        }
        result = supply - buy;
        String[] array = new String[9];
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
