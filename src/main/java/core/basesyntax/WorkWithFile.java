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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
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

    public String createReport(int supplySum, int buySum) {
        int differenceSupplyBuy = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buySum).append(System.lineSeparator());
        stringBuilder.append("result,").append(differenceSupplyBuy).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    public void writeToFile(String reportData, File toFile) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFile,true));
            bufferedWriter.write(reportData);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file",e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter",e);
                }
            }
        }
    }
}
