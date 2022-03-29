package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        File file1 = new File(fromFileName);
        StringBuilder stringBuilder1 = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder1.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] strings = stringBuilder1.toString().split(System.lineSeparator());
        String[] temp = new String[2];
        for (String string : strings) {
            temp = string.split(",");
            if (temp[0].equals("supply")) {
                supply += Integer.parseInt(temp[1]);
            } else {
                buy += Integer.parseInt(temp[1]);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringBuilder2.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
