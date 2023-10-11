package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final int TEXT_ELEMENTS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int buyCount = 0;
        int supplyCount = 0;
        int result = 0;
        writeReport(toFileName, readFileName(fromFileName, buyCount,supplyCount,result));
    }

    private int[] readFileName(String fromFileName, int buyCount, int supplyCount, int result) {
        File fileReader = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReader))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] text = line.split(COMA);
                if (text.length == TEXT_ELEMENTS) {
                    String word = text[0];
                    int count = Integer.parseInt(text[1]);
                    if (word.equals(BUY)) {
                        buyCount += count;
                    }
                    if (word.equals(SUPPLY)) {
                        supplyCount += count;
                    }
                }
                result = supplyCount - buyCount;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file" + fromFileName, e);
        }
        return new int[]{supplyCount, buyCount, result};
    }

    private void writeReport(String toFileName, int[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            createReport(bufferedWriter, report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write report", e);
        }
    }

    private void createReport(BufferedWriter bufferedWriter, int[] report) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(COMA).append(report[0]).append(System.lineSeparator());
        sb.append(BUY).append(COMA).append(report[1]).append(System.lineSeparator());
        sb.append(RESULT).append(COMA).append(report[2]).append(System.lineSeparator());
        bufferedWriter.write(sb.toString());
    }
}
