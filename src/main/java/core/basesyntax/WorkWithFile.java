package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITING_SYMBOL = ",";
    private static final int TYPE_INDEX = 0;
    private static final int VOLUE_INDEX = 1;
    private static final String OPARATION_TYPE_SUPPLY = "supply";
    private static final String OPARATION_TYPE_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                data.append(nextLine).append(System.lineSeparator());
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return data.toString();
    }

    private String createReport(String inputData) {
        String[] lines = inputData.split(System.lineSeparator());
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String line : lines) {
            String[] lineData = line.split(DELIMITING_SYMBOL);
            if (lineData[TYPE_INDEX].equals(OPARATION_TYPE_SUPPLY)) {
                supplyTotal += Integer.parseInt(lineData[VOLUE_INDEX]);
            } else {
                buyTotal += Integer.parseInt(lineData[VOLUE_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(OPARATION_TYPE_SUPPLY).append(DELIMITING_SYMBOL).append(supplyTotal);
        report.append(System.lineSeparator());
        report.append(OPARATION_TYPE_BUY).append(DELIMITING_SYMBOL).append(buyTotal);
        report.append(System.lineSeparator());
        report.append("result").append(DELIMITING_SYMBOL).append(supplyTotal - buyTotal);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
