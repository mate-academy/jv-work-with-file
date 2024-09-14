package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        Counters counters = new Counters(0,0);
        int result = readFromFile(fromFileName, counters);
        String data = infoGathering(counters, result);
        writeToFile(toFileName, data);
    }

    public int readFromFile(String fromFileName, Counters counters)
            throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataFromFile = line.split(COMMA);
                int amount = Integer.parseInt(dataFromFile[AMOUNT]);

                if (dataFromFile[OPERATION_TYPE].equals(OPERATION_SUPPLY)) {
                    counters.supplyCounter += amount;
                } else {
                    counters.buyCounter += amount;
                }

            }
        } catch (IOException e) {
            throw new IOException("File can not be open", e);
        }

        return counters.supplyCounter - counters.buyCounter;
    }

    public void writeToFile(String toFileName, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data.toString());
        } catch (IOException e) {
            throw new IOException("can not write to file", e);
        }
    }

    public String infoGathering(Counters counters, int result) {
        return new StringBuilder(OPERATION_SUPPLY
                + COMMA + counters.supplyCounter + System.lineSeparator()
                + OPERATION_BUY + COMMA + counters.buyCounter + System.lineSeparator()
                + OPERATION_RESULT + COMMA + result + System.lineSeparator()).toString();
    }
}
