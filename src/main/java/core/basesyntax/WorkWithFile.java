package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readCsv(fromFileName);
        String[] resultData = processData(data);
        writeCsv(toFileName, resultData);
    }

    private String[] readCsv(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        String separatingRegex = " ";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(separatingRegex);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString().split(separatingRegex);
    }

    private void writeCsv(String fileName, String[] strings) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String str : strings) {
                bufferedWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + fileName, e);
        }
    }

    private String [] processData(String[] data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String str : data) {
            String [] splittedString = str.split(",");
            String inputData = splittedString[0];
            int inputAmount = Integer.parseInt(splittedString[1]);
            if (inputData.equals("supply")) {
                supplyAmount += inputAmount;
            }
            if (inputData.equals("buy")) {
                buyAmount += inputAmount;
            }
        }
        String supply = "supply," + supplyAmount;
        String buy = "buy," + buyAmount;
        String result = "result," + (supplyAmount - buyAmount);
        return new String []{supply, buy, result};
    }
}
