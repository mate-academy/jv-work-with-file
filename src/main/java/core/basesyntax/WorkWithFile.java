package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DEFAULT_DELIMETER = ",";
    private static final String KEY_SUPPLY = "supply";
    private static final String KEY_BUY = "buy";
    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        clear();
        readFromFile(fromFileName);
        calculateResult();
        writeToFile(toFileName);
    }

    private void clear() {
        this.buy = 0;
        this.supply = 0;
        this.result = 0;
    }

    private void writeToFile(String toFileName) {
        File file = new File(toFileName);
        try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)) ) {
            bufferedWriter.write(KEY_SUPPLY);
            bufferedWriter.write(DEFAULT_DELIMETER);
            bufferedWriter.write(Integer.valueOf(supply).toString());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(KEY_BUY);
            bufferedWriter.write(DEFAULT_DELIMETER);
            bufferedWriter.write(Integer.valueOf(buy).toString());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("result");
            bufferedWriter.write(DEFAULT_DELIMETER);
            bufferedWriter.write(Integer.valueOf(result).toString());
            bufferedWriter.write(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't close file", e);
        }
    }

    private void calculateResult() {
        this.result = this.supply - this.buy;
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
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
