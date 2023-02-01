package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readByLine = bufferedReader.readLine();

            while (readByLine != null) {
                text.append(readByLine).append(",");
                readByLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] split = text.toString().split(",");
        String[] actions = { "supply", "buy" };
        int[] amount = new int[actions.length];

        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < actions.length; j++) {
                if (actions[j].equals(split[i])) {
                    amount[j] += Integer.parseInt(split[i + 1]);
                }
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(actions[0] + "," + amount[0] + System.lineSeparator()
                    + actions[1] + "," + amount[1] + System.lineSeparator()
                    + "result" + "," + (amount[0] - amount[1]));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }
}
