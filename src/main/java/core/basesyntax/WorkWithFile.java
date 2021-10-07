package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] content = readFromFile(fromFileName);
        String report = createReport(content);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder innerContent = new StringBuilder();
        String fileLineContent;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLineContent = bufferedReader.readLine()) != null) {
                innerContent.append(fileLineContent).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return innerContent.toString().split(System.lineSeparator());
    }

    private String createReport(String[] lines) {
        int buyAmount = 0;
        int supplyAmount = 0;
        StringBuilder report = new StringBuilder();
        for (String dataArrayElement : lines) {
            String[] record = dataArrayElement.split(SEPARATOR);
            if (record[0].equals("buy")) {
                buyAmount += Integer.parseInt(record[1]);
            } else if (record[0].equals("supply")) {
                supplyAmount += Integer.parseInt(record[1]);
            }
        }
        report.append("supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + (supplyAmount - buyAmount));
        return report.toString();
    }

    private void writeToFile(String toFileName, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
