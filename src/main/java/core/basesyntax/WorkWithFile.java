package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] keys = new String[] {"supply", "buy"};
        int[] values = new int[keys.length];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] keyValuePair = line.split("\\,");
                int value = Integer.parseInt(keyValuePair[1]);
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i].equals(keyValuePair[0])) {
                        values[i] += value;
                        break;
                    }
                }
                line = reader.readLine();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < keys.length; i++) {
                stringBuilder.append(keys[i])
                        .append(",")
                        .append(values[i])
                        .append(System.lineSeparator());
            }
            stringBuilder.append("result,").append(values[0] - values[1]);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(stringBuilder.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
