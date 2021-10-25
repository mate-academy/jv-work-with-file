package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int NUMBERLINE = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileString = readData(fromFileName);
        String data = createReport(fileString);
        writeData(toFileName, data);

    }

    private String readData(String fromFileName) {
        File file = new File(fromFileName);
        try {
            String result = Files.readAllLines(file.toPath()).toString();
            return result.substring(1, result.length() - 1);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }

    private String createReport(String fileString) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] arrayString = fileString.split(" ");
        int[] arrayNumber = calculateDate(arrayString);
        stringBuilder.append("supply,").append(arrayNumber[0]).append(System.lineSeparator())
                        .append("buy,").append(arrayNumber[1]).append(System.lineSeparator())
                        .append("result,").append(arrayNumber[2]);
        return stringBuilder.toString();
    }

    private int[] calculateDate(String[] arrayString) {
        int[] arrayResult = new int[NUMBERLINE];
        int sumSupply = 0;
        int sumBuy = 0;
        int result = 0;
        for (String array : arrayString) {
            String[] arrayStringLine = array.split(",");
            if (arrayStringLine[0].contains("supply")) {
                sumSupply += Integer.parseInt(arrayStringLine[1]);
            } else {
                sumBuy += Integer.parseInt(arrayStringLine[1]);
            }
        }
        result = sumSupply - sumBuy;
        arrayResult[0] = sumSupply;
        arrayResult[1] = sumBuy;
        arrayResult[2] = result;
        return arrayResult;
    }

    private void writeData(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file!", e);
        }
    }
}
