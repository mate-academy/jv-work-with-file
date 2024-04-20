package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String Final_Supply = "supply";
    private static final String Final_Buy = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFileText(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFileText(String fromFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("can not read file: " + fromFileName, e);
        }
    }

    private String createReport(String[] datas) {
        int supply = 0;
        int buy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: datas) {
            String[] data = line.split(",");
            if (data[0].equals("supply")) {
                supply += Integer.parseInt(data[1]);
            } else {
                buy += Integer.parseInt(data[1]);
            }
        }
        int result = supply - buy;
        return stringBuilder
                    .append(Final_Supply).append(",").append(supply)
                    .append(System.lineSeparator())
                    .append(Final_Buy).append(",").append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(",").append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(toFileName);
        } catch (IOException e) {
            throw new RuntimeException("can not write to file: " + toFileName, e);
        }
    }
}
