package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String readFile = readFile(fromFileName);
        String[] prepareReport = prepareReport(readFile);
        writeToFile(prepareReport, toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = bufferedReader.readLine();
            }
            String dataFromFile = builder.toString().replace("[^A-Za-z0-9]", " ");
            return dataFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't reader file", e);
        }
    }

    private void writeToFile(String[] prepareReport, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                true))) {
            for (String res : prepareReport) {
                bufferedWriter.write(res + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't reader file", e);
        }
    }

    private String[] prepareReport(String dataFromFile) {
        String[] splittedData = dataFromFile.split(",");
        int supplyTotal = 0;
        int buyTotal = 0;
        int resultTotal;
        for (int i = 0; i < splittedData.length; i++) {
            if (splittedData[i].equals("supply")) {
                supplyTotal += Integer.parseInt(splittedData[i + 1]);
            } else if (splittedData[i].equals("buy")) {
                buyTotal += Integer.parseInt(splittedData[i + 1]);
            }
        }
        resultTotal = supplyTotal - buyTotal;
        String[] results = {"supply," + supplyTotal, "buy," + buyTotal,
                "result," + resultTotal};
        return results;
    }
}
