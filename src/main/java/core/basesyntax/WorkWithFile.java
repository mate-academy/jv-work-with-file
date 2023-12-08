package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fileGotRead = readFromFile(fromFileName);
        String[] dividedByEmptySpace = fileGotRead.split(" ");
        int supplySum = getSupplySum(dividedByEmptySpace);
        int buySum = getBuySum(dividedByEmptySpace);
        int benefit = supplySum - buySum;
        writeToFile(toFileName, supplySum, buySum, benefit);
    }

    public int getSupplySum(String[] dividedByEmptySpace) {
        int supplySum = 0;
        for (String info : dividedByEmptySpace) {
            String[] dividedByComma = info.split(",");
            String firstElement = dividedByComma[0];
            int amountOfMoney = Integer.parseInt(dividedByComma[1]);
            if (firstElement.equals("supply")) {
                supplySum += amountOfMoney;
            }
        }
        return supplySum;
    }

    public int getBuySum(String[] dividedByEmptySpace) {
        int buySum = 0;
        for (String info : dividedByEmptySpace) {
            String[] dividedByComma = info.split(",");
            String firstElement = dividedByComma[0];
            int amountOfMoney = Integer.parseInt(dividedByComma[1]);
            if (firstElement.equals("buy")) {
                buySum += amountOfMoney;
            }
        }
        return buySum;
    }

    public String readFromFile(String fromFileName) {
        StringBuilder fullFileText = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                fullFileText.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("There is an exception at line 70 named ", e);
        }
        return fullFileText.toString();
    }

    public void writeToFile(String toFileName, int supplySum, int buySum, int benefit) {
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
