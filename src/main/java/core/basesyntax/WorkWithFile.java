package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFIle = new StringBuilder();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            while ((line = bufferedReader.readLine()) != null) {
                dataFromFIle.append(line).append(System.lineSeparator());
            }
            return dataFromFIle.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot read from file" + fromFileName, e);
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
            if (words[0].equals(BUY)) {
                buyTotal += Integer.parseInt(words[1]);
            } else {
                supplyTotal += Integer.parseInt(words[1]);
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
