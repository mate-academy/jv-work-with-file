package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String VALUE_CRITERIA = "supply";
    private static final String LINE_SEPARATOR = " ";

    public static void getStatistic(String fromFileName, String toFileName) {
        List<Integer> listOfSupplyCosts = new ArrayList<>();
        List<Integer> listOfBuyCosts = new ArrayList<>();
        readFromFile(fromFileName, listOfSupplyCosts, listOfBuyCosts);
        int sumOfSupplies = getSumOfList(listOfSupplyCosts);
        int sumOfBuys = getSumOfList(listOfBuyCosts);
        String result = createReport(sumOfSupplies, sumOfBuys, sumOfSupplies - sumOfBuys);
        createAndWriteToFile(toFileName, result);
    }

    public static void readFromFile(String fromFile, List<Integer> supplies, List<Integer> buys) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String string = bufferedReader.readLine();
            String[] splitedString = null;

            while (string != null) {
                splitedString = string.split(LINE_SEPARATOR);
                for (String value : splitedString) {
                    if (value.contains(VALUE_CRITERIA)) {
                        supplies.add(Integer.valueOf(value
                                .substring(value.indexOf(",") + 1, value.length())));
                    } else {
                        buys.add(Integer.valueOf(value
                                .substring(value.indexOf(",") + 1, value.length())));
                    }
                }
                string = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file " + fromFile + " ", e);
        }
    }

    public static int getSumOfList(List<Integer> list) {
        int sum = 0;
        for (int number : list) {
            sum += number;
        }
        return sum;
    }

    public static String createReport(int sumOfSupplies, int sumOfBuys, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(sumOfSupplies)
                .append(System.lineSeparator()).append("buy,")
                .append(sumOfBuys).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    public static void createAndWriteToFile(String toFileName, String data) {
        try {
            Files.write(new File(toFileName).toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName + " ", e);
        }
    }
}
