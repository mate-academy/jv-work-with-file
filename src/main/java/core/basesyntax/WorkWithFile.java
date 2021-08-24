package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        write(toFileName, report(buyAndSupply(read(fromFileName))).toString());
    }

    private String[] read(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private int[] buyAndSupply(String[] data) {
        int buy = 0;
        int supply = 0;
        for (String array : data) {
            String[] arrayData = array.split(",");
            if (arrayData[OPERATION_TYPE].equals("buy")) {
                buy += Integer.parseInt(arrayData[AMOUNT]);
            } else {
                supply += Integer.parseInt(arrayData[AMOUNT]);
            }
        }
        return new int[] {buy, supply};
    }

    private String[] report(int[] data) {
        String[] report = new String[3];
        report[0] = "supply," + data[1] + System.lineSeparator();
        report[1] = "buy," + data[0] + System.lineSeparator();
        report[2] = "result," + (data[1] - data[0]);
        return report;

    }

    private void write(String fileName, String report) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
