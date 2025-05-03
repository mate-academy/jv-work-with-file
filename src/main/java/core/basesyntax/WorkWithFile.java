package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TYPE_INDEX = 0;
    private static final int SUM_INDEX = 1;

    private static final String SUPPLY_LABEL = "supply";
    private static final String BUY_LABEL = "buy";
    private static final String RESULT_LABEL = "result";

    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = createReport(fileContent);
        writeReportToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value).append(NEW_LINE);
            }
            return builder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private String createReport(String fileContent) {
        String[] lines = fileContent.split(NEW_LINE);
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : lines) {
            String[] words = line.split(",");
            String operatorType = words[TYPE_INDEX];
            int operatorSum = Integer.parseInt(words[SUM_INDEX]);
            if (operatorType.equals(SUPPLY_LABEL)) {
                totalSupply += operatorSum;
            } else {
                totalBuy += operatorSum;
            }
        }
        String template = SUPPLY_LABEL + ",%d" + NEW_LINE
                + BUY_LABEL + ",%d" + NEW_LINE
                + RESULT_LABEL + ",%d";
        return String.format(template, totalSupply, totalBuy, totalSupply - totalBuy);
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
