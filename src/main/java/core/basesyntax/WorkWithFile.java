package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String[] operationType = new String[]{"supply", "buy"};

    private int supplyAmount;
    private int buyAmount;
    private int finalAmount;
    private StringBuilder strBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        String extractedData = extractData(new File(fromFileName));
        String[] splittedData = extractedData.split(",");
        for (int i = 0; i < splittedData.length; i += 2) {
            if (splittedData[i].equals(operationType[0])) {
                supplyAmount += Integer.parseInt(splittedData[i + 1]);
            } else if (splittedData[i].equals(operationType[1])) {
                buyAmount += Integer.parseInt(splittedData[i + 1]);
            }
        }
        String dataToWrite = String.format("%s,%d%n%s,%d%nresult,%d",
                operationType[0], supplyAmount,
                operationType[1], buyAmount, (supplyAmount - buyAmount));
        writeData(dataToWrite.getBytes(), new File(toFileName).toPath());
    }

    private String extractData(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                strBuilder.append(line).append(",");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + file.toString());
        }
        return strBuilder.toString();
    }

    private void writeData(byte[] bytes, Path file) {
        try {
            Files.write(file, bytes);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + file.toString());
        }
    }
}
