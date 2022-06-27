package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lineData = reader.readLine();
            while (lineData != null) {
                stringBuilder.append(lineData).append(System.lineSeparator());
                lineData = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data to file", e);
        }
        String stringData = stringBuilder.toString();
        String[] arrayData = stringData.split(System.lineSeparator());
        int supply = 0, buy = 0;
        for (String data: arrayData) {
            if (data.substring(0,3).equals("buy")) {
                buy += Integer.parseInt(data.substring(4));
            } else {
                supply += Integer.parseInt(data.substring(7));
            }
        }
        String[] report = { "supply," + supply,
                "buy," + buy,
                "result," + (supply - buy)};
        for (String reportLine: report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
                bufferedWriter.write(reportLine + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
