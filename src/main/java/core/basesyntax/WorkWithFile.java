package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] countResult = readCount(fromFileName);
        String data = createReport(countResult);
        writeInFile(data, toFileName);
    }

    public int[] readCount(String fromFileName) {
        int buyResult = 0;
        int supplyResult = 0;
        String report;
        File file = new File(fromFileName);
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fromFileName));) {
            String value = bufferReader.readLine();
            while (value != null) {
                String[] array = value.split(",");
                if (array[0].equals("buy")) {
                    int temp = Integer.parseInt(array[1]);
                    buyResult += temp;
                } else if (array[0].equals("supply")) {
                    int temp = Integer.parseInt(array[1]);
                    supplyResult += temp;
                }
                value = bufferReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("File can not be read");
        }
        int[] countResult = new int[2];
        countResult[0] = supplyResult;
        countResult[1] = buyResult;
        return countResult;
    }

    public String createReport(int[] countResult) {
        String supply = String.valueOf(countResult[0]);
        String buy = String.valueOf(countResult[1]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(String.valueOf(countResult[0] - countResult[1])).toString();
        String report = stringBuilder.toString();
        return report;
    }

    public void writeInFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("File can not be written");
        }
    }
}
