package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        String allTextFile = readFile(fromFileName);
        supply = calculetedSupplyForReport(allTextFile);
        buy = calculetedBuyForReport(allTextFile);
        writeToFile(supply,buy,toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(" ");
                data = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with read file",e);
        }
        return stringBuilder.toString();
    }

    private int calculetedSupplyForReport(String allTextFile) {
        int intSupply = 0;
        String[] values = allTextFile.split("\\s+");
        for (String value : values) {
            String[] eachValue = value.split(",");
            if (eachValue[0].equals(SUPPLY)) {
                intSupply += Integer.parseInt(eachValue[1]);
            }
        }
        return intSupply;
    }

    private int calculetedBuyForReport(String allTextFile) {
        int intBuy = 0;
        String[] values = allTextFile.split("\\s+");
        for (String value : values) {
            String[] eachValue = value.split(",");
            if (eachValue[0].equals(BUY)) {
                intBuy += Integer.parseInt(eachValue[1]);
            }
        }
        return intBuy;
    }

    private void writeToFile(int supply, int buy, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + "," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write(BUY + "," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Error with record to file", e);
        }
    }

}


