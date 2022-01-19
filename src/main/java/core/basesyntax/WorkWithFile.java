package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataInfo = readFile(fromFileName);
        String report = dateProcessing(dataInfo);
        writeReport(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }

    private String dateProcessing(String dataInfo) {
        String[] valueArr = dataInfo.split(",");
        int supply = 0;
        int buy = 0;
        int counter = 1;
        for (int i = 0; i < valueArr.length; i += 2) {
            if (valueArr[i].equals("supply")) {
                supply += Integer.parseInt(valueArr[counter]);
            } else {
                buy += Integer.parseInt(valueArr[counter]);
            }
            counter += 2;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator());
        return builder.toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file" + toFileName, e);
        }
    }
}
