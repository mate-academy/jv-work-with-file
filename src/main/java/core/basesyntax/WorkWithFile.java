package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int POSITION_OF_CATEGORY_IN_DATA = 0;
    private static final int POSITION_OF_COST_IN_DATA = 1;

    private void writeToFile(String toFileName, String data) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't open a file" + e);
        }
    }

    private String createReport(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int supplyTotal = 0;
            int buyTotal = 0;
            String lineFromFile = bufferedReader.readLine();

            while (lineFromFile != null) {
                String[] splitResult = lineFromFile.split(",");
                if (splitResult[POSITION_OF_CATEGORY_IN_DATA].equals("supply")) {
                    supplyTotal += Integer.parseInt(splitResult[POSITION_OF_COST_IN_DATA]);
                } else {
                    buyTotal += Integer.parseInt(splitResult[POSITION_OF_COST_IN_DATA]);
                }
                lineFromFile = bufferedReader.readLine();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator());
            stringBuilder.append("buy,").append(buyTotal).append(System.lineSeparator());
            stringBuilder.append("result,").append(supplyTotal - buyTotal);

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't open a file" + e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String createdReport = createReport(fromFileName);
        writeToFile(toFileName, createdReport);
    }
}
