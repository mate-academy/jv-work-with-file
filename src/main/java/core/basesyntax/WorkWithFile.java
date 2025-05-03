package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                sb.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        String s = sb.toString();
        String[] strings = s.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("supply")) {
                supply += Integer.parseInt(strings[i + 1]);
            }
            if (strings[i].contains("buy")) {
                buy += Integer.parseInt(strings[i + 1]);
            }
        }
        int result = supply - buy;
        StringBuilder sb1 = new StringBuilder();
        sb1.append("supply,").append((supply)).append(System.lineSeparator());
        sb1.append("buy,").append((buy)).append(System.lineSeparator());
        sb1.append("result,").append((result)).append(System.lineSeparator());
        System.out.println(sb1.toString());
        File file1 = new File(toFileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true))) {
            bw.write(sb1.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file", e);
        }
    }
}

