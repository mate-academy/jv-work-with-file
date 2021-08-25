package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = getResult(readFromFile(fromFileName));
        writeToFile(infoFromFile, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataFromFile = bufferedReader.readLine();
            while (dataFromFile != null) {
                stringBuilder.append(dataFromFile).append(" ");
                dataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private int[] calculateAll(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String value : data) {
            int amount = Integer.parseInt(value.substring(value.indexOf(',') + 1));
            if (value.substring(0, value.indexOf(',')).equals("supply")) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        int result = supply - buy;
        return new int[]{supply, buy, result};
    }

    private String getResult(String[] fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] a = calculateAll(fromFileName);
        return stringBuilder.append("supply,")
                .append(a[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(a[BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(a[RESULT_INDEX]).toString();
    }
}
