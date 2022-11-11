package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String COMMA_DELIMITER = ",";
    private static final String SPACE_DELIMITER = " ";

    public void getStatistic(String fromFileName,
                             String toFileName) {
        String lines = getString(fromFileName);

        String report = getReport(lines);

        writeReport(toFileName, report);
    }

    private static String getString(String fromFileName) {
        String lines;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuilder.append(lines).append(" ");
                lines = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        lines = stringBuilder.toString();
        return lines;
    }

    private static String getReport(String lines) {
        StringBuilder reportBuilder = new StringBuilder();
        String[] values = lines.split(SPACE_DELIMITER);
        int sumSupply = 0;
        int sumBuy = 0;
        for (String value : values) {
            String[] line = value.split(COMMA_DELIMITER);
            if (line[0].equals("supply")) {
                sumSupply += Integer.parseInt(line[1]);
            } else {
                sumBuy += Integer.parseInt(line[1]);
            }
        }
        int result = sumSupply - sumBuy;
        String report = createReport(reportBuilder, sumSupply, sumBuy, result);
        return report;
    }

    private static String createReport(StringBuilder reportBuilder,
                                       int sumSupply, int sumBuy, int result) {
        reportBuilder.append("supply")
                .append(",")
                .append(sumSupply);
        reportBuilder.append(System.lineSeparator())
                .append("buy")
                .append(",")
                .append(sumBuy);
        reportBuilder.append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(result);
        String report = reportBuilder.toString();
        return report;
    }

    private static void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
