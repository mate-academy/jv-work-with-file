package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPRATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = getReport(data);
        writeToFile(report, toFileName);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return builder.toString();
    }

    public String getReport(String info) {
        int supply = 0;
        int buy = 0;
        String [] lines = info.split(System.lineSeparator());
        for (String words : lines) {
            String[] resultSplited = words.split(",");
            if (resultSplited[OPRATION_INDEX].equals(SUPPLY)) {
                supply = supply + Integer.parseInt(resultSplited[1]);
            } else if (resultSplited[OPRATION_INDEX].equals(BUY)) {
                buy = buy + Integer.parseInt(resultSplited[1]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String statistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in the file", e);
        }
    }
}
