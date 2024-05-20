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
    private static final String DATA_SUPPLY = "supply";
    private static final String DATA_BUY = "buy";
    private static final String DATA_RES = "result";
    private Data supply = new Data(DATA_SUPPLY);
    private Data buy = new Data(DATA_BUY);
    private Data result = new Data(DATA_RES);


    public void getStatistic(String fromFileName, String toFileName) {
        reset();
        readFromFile(fromFileName);
        calculateRes();
        writeToFile(toFileName);
    }

    private void reset() {
        supply.setValue(0);
        buy.setValue(0);
        result.setValue(0);
    }

    private void calculateRes() {
        int diff = supply.getValue() - buy.getValue();
        result.setValue(diff);
    }

    private void writeToFile(String toFileName) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(toFileName);
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(supply.toString());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(buy.toString());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(result.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close file", e);
            }
        }
    }

    private void readFromFile(String fromFileName) {
        Data supply = new Data(DATA_SUPPLY);
        Data buy = new Data(DATA_BUY);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                updateDataFromLine(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't close file", e);
        }
    }

    private void updateDataFromLine(String line) {
        String[] datas = line.split(DEFAULT_DELIMETER);
        String type = datas[0];
        int value = Integer.parseInt(datas[1]);
        if (type.equals(DATA_SUPPLY)) {
            supply.addValue(value);
            return;
        }
        if (type.equals(DATA_BUY)) {
            buy.addValue(value);
            return;
        }
        throw new RuntimeException("Unknown type");
    }
}
