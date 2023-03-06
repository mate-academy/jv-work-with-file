package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_SYMBOL = " ";
    private static final String SPLIT_COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readTheFile(fromFileName);
        int supply = 0;
        int buy = 0;

        for (String line : linesFromFile) {
            String[] lineElements = line.split(SPLIT_COMMA);
            if (SUPPLY.equals(lineElements[OPERATION])) {
                supply += Integer.parseInt(lineElements[AMOUNT]);
            } else {
                buy += Integer.parseInt(lineElements[AMOUNT]);
            }
        }
        int result = supply - buy;
        writeIntoFile(toFileName, supply, buy, result);
    }

    public String[] readTheFile(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(SPLIT_SYMBOL);
            }
            String finalText = stringBuilder.toString();
            return finalText.length() == 0 ? new String[0] : finalText.split(SPLIT_SYMBOL);
        } catch (Exception e) {
            throw new RuntimeException("File is absent");
        }
    }

    public void writeIntoFile(String file, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(SPLIT_COMMA).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(SPLIT_COMMA).append(buy).append(System.lineSeparator())
                    .append(RESULT).append(SPLIT_COMMA).append(result);
            bufferedWriter.write("");
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
