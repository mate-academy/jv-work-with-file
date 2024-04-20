package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final int ROWS_IN_FILE = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        file.delete();
        String[] inputFile = fileReader(fromFileName);
        fileWriter(inputFile, toFileName);
    }

    private String[] fileReader(String fileName) {
        File file = new File(fileName);
        int supplyNum = 0;
        int buyNum = 0;
        int result;
        String[] answer = new String[ROWS_IN_FILE];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }

            String[] dataArray = stringBuilder.toString().split(" ");

            for (String string : dataArray) {
                String[] arrayForNum = string.split(",");
                if (arrayForNum[0].equals(SUPPLY)) {
                    supplyNum += Integer.parseInt(arrayForNum[1]);
                } else {
                    buyNum += Integer.parseInt(arrayForNum[1]);
                }
            }

            result = supplyNum - buyNum;
            answer[0] = "supply," + supplyNum;
            answer[1] = "buy," + buyNum;
            answer[2] = "result," + result;

            return answer;

        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }

    private void fileWriter(String[] data, String name) {
        File file = new File(name);
        String separator = System.lineSeparator();
        for (String str : data) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(str);
                bufferedWriter.write(separator);
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        }
    }
}
