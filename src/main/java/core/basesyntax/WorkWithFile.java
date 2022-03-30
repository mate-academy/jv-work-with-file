package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String reader = bufferedReader.readLine();
            while (reader != null) {
                stringBuilder.append(reader).append(" ");
                reader = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
        String[] array = stringBuilder.toString().split(" ");
        Arrays.sort(array);
        int supplyCount = 0;
        int buyCount = 0;
        for (String str : array) {
            String[] temp = str.split(",");
            if (temp[0].equals("supply")) {
                supplyCount += Integer.parseInt(temp[1]);
            }
            if (temp[0].equals("buy")) {
                buyCount += Integer.parseInt(temp[1]);
            }
        }
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
        stringBuilder = new StringBuilder();
        String[] writer = {stringBuilder.append("supply")
                .append(",")
                .append(supplyCount).toString(),
                stringBuilder.append(System.lineSeparator())
                        .append("buy")
                        .append(",")
                        .append(buyCount).toString(),
                stringBuilder.append(System.lineSeparator())
                        .append("result")
                        .append(",")
                        .append(supplyCount - buyCount).toString()};
        for (String write : writer) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write(write);
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException("Can`t write data to file", e);
            }
        }
    }
}
