package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String EMPTY_STRING = " ";
    private static final String DELIMITER_COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int TYPE_COST = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        String[] dataFromFile = readInfoFromFile(fromFileName);
        int totalBought = costCalculation(dataFromFile, BUY);
        int totalSupplied = costCalculation(dataFromFile, SUPPLY);
        String report = getReport(totalBought, totalSupplied);
        writeReportToFile(report, toFileName);

    }

    private void writeReportToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file" + toFileName, e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(report);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String getReport(int totalBought, int totalSupplied) {
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(DELIMITER_COMMA).append(totalSupplied)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMITER_COMMA).append(totalBought)
                .append(System.lineSeparator())
                .append("result,").append(totalSupplied - totalBought);
        return sb.toString();
    }

    private int costCalculation(String[] dataFromFile, String type) {
        int total = 0;
        for (String s : dataFromFile) {
            String[] info = s.split(DELIMITER_COMMA);
            if (info[TYPE_COST].equals(type)) {
                total += Integer.parseInt(info[AMOUNT]);
            }
        }
        return total;
    }

    private String[] readInfoFromFile(String fromFileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append(EMPTY_STRING);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return sb.toString().split(EMPTY_STRING);
    }
}
