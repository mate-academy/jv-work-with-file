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
    private static final String COMMA = ",";
    private static final String SPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = calculateSumBy(SUPPLY_WORD, readFile(fromFileName));
        int sumBuy = calculateSumBy(BUY_WORD, readFile(fromFileName));

        writeToFile(generateReport(sumSupply, sumBuy), toFileName);
    }

    private String[] readFile(String fromFileName) {
        File file = new File(fromFileName);
        String[] arrayFromFile;
        try {
            arrayFromFile = Files.readAllLines(file.toPath()).toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + file, e);
        }
        return arrayFromFile;
    }

    private int calculateSumBy(String nameAction, String[] inputData) {
        int sumAction = 0;
        for (int i = 0; i < inputData.length; i++) {
            String[] reportValue = inputData[i].split(COMMA);
            if (reportValue[NAME_INDEX].equals(nameAction)) {
                sumAction += Integer.parseInt(reportValue[NUMBER_INDEX]);
            }
        }
        return sumAction;
    }

    private String[] generateReport(int sumSupply, int sumBuy) {
        StringBuilder giveReport = new StringBuilder();
        giveReport.append(SUPPLY_WORD).append(COMMA).append(sumSupply).append(SPACE)
                .append(BUY_WORD).append(COMMA).append(sumBuy).append(SPACE)
                .append(RESULT_WORD).append(COMMA).append(sumSupply - sumBuy);

        return giveReport.toString().split(SPACE);
    }

    private void writeToFile(String[] reportData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (String valueReport : reportData) {
                writer.write(valueReport);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
