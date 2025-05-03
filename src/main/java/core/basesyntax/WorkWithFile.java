package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String OPERATION_TYPE_SUPPLY = "supply";
    public static final String OPERATION_TYPE_BUY = "buy";
    public static final String COMA_DELIMITER = ",";
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeDataToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(COMA_DELIMITER);
                line = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can not read fileName " + fileName, e);
        }
    }

    private String createReport(String data) {
        String[] dataPeaces = data.split(COMA_DELIMITER);
        int supply = 0;
        int buy = 0;
        int peaceIndex = 0;
        for (String peace: dataPeaces) {
            if (peace.equals(OPERATION_TYPE_SUPPLY)) {
                supply += Integer.parseInt(dataPeaces[peaceIndex + 1]);
            }
            if (peace.equals(OPERATION_TYPE_BUY)) {
                buy += Integer.parseInt(dataPeaces[peaceIndex + 1]);
            }
            peaceIndex++;
        }
        return "supply," + supply + "\n" + "buy," + buy + "\n" + "result," + (supply - buy);
    }

    private void writeDataToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file " + fileName, e);
        }
    }
}
