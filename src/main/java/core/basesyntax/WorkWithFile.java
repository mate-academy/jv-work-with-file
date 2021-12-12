package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int TOTAL_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }

    private String[] redData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private int[] getEstimation(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String[] records = redData(fromFileName);
        for (String record: records) {
            String[] info = record.split(",");
            if (info[OPERATION_TYPE_INDEX].equals("supply")) {
                supply += Integer.parseInt(info[TOTAL_INDEX]);
            }
            if (info[OPERATION_TYPE_INDEX].equals("buy")) {
                buy += Integer.parseInt(info[TOTAL_INDEX]);
            }
        }
        return new int[] {supply, buy, supply - buy};
    }

    private String getReport(String fromFileName) {
        int[] estimation = getEstimation(fromFileName);
        return "supply," + estimation[0] + System.lineSeparator()
                + "buy," + estimation[1] + System.lineSeparator()
                + "result," + estimation[2];
    }
}
