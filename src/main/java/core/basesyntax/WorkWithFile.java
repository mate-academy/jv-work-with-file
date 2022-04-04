package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String[] result;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
            result = stringBuilder.toString().split("\\n");
        } catch (IOException e) {
            throw new RuntimeException("can't read to the file" + fromFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getStatisticHelper(result));
        } catch (IOException e) {
            throw new RuntimeException("can't write date to file" + toFileName, e);
        }

    }

    public String getStatisticHelper(String[] date) {
        int supply = 0;
        int buy = 0;
        for (String s : date) {
            String[] string = s.split(",");
            String[] value = string[1].split("\r");
            if (string[0].equals("supply")) {
                supply += Integer.parseInt(value[0]);
            } else if (string[0].equals("buy")) {
                buy += Integer.parseInt(value[0]);
            }
        }
        String resultString = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
        return resultString;
    }
}
