package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listData;
        try {
            File file = new File(fromFileName);
            listData = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + toFileName, e);
        }
        try {
            Files.write(file.toPath(), calculatedData(listData).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }

    private String calculatedData(List<String> listData) {
        StringBuilder result = new StringBuilder();
        String[] supplyBuy;
        int sumSupply = 0;
        int sumBuy = 0;
        for (String line : listData) {
            supplyBuy = line.split(",");
            if ("supply".equals(supplyBuy[0])) {
                sumSupply += Integer.parseInt(supplyBuy[1]);
            }
            if ("buy".equals(supplyBuy[0])) {
                sumBuy += Integer.parseInt(supplyBuy[1]);
            }
        }
        result.append("supply,").append(sumSupply).append(System.lineSeparator());
        result.append("buy,").append(sumBuy).append(System.lineSeparator());
        result.append("result,").append(sumSupply - sumBuy).append(System.lineSeparator());
        return result.toString();
    }

}
