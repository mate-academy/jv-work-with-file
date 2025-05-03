package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int buySum = 0;
        int supplySum = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] dataLine = currentLine.split(SEPARATOR);
                switch (dataLine[INDEX_OF_OPERATION]) {
                    case BUY:
                        buySum += Integer.parseInt(dataLine[INDEX_OF_AMOUNT]);
                        break;
                    case SUPPLY:
                        supplySum += Integer.parseInt(dataLine[INDEX_OF_AMOUNT]);
                        break;
                    default:
                        return;
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find file " + e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read line " + e);
        }
        int result = supplySum - buySum;
        stringBuilder.append(SUPPLY)
                .append(SEPARATOR)
                .append(supplySum)
                .append(System.lineSeparator())
                .append(BUY)
                .append(SEPARATOR)
                .append(buySum)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SEPARATOR)
                .append(result);
        String str = String.valueOf(stringBuilder);
        createFile(str, toFileName);
    }

    public void createFile(String data, String toFile) {
        File file = new File(toFile);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Can`t write to file" + e);
        }
    }
}
