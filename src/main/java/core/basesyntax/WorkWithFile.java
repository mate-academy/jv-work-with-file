package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {

        String[] arrayString = readeFromFile(fromFileName).toString().split(",");
        for (int i = 1; i < arrayString.length; i++) {
            if (arrayString[i - 1].equals("supply")) {
                supplySum = Integer.parseInt(arrayString[i].trim()) + supplySum;
            } else if (arrayString[i - 1].equals("buy")) {
                buySum = Integer.parseInt(arrayString[i].trim()) + buySum;
            }
        }
        writerToFile(toFileName);
    }

    private StringBuilder readeFromFile(String fileName) {
        File fromFile = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                stringBuilder.append(",");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't not reade data from file " + fileName, e);
        }
        return stringBuilder;
    }

    private void writerToFile(String fileName) {
        int result = supplySum - buySum;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write("supply,");
            bufferedWriter.write(String.valueOf(supplySum));
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("buy,");
            bufferedWriter.write(String.valueOf(buySum));
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write("result,");
            bufferedWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("Can't not write data to file" + fileName, e);
        }

    }
}
