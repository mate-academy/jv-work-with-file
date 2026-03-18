package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = getFileData(fromFileName)[0];
        int buy = getFileData(fromFileName)[1];
        File report = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            writer.write(getReportData(supply, buy));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }

    private int[] getFileData(String fromFileName) {
        int[] info = new int[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts[0].equals(SUPPLY_OPERATION)) {
                    info[0] += Integer.parseInt(parts[1]);
                } else {
                    info[1] += Integer.parseInt(parts[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return info;
    }

    private String getReportData(int supply, int buy) {
        StringBuilder stb = new StringBuilder();
        int result = supply - buy;
        stb.append(SUPPLY_OPERATION).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY_OPERATION).append(",")
                .append(buy).append(System.lineSeparator()).append(RESULT_OPERATION)
                .append(",").append(result);
        return stb.toString();
    }
}
