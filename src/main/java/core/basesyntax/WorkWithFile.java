package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFileText(fromFileName).split(",");
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readFileText(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can not read file: " + fromFileName, e);
        }
        return stringBuilder.toString().replace(System.lineSeparator(), ",");
    }

    private String createReport(String[] data) {
        int buy = 0;
        int supply = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supply += Integer.parseInt(data[i + 1]);
            }
            if (data[i].equals("buy")) {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        int result = supply - buy;
        return "supply" + "," + supply + System.lineSeparator() + "buy" + "," + buy
                + System.lineSeparator() + "result" + "," + result;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(toFileName);
        } catch (IOException e) {
            throw new RuntimeException("can not write to file: " + toFileName, e);
        }
    }
}



