package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {
        File myFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> listOfSupplyCosts = new ArrayList<>();
        List<Integer> listOfBuyCosts = new ArrayList<>();
        String result = calculateResult(fromFileName, listOfSupplyCosts,
                listOfBuyCosts, stringBuilder);
        createAndWriteToFile(toFileName, result);
    }

    public static String calculateResult(String fromFile, List<Integer> supplies,
                                         List<Integer> buys, StringBuilder stringBuilder) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
            String string = bufferedReader.readLine();
            while (string != null) {
                String[] splitedString = string.split(" ");
                for (String value : splitedString) {
                    if (value.contains("supply")) {
                        supplies.add(Integer.valueOf(value
                                .substring(value.indexOf(",") + 1, value.length())));
                    } else {
                        buys.add(Integer.valueOf(value
                                .substring(value.indexOf(",") + 1, value.length())));
                    }
                }
                string = bufferedReader.readLine();
            }

            int sumOfSupplies = 0;
            for (int number : supplies) {
                sumOfSupplies += number;
            }

            int sumOfBuys = 0;
            for (int number : buys) {
                sumOfBuys += number;
            }
            return stringBuilder.append("supply,").append(sumOfSupplies)
                    .append(System.lineSeparator()).append("buy,")
                    .append(sumOfBuys).append(System.lineSeparator())
                    .append("result,").append(sumOfSupplies - sumOfBuys).toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }
    }

    public static void createAndWriteToFile(String toFileName, String data) {
        File resultFile = new File(toFileName);
        try {
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try {
            Files.write(resultFile.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public static void main(String[] args) {
        getStatistic("apple.csv", "appleResult.csv");
    }
}
