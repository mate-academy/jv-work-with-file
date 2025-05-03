package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName);
        String[] report = makeReport(lines);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open file " + fileName, e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeToFile(String nameFile, String[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nameFile))) {
            for (String lines : report) {
                bufferedWriter.write(lines);
                bufferedWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant open file " + nameFile, e);
        }
    }

    private String[] makeReport(String[] lines) {
        int buySum = 0;
        int supplySum = 0;
        int tempSum = 0;
        for (String line : lines) {
            tempSum = Integer.parseInt(line.substring(line.indexOf(",") + 1));
            if (line.substring(0, line.indexOf(",")).equals("buy")) {
                buySum += tempSum;
            } else {
                supplySum += tempSum;
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supplySum).append(" ")
                .append("buy,").append(buySum).append(" ")
                .append("result,").append(supplySum - buySum);
        return reportBuilder.toString().split(" ");
    }
}
