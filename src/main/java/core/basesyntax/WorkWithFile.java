package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readedLine = bufferedReader.readLine();
            String lineSeparator = "";
            while (readedLine != null) {
                stringBuilder.append(lineSeparator).append(readedLine);
                readedLine = bufferedReader.readLine();
                lineSeparator = System.lineSeparator();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] readingContent = stringBuilder.toString().split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String row : readingContent) {
            String[] rowInAray = row.split(",");
            String operationType = rowInAray[0].trim();
            int amount = Integer.parseInt(rowInAray[1].trim());
            if (operationType.equals("supply")) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator());
            bufferedWriter.write("buy," + buyAmount + System.lineSeparator());
            bufferedWriter.write("result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }
}
