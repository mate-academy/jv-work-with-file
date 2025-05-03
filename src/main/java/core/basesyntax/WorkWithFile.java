package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] splitData = line.split(",");
                if (splitData[0].equals("supply")) {
                    supplySum += Integer.parseInt(splitData[1]);
                } else {
                    buySum += Integer.parseInt(splitData[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file",e);
        }
        String reportData = createReport(supplySum,buySum);
        writeToFile(reportData,toFile);
    }

    private String createReport(int supplySum, int buySum) {
        int differenceSupplyBuy = supplySum - buySum;
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + differenceSupplyBuy + System.lineSeparator();
    }

    private void writeToFile(String reportData, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile,true))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file",e);
        }
    }
}
