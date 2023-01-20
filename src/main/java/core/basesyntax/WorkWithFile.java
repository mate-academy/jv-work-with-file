package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";
    private static final String RESULT_WORD = "result";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String GET_SUM_SUPPLY = "getSupply";
    private static final String GET_SUM_BUY = "getBuy";
    private static final String COMMA = ",";
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayReport = writeToFile(fromFileName, toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String valueReport : arrayReport) {
                writer.write(valueReport);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fromFileName, e);
        }
    }

    private String[] readFile(String fileName) {
        File file = new File(fileName);
        String[] arrayFile;
        try {
            arrayFile = Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return arrayFile;
    }

    private int generateReport(String getSum, String fileName) {
        String[] arrayReport = readFile(fileName);
        int sumSupply = 0;
        int sumBuy = 0;

        for (int i = 0; i < arrayReport.length; i++) {
            String[] reportValue = arrayReport[i].split(COMMA);
            sumSupply += reportValue[NAME_INDEX].equals(SUPPLY_WORD)
                    ? Integer.parseInt(reportValue[NUMBER_INDEX]) : 0;
            sumBuy += reportValue[NAME_INDEX].equals(BUY_WORD)
                    ? Integer.parseInt(reportValue[NUMBER_INDEX]) : 0;
        }
        if (getSum.equals(GET_SUM_SUPPLY)) {
            return sumSupply;
        }
        return sumBuy;
    }

    private String[] writeToFile(String fromFileName, String toFileName) {
        int sumSupply = generateReport(GET_SUM_SUPPLY, fromFileName);
        int sumBuy = generateReport(GET_SUM_BUY, fromFileName);
        int sumResult = sumSupply - sumBuy;
        StringBuilder giveReport = new StringBuilder();
        giveReport.append(SUPPLY_WORD).append(COMMA).append(sumSupply).append(SPACE)
                .append(BUY_WORD).append(COMMA).append(sumBuy).append(SPACE)
                .append(RESULT_WORD).append(COMMA).append(sumResult);
        String[] arrayReport = giveReport.toString().split(SPACE);

        return arrayReport;
    }
}
