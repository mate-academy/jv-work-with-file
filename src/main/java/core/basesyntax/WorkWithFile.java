package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = buildReport(dataFromFile);
        writeToFile(report, toFileName);

    }

    private String readFromFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                fileContent.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        return fileContent.toString();

    }

    private String buildReport(String dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;
        String data = dataFromFile.replaceAll("\n|\r\n"," ");
        String[]dataInfo = data.split(" ");
        for (int i = 0; i < dataInfo.length; i++) {
            String[] reportInfo = dataInfo[i].split(CSV_SEPARATOR);
            if (reportInfo[0].equals("supply")) {
                sumSupply += Integer.parseInt(reportInfo[1]);
            } else {
                sumBuy += Integer.parseInt(reportInfo[1]);
            }
        }
        int result = sumSupply - sumBuy;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(sumSupply)
                .append(System.lineSeparator()).append("buy,")
                .append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

}


