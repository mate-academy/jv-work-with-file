package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String dataFromFile) {
        String[] countedValues = dataFromFile.split(" ");

        return "supply," + countedValues[0]
                + System.lineSeparator()
                + "buy," + countedValues[1]
                + System.lineSeparator()
                + "result," + countedValues[2];
    }

    private String readFile(String fromFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                String[] data = dataLine.split("[, ]");
                if (dataLine.contains("buy")) {
                    buy += Integer.parseInt(data[1]);
                } else {
                    supply += Integer.parseInt(data[1]);
                }

                dataLine = bufferedReader.readLine();
            }
            result = supply - buy;

            return supply + " " + buy + " " + result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
