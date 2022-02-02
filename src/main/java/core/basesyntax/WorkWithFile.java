package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int buyResult = 0;
    private int supplyResult = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String report = workWithFile.readCount(fromFileName);
        workWithFile.writeInFile(report, toFileName);
    }

    public String readCount(String fromFileName) {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                .append(supplyResult)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyResult)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyResult - buyResult).toString();
        report = stringBuilder.toString();
        return report;
    }

    public void writeInFile(String s, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            bufferedWriter.write(s);
        } catch (IOException e) {
            throw new RuntimeException("File can not be written");
        }
    }
}





