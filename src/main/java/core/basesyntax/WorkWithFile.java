package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName,
                             String toFileName) {
        String lines = readFromFile(fromFileName);
        String report = getReport(lines);
        writeReport(toFileName, report);
    }

    private static String readFromFile(String fromFileName) {
        String lines;
        StringBuilder linesBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));) {
            lines = bufferedReader.readLine();
            while (lines != null) {
                linesBuilder.append(lines).append(" ");
                lines = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return linesBuilder.toString();
    }

    private static String getReport(String lines) {
        StringBuilder reportBuilder = new StringBuilder();
        String[] splittedLines = lines.split(" ");
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : splittedLines) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                sumSupply += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                sumBuy += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        int result = sumSupply - sumBuy;
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
        return reportBuilder.toString();
    }

    private static void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
