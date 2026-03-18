package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String[] content;
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            supply = 0;
            buy = 0;
            result = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = line.split(",");
                String operation = content[OPERATION_TYPE_INDEX].trim();
                int amount = Integer.parseInt(content[AMOUNT_INDEX]);

                if (operation.equals(SUPPLY)) {
                    supply += amount;
                } else if (operation.equals(BUY)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File is not found", e);
        }
        writer(toFileName, result, supply, buy);

    }

    private void writer(String toFileName, int result, int supply, int buy) {
        result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
