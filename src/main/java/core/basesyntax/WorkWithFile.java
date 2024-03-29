package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATOR_TYPE_INDEX = 0;
    private static final int OPERATOR_SUM_INDEX = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String REPORT_TEMPLATE = "%s,%d%n%s,%d%n%s,%d";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = createReport(fileContent);
        writeReportToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл", e);
        }
    }

    private String createReport(String fileContent) {
        String[] lines = fileContent.split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : lines) {
            String[] words = line.split(",");
            String operatorType = words[OPERATOR_TYPE_INDEX];
            int operatorSum = Integer.parseInt(words[OPERATOR_SUM_INDEX]);
            if (operatorType.equals(OPERATION_SUPPLY)) {
                totalSupply += operatorSum;
            } else {
                totalBuy += operatorSum;
            }
        }
        return String.format(REPORT_TEMPLATE, OPERATION_SUPPLY, totalSupply, "buy", totalBuy,
                "result", totalSupply - totalBuy);
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать файл", e);
        }
    }
}
