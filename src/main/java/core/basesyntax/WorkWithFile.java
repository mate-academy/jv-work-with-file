package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final int WORD_POSITION_IN_ARRAY = 0;
    private static final int NUMBER_POSITION_IN_ARRAY = 1;
    private static final int SUPPLY_NUMBER = 0;
    private static final int BUY_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] numberData = makeReport(fromFileName, toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_WORD)
                .append(',')
                .append(numberData[SUPPLY_NUMBER])
                .append(System.lineSeparator())
                .append(BUY_WORD).append(',')
                .append(numberData[BUY_NUMBER])
                .append(System.lineSeparator())
                .append(RESULT_WORD)
                .append(',')
                .append(Integer.valueOf(numberData[SUPPLY_NUMBER])
                        - Integer.valueOf(numberData[BUY_NUMBER]));
        writeToFile(stringBuilder.toString(), toFileName);
    }

    private String readFile(String fromFileName) {
        File readableFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(readableFile))) {
            String value = reader.readLine();
            while (value != null) {
                value = reader.readLine();
                stringBuilder.append(value)
                        .append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return stringBuilder.toString();
    }

    private int[] makeReport(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String[] dataArray = dataFromFile.split(System.lineSeparator());
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String data :
                dataArray) {
            String[] splittedData = data.split(",");
            for (int i = 0; i < splittedData.length; i++) {
                if (splittedData[WORD_POSITION_IN_ARRAY].equals(SUPPLY_WORD)) {
                    supplyCounter = supplyCounter
                            + Integer.valueOf(splittedData[NUMBER_POSITION_IN_ARRAY]);
                } else {
                    buyCounter = buyCounter
                           + Integer.valueOf(splittedData[NUMBER_POSITION_IN_ARRAY]);
                }
            }
        }
        return new int[] {supplyCounter, buyCounter};
    }

    private void writeToFile(String input, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(input);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to this file", e);
        }
    }
}
