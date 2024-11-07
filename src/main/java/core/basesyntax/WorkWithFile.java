package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
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
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.valueOf(numbers[i+1]);
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
        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            file1.createNewFile();
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
