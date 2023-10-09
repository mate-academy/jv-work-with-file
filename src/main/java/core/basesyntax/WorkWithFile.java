package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] dataToWrite = readFromFile(fromFileName);
        writeToFile(toFileName, dataToWrite);
    }

    private int[] readFromFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String readerString = reader.readLine();
            int supplyCount = 0;
            int buyCount = 0;
            while (readerString != null) {
                final String[] splittedLine = readerString.split(",");
                final String operationType = splittedLine[0];
                final int operatedAmount = Integer.parseInt(splittedLine[1]);
                if (operationType.equals("supply")) {
                    supplyCount += operatedAmount;
                } else {
                    buyCount += operatedAmount;
                }
                readerString = reader.readLine();
            }
            return new int[]{supplyCount, buyCount};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
    }

    private void writeToFile(String destinationFileName, int[] dataArray) {
        File destinationfile = new File(destinationFileName);
        int supplyCount = dataArray[0];
        int buyCount = dataArray[1];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplyCount).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buyCount).append(System.lineSeparator());
        stringBuilder.append("result,").append(supplyCount - buyCount);
        String resultData = stringBuilder.toString();
        try {
            Files.write(destinationfile.toPath(), resultData.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + destinationFileName, e);
        }
    }
}
