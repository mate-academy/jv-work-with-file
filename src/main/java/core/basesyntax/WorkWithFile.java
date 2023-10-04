package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readCsv(fromFileName);
        String[] sortedData = calculateData(data);
        writeCsv(toFileName, sortedData);
    }

    private String[] readCsv(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeCsv(String fileName, String[] strings) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String str : strings) {
                bufferedWriter.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String [] calculateData(String[] data) {
        Arrays.sort(data);
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String str : data) {
            String inputData = str.split(",")[0];
            int inputAmount = Integer.parseInt(str.split(",")[1]);
            if (inputData.equals("supply")) {
                supplyAmount += inputAmount;
            }
            if (inputData.equals("buy")) {
                buyAmount += inputAmount;
            }
        }
        return new String []{"supply," + supplyAmount,
                "buy," + buyAmount,
                "result," + (supplyAmount - buyAmount)};
    }
}
