package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFile = getRawDataFromFile(fromFileName);
        String report = createReport(dataFile);
        writeReportToFile(report, toFileName);
    }

    private String[] getRawDataFromFile(String fromFileName) {
        Path pathFrom = Paths.get(fromFileName).toAbsolutePath();
        StringBuilder textBuilder = new StringBuilder();
        try {
            Files.lines(pathFrom).forEach(data -> textBuilder
                    .append(data)
                    .append(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return textBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        int sumSupply = 0;
        int sumBuy = 0;
        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(",");
            String act = splitData[0];
            int countGoods = Integer.valueOf(splitData[1]);
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
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeReportToFile(String report, String toFieName) {
        Path pathTo = Paths.get(toFieName).toAbsolutePath();
        File fileTo = new File(pathTo.toString());
        try {
            Files.write(fileTo.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
