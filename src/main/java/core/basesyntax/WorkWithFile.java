package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot delete file " + toFileName, e);
        }

        String[] actionsList = readFile(fromFileName);
        String report = createReport(actionsList);
        writeReportToFile(report, toFileName);
    }
    
    private String[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            return stringBuilder.toString().split("[\\n\\r\\t]+");

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeReportToFile(String reportToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(toFileName, true))) {
            bufferedWriter.write(reportToWrite);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
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
        action = action.replaceAll("[^0-9]", "");
        return Integer.parseInt(action);
    }

}
