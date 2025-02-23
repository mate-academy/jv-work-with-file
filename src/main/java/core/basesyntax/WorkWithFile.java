package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File sourceFile = new File(fromFileName);
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String fileLine = reader.readLine();
            while (fileLine != null) {
                String[] line = fileLine.split(",");
                if (line[0].equals("supply")) {
                    supplySum += Integer.parseInt(line[1]);
                } else {
                    buySum += Integer.parseInt(line[1]);
                }
                fileLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String inputData = getResult(supplySum, buySum);
        File targetFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile))) {
            bufferedWriter.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
    public String getResult(int supplySum, int buySum) {
        int result = supplySum - buySum;
        String inputData = "supply," + supplySum + System.lineSeparator() + "buy," + buySum
                + System.lineSeparator() + "result," + result;
    }
}
