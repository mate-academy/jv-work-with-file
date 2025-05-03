package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_FIELD = "buy";
    private static final String SUPPLY_FIELD = "supply";
    private static final String RESULT_FIELD = "result";
    private static final String COMA_SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String reportFromData = generateReport(dataFromFile);
        writeToFile(reportFromData,toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        return content.toString();
    }

    private String generateReport(String data) {
        String[] lines = data.split(System.lineSeparator());
        StringBuilder report = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : lines) {
            String[] values = line.split(COMA_SPLITTER);
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals(SUPPLY_FIELD)) {
                    i++;
                    totalSupply += Integer.parseInt(values[i]);
                } else if (values[i].equals(BUY_FIELD)) {
                    i++;
                    totalBuy += Integer.parseInt(values[i]);
                }
            }
        }
        int result = totalSupply - totalBuy;
        report.append(SUPPLY_FIELD).append(COMA_SPLITTER).append(totalSupply)
                .append(System.lineSeparator());
        report.append(BUY_FIELD).append(COMA_SPLITTER).append(totalBuy)
                .append(System.lineSeparator());
        report.append(RESULT_FIELD).append(COMA_SPLITTER).append(result);
        return report.toString();
    }

    private void writeToFile(String report, String toFile) {
        try (FileWriter writer = new FileWriter(toFile)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write the report to file " + toFile, e);
        }
    }
}
