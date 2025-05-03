package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String[] ACTIONS = { SUPPLY, BUY };
    private final StringBuilder text = new StringBuilder();
    private final int[] amount = new int[ACTIONS.length];

    public void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            if (text.length() == 0) {
                String readByLine = bufferedReader.readLine();
                while (readByLine != null) {
                    text.append(readByLine).append(",");
                    readByLine = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    public void calculation() {
        String[] split = text.toString().split(",");
        if (amount[0] != 0 && amount[1] != 0) {
            amount[0] = 0;
            amount[1] = 0;
        }
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < ACTIONS.length; j++) {
                if (ACTIONS[j].equals(split[i])) {
                    amount[j] += Integer.parseInt(split[i + 1]);
                }
            }
        }
    }

    public void writeToTheFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(ACTIONS[0] + COMMA + amount[0] + System.lineSeparator()
                        + ACTIONS[1] + COMMA + amount[1] + System.lineSeparator()
                        + RESULT + COMMA + (amount[0] - amount[1]));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        calculation();
        writeToTheFile(toFileName);
    }
}
