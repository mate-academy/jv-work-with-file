package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String VALUE_CRITERIA = "supply";
    private static final String SPLIT_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        int[] statistic = countStatistic(dataFromFile);
        String report = createReport(statistic);
        writeToFile(toFileName, report);
    }

    private int[] countStatistic(List<String> dataFromFile) {
        int sumOfSupplies = 0;
        int sumOfBuys = 0;
        String[] array;
        for (int i = 0; i < dataFromFile.size(); i++) {
            array = dataFromFile.get(i).split(SPLIT_REGEX);
            if (array[0].equals(VALUE_CRITERIA)) {
                sumOfSupplies += Integer.parseInt(array[1]);
            } else {
                sumOfBuys += Integer.parseInt(array[1]);
            }
        }
        return new int[]{sumOfSupplies, sumOfBuys};
    }

    private List<String> readFromFile(String fileName) {
        try {
            List<String> list = Files.readAllLines(new File(fileName).toPath());
            return list;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName + "", e);
        }
    }

    private void writeToFile(String toFileName, String data) {
        try {
            Files.write(new File(toFileName).toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName + " ", e);
        }
    }

    private String createReport(int[] statistic) {
        final int supply = statistic[0];
        final int buys = statistic[1];
        final int result = supply - buys;
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,")
                .append(buys).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }
}
