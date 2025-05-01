package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_NUMBER = 0;
    private static final int SECOND_OPERATION_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                dataFromFile.append(line).append(System.lineSeparator());
            }
            return dataFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file" + fromFileName, e);
        }
    }

    private String generateReport(String dataFromFile) {
        StringBuilder report = new StringBuilder();
        String[] data = dataFromFile.split(System.lineSeparator());
        int buyTotal = 0;
        int supplyTotal = 0;
        for (String info : data) {
            String[] words = info.split(",");
            if (words[OPERATION_NUMBER].equals(BUY)) {
                buyTotal += Integer.parseInt(words[SECOND_OPERATION_NUMBER]);
            } else {
                supplyTotal += Integer.parseInt(words[SECOND_OPERATION_NUMBER]);
            }
        }
        report.append(SUPPLY).append(",")
                .append(supplyTotal).append(System.lineSeparator());
        report.append(BUY).append(",")
                .append(buyTotal).append(System.lineSeparator());
        report.append(RESULT).append(",")
                .append(supplyTotal - buyTotal).append(System.lineSeparator());
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        if (!toFile.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
                bufferedWriter.write(report);
            } catch (IOException e) {
                throw new RuntimeException("Cannot write to file" + toFileName, e);
            }
        }
    }
}
