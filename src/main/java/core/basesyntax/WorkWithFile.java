package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    public static final int LABEL_COLUMN_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] recordsArray = readFileToArray(fromFileName);
        int supplyTotal = getSupplyTotal(recordsArray);
        int buyTotal = getBuyTotal(recordsArray);
        int result = getResult(supplyTotal, buyTotal);
        String[][] report = createReport(supplyTotal, buyTotal, result);
        try {
            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String[][] readFileToArray(String fromFileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            String[][] recordsArray = records.stream()
                    .map(l -> l.stream().toArray(String[]::new))
                    .toArray(String[][]::new);
            return recordsArray;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        }
    }

    private int getSupplyTotal(String[][] recordsArray) {
        int supplyTotal = 0;
        for (int i = 0; i < recordsArray.length; i++) {
            if (recordsArray[i][LABEL_COLUMN_INDEX].equals("supply")) {
                supplyTotal += Integer.valueOf(recordsArray[i][1]);
            }
        }
        return supplyTotal;
    }

    private int getBuyTotal(String[][] recordsArray) {
        int buyTotal = 0;
        for (int i = 0; i < recordsArray.length; i++) {
            if (recordsArray[i][LABEL_COLUMN_INDEX].equals("buy")) {
                buyTotal += Integer.valueOf(recordsArray[i][1]);
            }
        }
        return buyTotal;
    }

    private int getResult(int supplyTotal, int buyTotal) {
        return supplyTotal - buyTotal;
    }

    private String[][] createReport(int supplyTotal, int buyTotal, int result) {
        String[][] report = new String[3][2];
        report[0][0] = "supply";
        report[0][1] = String.valueOf(supplyTotal);
        report[1][0] = "buy";
        report[1][1] = String.valueOf(buyTotal);
        report[2][0] = "result";
        report[2][1] = String.valueOf(result);
        return report;
    }

    private void writeToFile(String[][] report, String toFileName) throws IOException {
        File newFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile))) {
            for (int i = 0; i < report.length; i++) {
                for (int j = 0; j < report[i].length; j++) {
                    bufferedWriter.append(report[i][j]);
                    if (j < report[i].length - 1) {
                        bufferedWriter.append(",");
                    }
                }
                bufferedWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
