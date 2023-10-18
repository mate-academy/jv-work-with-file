package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(',');
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file " + fromFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int buy = 0;
        int supply = 0;
        String[] split = dataFromFile.split(",");
        for (int i = 0; i < split.length - 1; i++) {
            if (split[i].equals("supply")) {
                supply += Integer.parseInt(split[i + 1]);
            } else if (split[i].equals("buy")) {
                buy += Integer.parseInt(split[i + 1]);
            }
        }
        return "supply," + supply + System.lineSeparator() + "buy,"
                + buy + System.lineSeparator() + "result," + (supply - buy);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(toFileName)))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName, e);
        }
    }
}
