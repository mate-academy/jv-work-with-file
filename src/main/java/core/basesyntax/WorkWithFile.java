package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String dataRead = readFromFile(fromFileName);
        StringBuilder sumData = sumData(dataRead);
        File file = new File(toFileName);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(String.valueOf(sumData));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't write file");
            }
        }
    }

    private String readFromFile(String fromFileName) {
        String stringFromFile = null;
        try {
            stringFromFile = new String(Files.readAllBytes(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringFromFile;
    }

    private StringBuilder sumData(String data) {
        String[] dataSeparate = data.split("\r\n");
        String supply = "supply";
        int supplySum = 0;
        String buy = "buy";
        int buySum = 0;
        String result = "result";
        for (int i = 0; i < dataSeparate.length; i++) {
            if (dataSeparate[i].contains("supply")) {
                supplySum = supplySum + Integer.parseInt(dataSeparate[i].split(",")[1]);
            }
            if (dataSeparate[i].contains("buy")) {
                buySum = buySum + Integer.parseInt(dataSeparate[i].split(",")[1]);
            }
        }
        int resultTotal = supplySum - buySum;
        StringBuilder returnResult = new StringBuilder();
        return returnResult.append(supply).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(buy).append(",").append(buySum).append(System.lineSeparator())
                .append(result).append(",").append(resultTotal);
    }
}
