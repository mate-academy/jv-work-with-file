package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                value = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String str = stringBuilder.toString();
        System.out.println(str);
        String[] lines = str.split("\n");
        System.out.println(Arrays.toString(lines));
        System.out.println(lines.length);
        String[] numbers = str.trim().split("\\D+");
        Integer[] num = new Integer[numbers.length - 1];
        System.out.println(num.length);
        num[0] = Integer.valueOf(numbers[1]);
        for (int i = 1; i < num.length; i++) {
            if (Integer.valueOf(numbers[i + 1]) >= 0) {
                num[i] = Integer.valueOf(numbers[i + 1]);
            } else {
                continue;
            }
        }
        String[] words = str.trim().split("\\d+");
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            char[] symbols = words[i].toCharArray();
            for (int j = 0; j < symbols.length; j++) {
                if (symbols[j] == ',') {
                    continue;
                }
                stringBuilder1.append(symbols[j]);
            }
            stringBuilder1.append(" ");
        }
        String str1 = stringBuilder1.toString();
        String[] worlds = str1.trim().split("\\W+");
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < worlds.length; i++) {
            if (worlds[i].equals("supply")) {
                supply += num[i];
            } else if (worlds[i].equals("buy")) {
                buy += num[i];
            }

        }
        int result = supply - buy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
