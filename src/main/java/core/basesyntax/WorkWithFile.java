package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report,toFileName);
    }

    private String readFromFile(String fileName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("problems with file!", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String data) {
        int supplyCount = 0;
        int buyCount = 0;
        String [] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String [] input = line.split(",");
            switch (input[0]) {
                case "supply":
                    supplyCount += Integer.parseInt(input[1]);
                    break;
                case "buy":
                    buyCount += Integer.parseInt(input[1]);
                    break;
                default:
                    break;
            }
        }
        return "supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + (supplyCount - buyCount);
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Problems with file!");
        }
    }
}
