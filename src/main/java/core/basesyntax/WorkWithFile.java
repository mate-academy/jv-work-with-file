package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int buySum = 0;
    private int supplySum = 0;
    private final String supplyOperation = "supply";
    private final String buyOperation = "buy";
    private final String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] tempString = readFile(fromFileName).toString().split("\\W+");
        calculateSum(tempString);
        checkWriteFile(toFileName);
        writeFile(toFileName);
    }

    public StringBuilder readFile(String fromFile) {
        File file = new File(fromFile);
        StringBuilder textBox = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            File checkFile = new File(fromFile);
            if (checkFile.length() == 0) {
                System.out.println("File: " + fromFile + "is empty.");
            }
            int symbol = 0;
            while ((symbol = reader.read()) != -1) {
                textBox.append((char) symbol);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFile, e);
        }
        return textBox;
    }

    public void calculateSum(String[] dataArray) {
        supplySum = 0;
        buySum = 0;
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].toLowerCase();
            if (dataArray[i].equals(supplyOperation)) {
                supplySum += Integer.parseInt(dataArray[i + 1]);
            } else if (dataArray[i].equals(buyOperation)) {
                buySum += Integer.parseInt(dataArray[i + 1]);
            }
        }
    }

    public void checkWriteFile(String toFile) {
        File checkFile = new File(toFile);
        if (checkFile.length() != 0) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(toFile, false))) {
                bufferedWriter.write("");
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file " + toFile, e);
            }
        }
    }

    public void writeFile(String toFile) {
        File file = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            bufferedWriter.write(supplyOperation + "," + supplySum + System.lineSeparator()
                    + buyOperation + "," + buySum + System.lineSeparator()
                    + result + "," + (supplySum - buySum) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFile, e);
        }
    }
}
