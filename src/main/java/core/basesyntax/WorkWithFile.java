package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int PART_LINE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] actionsList = readFile(fromFileName);
        String report = createReport(actionsList);
        writeReportToFile(report, toFileName);
    }
    
    private String[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileDataCollector = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                fileDataCollector.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            return fileDataCollector.toString().split(System.lineSeparator());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find " + fromFileName + " file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fromFileName + " file", e);
        }
    }

    private void writeReportToFile(String reportToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName))) {
            bufferedWriter.write(reportToWrite);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find " + toFileName + " file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to " + toFileName + " file", e);
        }
    }

    private String createReport(String[] list) {
        int counterBuy = 0;
        int counterSupply = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : list) {
            if (line.startsWith(SUPPLY)) {
                counterSupply += calculateTurnover(line);
            } else {
                counterBuy += calculateTurnover(line);
            }
        }

        int result = counterSupply - counterBuy;
        return stringBuilder.append(SUPPLY).append(",").append(counterSupply)
                .append(System.lineSeparator()).append(BUY).append(",").append(counterBuy)
                .append(System.lineSeparator()).append(RESULT).append(",")
                .append(result).toString();
    }

    private int calculateTurnover(String action) {
        String[] lineElements = action.split(",");
        return Integer.parseInt(lineElements[PART_LINE]);
    }
}
