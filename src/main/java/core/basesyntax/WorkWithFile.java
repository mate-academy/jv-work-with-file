package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int ACTION = 0;
    private static final int NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        String[] arr = text.toString().split(LINE_SEPARATOR);
        int buy = 0;
        int supply = 0;
        int res = 0;
        for (String s : arr) {
            String[] parts = s.split(",");
            if (parts[ACTION].equals("buy")) {
                buy += Integer.parseInt(parts[NUMBER]);
            } else {
                supply += Integer.parseInt(parts[NUMBER]);
            }
            res = supply - buy;
        }
        StringBuilder results = new StringBuilder();
        results.append("supply,").append(supply)
                .append(LINE_SEPARATOR).append("buy,").append(buy)
                .append(LINE_SEPARATOR).append("result,").append(res);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(results.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
