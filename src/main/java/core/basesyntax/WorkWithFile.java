package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        final int elementsQuantity = 2;
        String[] keys = new String[elementsQuantity];
        int[] values = new int[elementsQuantity];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] keyValuePair = line.split("\\,");
                int value = Integer.parseInt(keyValuePair[1]);
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i] == null) {
                        keys[i] = keyValuePair[0];
                        values[i] = value;
                        break;
                    } else if (keys[i].equals(keyValuePair[0])) {
                        values[i] += value;
                        break;
                    }
                }
                line = reader.readLine();
            }
            if (keys[0].equals("buy")) {
                String tempKey = keys[0];
                keys[0] = keys[1];
                int tempValue = values[0];
                values[0] = values[1];
                keys[1] = tempKey;
                values[1] = tempValue;
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == null) {
                    break;
                }
                stringBuilder.append(keys[i])
                        .append(",")
                        .append(values[i])
                        .append(System.lineSeparator());
            }
            stringBuilder.append("result,").append(values[0] - values[1]);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
