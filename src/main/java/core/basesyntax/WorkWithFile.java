package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readerFile(fromFileName).split(" ");
        String[] linesForReport = creatorReport(data);
        writerFile(linesForReport, toFileName);
    }

    private String readerFile(String fileWithData) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileWithData))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(' ');
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileWithData, e);
        }
        return stringBuilder.toString();
    }

    private String[] creatorReport(String[] dataForReport) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : dataForReport) {
            String[] element = line.split(",");
            if (element[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(element[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(element[AMOUNT_INDEX]);
            }
        }
        return new String[] {("supply," + sumSupply), ("buy," + sumBuy),
                ("result," + (sumSupply - sumBuy))};
    }

    private void writerFile(String[] linesForReport, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report, true))) {
            for (String line : linesForReport) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + report, e);
        }
    }
}
