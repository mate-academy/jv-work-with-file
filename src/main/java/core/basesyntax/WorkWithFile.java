package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fullFileText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                fullFileText.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("There is an exception at line 17 named ", e);
        }
        String[] dividedByEmptySpace = fullFileText.toString().split(" ");
        int supplySum = 0;
        int buySum = 0;
        for (String info : dividedByEmptySpace) {
            String[] dividedByComma = info.split(",");
            int amountOfMoney = Integer.parseInt(dividedByComma[1]);
            String firstElememt = dividedByComma[0];
            if (firstElememt.equals("supply")) {
                supplySum += amountOfMoney;
            }
            if (firstElememt.equals("buy")) {
                buySum += amountOfMoney;
            }
        }
        int benefit = supplySum - buySum;
        StringBuilder result = new StringBuilder("supply,").append(supplySum)
                .append("\n").append("buy,").append(buySum)
                .append("\n").append("result,").append(benefit);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("There is an exception at line 36 named ", e);
        }
    }
}
