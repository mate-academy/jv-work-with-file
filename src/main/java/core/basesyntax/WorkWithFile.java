package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String TITTLE_BUY = "buy";
    public static final String TITTLE_SUPPLY = "supply";
    public static final String TITTLE_RESULT = "result";
    public static final int POSITION_AMOUNT = 1;
    public static final int POSITION_OPERATION_TYPE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayReadFromFile = readFromFile(fromFileName);
        String resultWriteToFile = calculateResult(arrayReadFromFile);
        writeToFile(resultWriteToFile, toFileName);
    }

    private String[] readFromFile(String fromFile) {
        StringBuilder readFromFileBuilder;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            readFromFileBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                readFromFileBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFile, e);
        }
        String[] arrayFromReadFile = readFromFileBuilder.toString().split(System.lineSeparator());
        return arrayFromReadFile;
    }

    private String calculateResult(String[] arrayFromFile) {
        int totalBuy = 0;
        int totalSupply = 0;
        for (String line : arrayFromFile) {
            String[] arrayOfLine = line.split(",");
            int amount = Integer.parseInt(arrayOfLine[POSITION_AMOUNT]);
            if (arrayOfLine[POSITION_OPERATION_TYPE].equals(TITTLE_BUY)) {
                totalBuy += amount;
            }
            if (arrayOfLine[POSITION_OPERATION_TYPE].equals(TITTLE_SUPPLY)) {
                totalSupply += amount;
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder writeToFileBuilder = new StringBuilder();
        writeToFileBuilder.append(TITTLE_SUPPLY).append(",").append(totalSupply)
                .append(System.lineSeparator()).append(TITTLE_BUY).append(",").append(totalBuy)
                .append(System.lineSeparator()).append(TITTLE_RESULT).append(",").append(result);
        return writeToFileBuilder.toString();
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}

