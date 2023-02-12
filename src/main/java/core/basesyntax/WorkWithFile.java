package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private Integer supplySum;
    private Integer buySum;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        getSum(fromFile);
        writeData(toFile);
    }

    private void getSum(File file) {
        buySum = 0;
        supplySum = 0;
        try (FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader)) {
            while (reader.ready()) {
                String[] array = reader.readLine().split(",");
                if (array[0].equals("buy")) {
                    buySum += Integer.parseInt(array[1]);
                }
                if (array[0].equals("supply")) {
                    supplySum += Integer.parseInt(array[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    private void writeData(File file) {
        try (FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("supply," + supplySum + System.lineSeparator());
            bufferedWriter.write("buy," + buySum + System.lineSeparator());
            bufferedWriter.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("Cant write data", e);
        }
    }
}



