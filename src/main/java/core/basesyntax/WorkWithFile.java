package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        int sumOfSupply = 0;
        int sumOfBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitArray = line.split(",");
                if (splitArray.length == 2) {
                    String operation = splitArray[0].trim();
                    int amount = Integer.parseInt(splitArray[1].trim());

                    if ("supply".equals(operation)) {
                        sumOfSupply += amount;
                    } else if ("buy".equals(operation)) {
                        sumOfBuy += amount;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        int result = sumOfSupply - sumOfBuy;

        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(saveToFile(result, sumOfSupply, sumOfBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }

    public static String saveToFile(int result, int sumOfSupply, int sumOfBuy) {
        StringBuilder resultToFile = new StringBuilder();
        resultToFile.append("supply,").append(sumOfSupply)
                .append(System.lineSeparator()).append("buy,")
                .append(sumOfBuy).append(System.lineSeparator())
                .append("result,").append(result);

        return resultToFile.toString();
    }
}
