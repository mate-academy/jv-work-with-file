package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATED_CHARACTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] block = currentLine.split(SEPARATED_CHARACTER);
                String operationType = block[0].trim();
                int amount = Integer.parseInt(block[1].trim());

                if (operationType.equals(SUPPLY)) {
                    supply += amount;
                } else if (operationType.equals(BUY)) {
                    buy += amount;
                }
            }

            int result = supply - buy;
            writeStatisticsToFile(toFileName, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException("Error with reading file: " + fromFileName, e);
        }
    }

    private void writeStatisticsToFile(String toFileName, int supply, int buy, int result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + SEPARATED_CHARACTER + supply);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + SEPARATED_CHARACTER + buy);
            bufferedWriter.newLine();
            bufferedWriter.write(RESULT + SEPARATED_CHARACTER + result);
        } catch (IOException e) {
            throw new RuntimeException("Error with writing to file: " + toFileName, e);
        }
    }
}
