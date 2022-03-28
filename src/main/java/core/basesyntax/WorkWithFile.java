package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile((createReport(readFromFile(fromFileName))), toFileName);
    }

    private String readFromFile(String fromFileName) {
        String readData;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String data = bufferedReader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(System.lineSeparator());
                data = bufferedReader.readLine();
            }
            readData = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
        return readData;
    }

    private String createReport(String readData) {
        int supply = 0;
        int buy = 0;
        String[] info = readData.split(System.lineSeparator());
        for (int i = 0; i < info.length; i++) {
            String[] result = info[i].split(",");
            int sum = Integer.parseInt(result[1]);
            if (result[0].startsWith("s")) {
                supply += sum;
            } else {
                buy += sum;
            }
        }
        StringBuilder stringReport = new StringBuilder();
        stringReport.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        String report = stringReport.toString();
        return report;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
