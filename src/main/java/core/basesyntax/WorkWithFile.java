package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File file = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file" + e);
        }
        String[] words = stringBuilder.toString().trim().toLowerCase(Locale.ROOT).split("\\W+");
        for (int i = 0;i < words.length;i++) {
            switch (words[i]) {
                case "supply":
                    supply += Integer.parseInt(words[i + 1]);
                    break;
                case "buy":
                    buy += Integer.parseInt(words[i + 1]);
                    break;
                default:
            }
        }
        result = supply - buy;
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Wrong file " + e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Wrong file" + e);
        }
    }
}
