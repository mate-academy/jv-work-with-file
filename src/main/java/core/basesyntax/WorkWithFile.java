package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        System.out.println(stringBuilder);

        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        int supply = 0;
        int buy = 0;

        for (String words : lines) {
            String[] wordsFromLine = words.split(",");
            if (wordsFromLine[0].equals("supply")) {
                supply = supply + Integer.parseInt(wordsFromLine[1]);
            } else if (wordsFromLine[0].equals("buy")) {
                buy = buy + Integer.parseInt(wordsFromLine[1]);
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
