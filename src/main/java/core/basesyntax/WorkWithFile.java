package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public int[] calculateResult(String fromFileName) {
        String[] resultArray = readFile(fromFileName);
        int[] result = new int[2];
        for (int i = 0; i < resultArray.length; i++) {
            if (resultArray[i].equals("supply")) {
                result[0] = result[0] + Integer.parseInt(resultArray[i + 1]);
            } else if (resultArray[i].equals("buy")) {
                result[1] = result[1] + Integer.parseInt(resultArray[i + 1]);
            }
        }
        return result;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = calculateResult(fromFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply,"
                    + String.valueOf(data[0])
                    + System.lineSeparator());
            writer.write("buy,"
                    + String.valueOf(data[1])
                    + System.lineSeparator());
            writer.write("result,"
                    + String.valueOf(data[0] - data[1])
                    + System.lineSeparator() + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("can't write file", e);
        }
    }

    private String[] readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        return builder.toString().split("\\W+");
    }
}
