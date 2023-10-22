package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int CONST_VALUE = 0;
    private static final int STEP_SIZE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    public String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] matchLines = readLine.split("\\W+");
                for (String word : matchLines) {
                    stringBuilder.append(word).append(" ");
                }
                readLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file" + fromFileName + "doesn't exist" + e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName + " " + e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String report) {
        int supplyCounter = 0;
        int buyCounter = 0;

        String[] parts = report.split("\\W+");

        for (int i = 0; i < parts.length; i += 2) {
            String operation = parts[i];
            int value = Integer.parseInt(parts[i + 1]);

            if (operation.equals("supply")) {
                supplyCounter += value;
            } else if (operation.equals("buy")) {
                buyCounter += value;
            }
        }

        return new StringBuilder()
                .append("supply,").append(supplyCounter).append(System.lineSeparator())
                .append("buy,").append(buyCounter).append(System.lineSeparator())
                .append("result,").append(supplyCounter - buyCounter)
                .toString();
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName + " " + e);
        }
    }
}
