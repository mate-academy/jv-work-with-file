package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(" ");
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with read file", e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String allTextFile) {
        int intSupply = 0;
        int intBuy = 0;
        StringBuilder resultBuilder = new StringBuilder();
        String[] values = allTextFile.split("\\s+");
        for (String value : values) {
            String[] eachValue = value.split(",");
            if (eachValue[0].equals(SUPPLY)) {
                intSupply += Integer.parseInt(eachValue[1]);
            } else if (eachValue[0].equals(BUY)) {
                intBuy += Integer.parseInt(eachValue[1]);
            }
        }
        resultBuilder.append(intSupply).append(',').append(intBuy);
        return resultBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            if (report != null) {
                String[] reportData = report.split(",");
                bufferedWriter.write(SUPPLY + "," + reportData[0]);
                bufferedWriter.newLine();
                bufferedWriter.write(BUY + "," + reportData[1]);
                bufferedWriter.newLine();
                bufferedWriter.write("result," + (Integer.parseInt(reportData[0])
                        - Integer.parseInt(reportData[1])));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with record to file", e);
        }
    }

}


