package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        if (fromFileName == null || toFileName == null) {
            throw new RuntimeException("[fromFileName] OR [toFileName] cannot be NULL!");
        }

        File fileFrom = new File(fromFileName);
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        int resultBuy = 0;
        int resultSupply = 0;
        int buyCount = 0;
        int supplyCount = 0;
        int index = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(fileFrom));
            stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }

        String[] tokens = stringBuilder.toString().toLowerCase().split("\\s+");

        for (String token : tokens) {
            String cleaned = token.replaceAll("[^a-zA-Z]", "");
            if (!cleaned.isEmpty()) {
                index++;
            }
        }

        String[] infoBuy = new String[tokens.length];
        String[] infoSupply = new String[tokens.length];
        for (String inf : tokens) {
            if (inf.startsWith("buy")) {
                infoBuy[buyCount] = "buy" + (inf.substring(inf.indexOf(",")));
                buyCount++;
            }
            if (inf.startsWith("supply")) {
                infoSupply[supplyCount] = "supply" + (inf.substring(inf.indexOf(",")));
                supplyCount++;
            }
        }

        index = 0;
        String[] buyResult = new String[buyCount];
        String[] supplyResult = new String[supplyCount];
        for (String buy : infoBuy) {
            if (buy != null) {
                String[] parts = buy.split(",");
                buyResult[index++] = buy;
                resultBuy += Integer.parseInt(parts[1]);
            }
        }

        index = 0;
        for (String supply : infoSupply) {
            if (supply != null) {
                String[] parts = supply.split(",");
                supplyResult[index++] = supply;
                resultSupply += Integer.parseInt(parts[1]);
            }
        }

        int result = resultSupply - resultBuy;
        String buy = "buy," + resultBuy;
        String supply = "supply," + resultSupply;
        String r = "result," + result;

        try {
            FileWriter fileWriter = new FileWriter(toFileName);
            fileWriter.write(supply + System.lineSeparator());
            fileWriter.write(buy + System.lineSeparator());
            fileWriter.write(r + System.lineSeparator());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while trying to write a report file", e);
        }
    }
}
