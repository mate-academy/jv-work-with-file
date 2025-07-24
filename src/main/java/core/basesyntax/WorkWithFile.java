package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File readFile = new File(fromFileName);
        File writeFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(readFile))) {
            String value = reader.readLine();

            while (value != null) {
                String[] partsOfOneLine = value.split(",");
                if (partsOfOneLine[0].equals("supply")) {
                    totalSupplyAmount += Integer.parseInt(partsOfOneLine[1]);
                }
                if (partsOfOneLine[0].equals("buy")) {
                    totalBuyAmount += Integer.parseInt(partsOfOneLine[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can not read the file",e);
        }

        int result = totalSupplyAmount - totalBuyAmount;
        String finalStatistic = builder
                .append("supply,").append(totalSupplyAmount).append(System.lineSeparator())
                .append("buy,").append(totalBuyAmount).append(System.lineSeparator())
                .append("result,").append(result).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
            writer.write(finalStatistic);
        } catch (IOException e) {
            throw new RuntimeException("can not write to the file", e);
        }
    }
}
