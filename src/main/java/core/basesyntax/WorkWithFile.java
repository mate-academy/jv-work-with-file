package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String infoFromFile = readFile(fromFileName);
        String report = calculator(infoFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read form file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String calculator(String data) {
        String[] statistics = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String statistic : statistics) {
            String[] info = statistic.split(",");
            String operationType = info[0];
            int index = Integer.parseInt(info[1]);
            if (operationType.equals(SUPPLY)) {
                supply += index;
            } else {
                buy += index;
            }
        }
        return report(supply, buy);
    }

    private String report(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supply)
                .append(System.lineSeparator()).append(BUY).append(",")
                .append(buy).append(System.lineSeparator()).append(RESULT)
                .append(",").append(supply - buy).append(System.lineSeparator());

        return builder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write form file " + toFileName, e);
        }
    }
}
