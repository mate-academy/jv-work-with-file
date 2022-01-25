package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readData(fromFileName);
        String calculatedReport = calculateData(dataFromFile);
        writeDataToFile(calculatedReport, toFileName);
    }

    private String readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = "";
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(",");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Some problem with reading file: " + fromFileName, e);
        }
    }

    private String calculateData(String dataFromFile) {
        int supplySum = 0;
        int buySum = 0;
        String[] dataFromFileArray = dataFromFile.split(",");
        for (int i = 0; i < dataFromFileArray.length; i += 2) {
            if (dataFromFileArray[i].equals(SUPPLY)) {
                supplySum += Integer.parseInt(dataFromFileArray[i + 1]);
            }
            if (dataFromFileArray[i].equals(BUY)) {
                buySum += Integer.parseInt(dataFromFileArray[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY + SEPARATOR).append(supplySum).append(System.lineSeparator())
                .append(BUY + SEPARATOR).append(buySum).append(System.lineSeparator())
                .append(RESULT + SEPARATOR).append(supplySum - buySum)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Some problem with writing file: " + toFileName, e);
        }
    }
}
