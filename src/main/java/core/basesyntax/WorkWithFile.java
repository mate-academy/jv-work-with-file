package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(calcData(readData(fromFileName)));
        writeToFile(report, toFileName);
    }

    private ArrayList<String> readData(String fromFileName) {
        File fromFile = new File(fromFileName);
        ArrayList<String> data = new ArrayList<>();
        String nextString;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            nextString = reader.readLine();
            while (nextString != null) {
                data.add(nextString);
                nextString = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private int[] calcData(ArrayList<String> data) {
        int supply = 0;
        int buy = 0;

        for (String line : data) {
            String[] lineValue;
            lineValue = line.split(",");
            if (lineValue[KEY_INDEX].equals(SUPPLY)) {
                supply += Integer.parseInt(lineValue[VALUE_INDEX]);
            } else if (lineValue[KEY_INDEX].equals(BUY)) {
                buy += Integer.parseInt(lineValue[VALUE_INDEX]);
            }
        }

        int[] dataArray = new int[]{supply, buy};
        return dataArray;
    }

    private String createReport(int[] dataArray) {
        StringBuilder report = new StringBuilder("supply,");
        int supply = dataArray[0];
        int buy = dataArray[1];
        report.append(supply);
        report.append(System.lineSeparator());
        report.append("buy,");
        report.append(buy);
        report.append(System.lineSeparator());
        report.append("result,");
        report.append(supply - buy);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
