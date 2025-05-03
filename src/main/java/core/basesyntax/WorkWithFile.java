package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int FIRST_INDEX = 0;
    private static final int NEXT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lineFromScV = reader.readLine();

            while (lineFromScV != null) {
                data.append(lineFromScV).append(System.lineSeparator());
                lineFromScV = reader.readLine();
            }
            return data.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the data from " + fromFileName, e);
        }

    }

    private String createReport(String dataFromFile) {
        int supplyAmout = 0;
        int buyAmount = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());

        for (String line: lines) {
            String[] partsOfData = line.split(",");
            if (partsOfData[FIRST_INDEX].equals(SUPPLY)) {
                supplyAmout += Integer.parseInt(partsOfData[NEXT_INDEX]);
            } else if (partsOfData[FIRST_INDEX].equals(BUY)) {
                buyAmount += Integer.parseInt(partsOfData[NEXT_INDEX]);
            }

        }
        int result = supplyAmout - buyAmount;
        return SUPPLY + "," + supplyAmout + System.lineSeparator() + BUY
                + "," + buyAmount + System.lineSeparator() + "result" + ","
                + result + System.lineSeparator();
    }

    private void writeReportToFile(String report,String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write the data to " + toFileName, e);
        }
    }
}
