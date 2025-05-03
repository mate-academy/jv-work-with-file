package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFile = readFromFile(fromFileName);
        writeToFile(toFileName, getStatisticData(readFromFile));
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
    }

    private int[] getStatisticData(String string) {
        int[] amount = new int[] {0,0};
        String[] readData = string.split(",");
        for (int i = 0; i < readData.length - 1; i += 2) {
            if (readData[i].equals("supply")) {
                amount[0] += Integer.parseInt(readData[i + 1]);
            } else if (readData[i].equals("buy")) {
                amount[1] += Integer.parseInt(readData[i + 1]);
            }
        }
        return amount;
    }

    private void writeToFile(String toFileName, int[] getStatisticData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String string = (stringBuilder
                    .append("supply,").append(getStatisticData[0]).append(System.lineSeparator())
                    .append("buy,").append(getStatisticData[1]).append(System.lineSeparator())
                    .append("result,").append(getStatisticData[0] - getStatisticData[1]))
                    .toString();
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
