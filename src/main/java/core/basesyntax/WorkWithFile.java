package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = read(fromFileName);
        String[] report = createReport(data);
        write(toFileName, report);

    }

    private String read(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.trim().isEmpty()) {
                    continue;
                }
                data.append(s).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    private String[] createReport(String data) {
        String[] dataArray = data.split(" ");
        int supply = 0;
        int buy = 0;
        for (String element : dataArray) {
            String[] keyValuePairs = element.split(",");
            if (element.trim().isEmpty()) {
                continue;
            }
            if (keyValuePairs[0].equals("supply")) {
                supply += Integer.parseInt(keyValuePairs[1]);
            } else {
                buy += Integer.parseInt(keyValuePairs[1]);
            }
        }
        int result = supply - buy;
        return new String[]{"supply," + supply,
                "buy," + buy,
                "result," + result
        };
    }

    private void write(String toFileName, String[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String line : report) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
