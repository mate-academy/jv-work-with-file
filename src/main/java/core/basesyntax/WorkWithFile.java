package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathFrom = Paths.get(fromFileName).toAbsolutePath();
        File fileFrom = new File(pathFrom.toString());
        StringBuilder textBuilder = new StringBuilder();
        try {
            textBuilder.append(Files.readAllLines(fileFrom.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] dataFile = textBuilder.toString().replaceAll("\\[|\\]|\\s", "").split(",");
        int sumSupply = 0;
        int sumBuy = 0;
        for (int i = 0; i + 2 < dataFile.length + 2; i += 2) {
            String act = dataFile[i];
            int countGoods = Integer.valueOf(dataFile[i + 1]);
            switch (act) {
                case "supply":
                    sumSupply += countGoods;
                    break;
                case "buy":
                    sumBuy += countGoods;
                    break;
                default:
                    throw new IllegalActEcxeption("Action case not found");
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        Path pathTo = Paths.get(toFileName).toAbsolutePath();
        File fileTo = new File(pathTo.toString());
        try {
            Files.write(fileTo.toPath(), resultBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }

    }
}
