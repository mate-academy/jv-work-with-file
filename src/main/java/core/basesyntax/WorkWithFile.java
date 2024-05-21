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
    private static final String DATA_RESULT = "result";
    private static final int TYPE = 0;
    private static final int VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(new File(fromFileName)))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder
                        .append(line)
                        .append(System.lineSeparator());
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read line from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String processData(String dataFromFile) {
        Data supply = new Data(DATA_SUPPLY);
        Data buy = new Data(DATA_BUY);
        Data result = new Data(DATA_RESULT);
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] typeValue = line.split(DEFAULT_DELIMETER);
            String type = typeValue[TYPE];
            int value = Integer.parseInt(typeValue[VALUE]);
            if (type.equals(DATA_SUPPLY)) {
                supply.addValue(value);
            } else if (type.equals(DATA_BUY)) {
                buy.addValue(value);
            } else {
                throw new RuntimeException("unknown type of data");
            }
        }
        int diff = supply.getValue() - buy.getValue();
        result.setValue(diff);
        stringBuilder
                .append(supply.toString()).append(System.lineSeparator())
                .append(buy.toString()).append(System.lineSeparator())
                .append(result.toString());
        return stringBuilder.toString();
    }

    private void writeToFile(String toFilename, String report) {
        try (BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter(new File(toFilename)));) {
            bufferedWriter.write(report);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't open file " + toFilename, e);
        }
    }
}
