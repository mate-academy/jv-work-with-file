package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public static final String[] OPERATION_TYPE = new String[]{"supply", "buy"};
    public static final String DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String extractedData = extractData(new File(fromFileName));
        String dataToWrite = calculateStatistic(extractedData);
        writeData(dataToWrite, toFileName);
    }

    private String extractData(File file) {
        StringBuilder strBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                strBuilder.append(line).append(DATA_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + file);
        }
        return strBuilder.toString();
    }

    private void writeData(String dataToWrite, String file) {
        byte[] bytes = dataToWrite.getBytes();
        try {
            Files.write(new File(file).toPath(), bytes);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + file);
        }
    }

    private String calculateStatistic(String data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] splittedData = data.split(DATA_SEPARATOR);
        for (int i = 0; i < splittedData.length; i += 2) {
            if (splittedData[i].equals(OPERATION_TYPE[0])) {
                supplyAmount += Integer.parseInt(splittedData[i + 1]);
            } else if (splittedData[i].equals(OPERATION_TYPE[1])) {
                buyAmount += Integer.parseInt(splittedData[i + 1]);
            }
        }
        return String.format("%s,%d%n%s,%d%nresult,%d",
                OPERATION_TYPE[0], supplyAmount,
                OPERATION_TYPE[1], buyAmount, (supplyAmount - buyAmount));
    }
}
