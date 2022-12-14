package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] result = countData(dataGetter(fromFileName));
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String each : result) {
                bufferedWriter.write(each);
                bufferedWriter.write("\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data");
        }
    }

    private Object[] dataGetter(String fromFileName) {
        File file = new File(fromFileName);
        List<String> dataFromFile;
        try {
            dataFromFile = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
        return dataFromFile.toArray();
    }

    private String[] countData(Object[] data) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (Object each : data) {
            String[] splittedData = each.toString().split(",");
            sumOfSupply += splittedData[0].equals("supply") ? Integer.parseInt(splittedData[1]) : 0;
            sumOfBuy += splittedData[0].equals("buy") ? Integer.parseInt(splittedData[1]) : 0;
        }
        int result = sumOfSupply - sumOfBuy;
        StringBuilder stringBuilder = new StringBuilder("supply,");
        stringBuilder.append(sumOfSupply).append("_")
                .append("buy,").append(sumOfBuy).append("_")
                .append("result,").append(result);
        return stringBuilder.toString().split("_");
    }
}
