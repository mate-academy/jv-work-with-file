package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int CHARACTERISTIC_POSITION_IN_ARRAY = 0;
    public static final int VALUE_POSITION_IN_ARRAY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = 0;
        int supply = 0;
        int buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                if (words[CHARACTERISTIC_POSITION_IN_ARRAY].equals("supply")) {
                    supply += Integer.parseInt(words[VALUE_POSITION_IN_ARRAY]);
                    result += Integer.parseInt(words[VALUE_POSITION_IN_ARRAY]);
                } else {
                    buy += Integer.parseInt(words[VALUE_POSITION_IN_ARRAY]);
                    result -= Integer.parseInt(words[VALUE_POSITION_IN_ARRAY]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
