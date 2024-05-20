package core.basesyntax;

import java.io.*;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String DEFAULT_DELIMETER = ",";
    private static final String KEY_SUPPLY = "supply";
    private static final String KEY_BUY = "buy";
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        calculateResult();
        writeToFile(toFileName);
    }

    private void writeToFile(String toFileName) {
    }

    private void calculateResult() {
        this.result = this.supply - this.buy;
    }

    private void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                parseDataFromLine(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't close file", e);
        }
    }

    private void parseDataFromLine(String line) {
        String[] datas = line.split(DEFAULT_DELIMETER);
        String key = datas[0];
        int value = Integer.parseInt(datas[1]);
        if (key.equals(KEY_SUPPLY)) {
            supply += value;
            return;
        }
        if (key.equals(KEY_BUY)) {
            buy += value;
            return;
        }
        throw new RuntimeException("Can't read data");
    }
}
