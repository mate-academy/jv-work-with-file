package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY_LENGTH = 7;
    private static final int INDEX_OF_BUY_LENGTH = 4;

    public void getStatistic(String fromFileName, String toFileName) {
        File fileForRead = new File(fromFileName);
        StringBuilder firstBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileForRead))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                firstBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't reaad this file", e);
        }
        int countSupply = 0;
        int countBuy = 0;
        String [] amounts = firstBuilder.toString().split(" ");
        for (String amount : amounts) {
            if (amount.contains("supply")) {
                countSupply += Integer.parseInt(amount.substring(INDEX_OF_SUPPLY_LENGTH,
                        amount.length()));
            }
            if (amount.contains("buy")) {
                countBuy += Integer.parseInt(amount.substring(INDEX_OF_BUY_LENGTH,
                        amount.length()));
            }
        }
        String writeSupply = "supply," + countSupply;
        String writeBuy = "buy," + countBuy;
        int countResult = countSupply - countBuy;
        String writeResult = "result," + countResult;
        String [] arrayResults = new String[] {writeSupply, writeBuy, writeResult};
        File fileForWright = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try (BufferedWriter writer = bufferedWriter =
                new BufferedWriter(new FileWriter(fileForWright))) {
            writer.write("");
        } catch (IOException e1) {
            throw new RuntimeException("Can't right this file", e1);
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileForWright, true));
            for (String arrayResult : arrayResults) {
                bufferedWriter.write(arrayResult);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't right this file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close bufferedWriter", e);
                }
            }
        }
    }
}
