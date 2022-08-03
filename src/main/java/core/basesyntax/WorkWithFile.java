package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dividedStrings = readFromFile(fromFileName);
        String[] report = makeReport(dividedStrings);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String result = builder.toString();
        String[] dividedString = result.split("\n");
        Arrays.sort(dividedString);
        return dividedString;
    }

    private String[] makeReport(String[] dividedString) {
        int buyWord = 0;
        int supplyWord = 0;
        for (int i = 0; i < dividedString.length; i++) {
            int comaPlace = dividedString[i].indexOf(',');
            String cutWord = dividedString[i].substring(0, comaPlace);
            String cutNumber = dividedString[i].substring(comaPlace
                    + 1, dividedString[i].length() - 1);
            Integer number = Integer.valueOf(cutNumber);
            if (cutWord.equals(BUY)) {
                buyWord += number;
            } else if (cutWord.equals(SUPPLY)) {
                supplyWord += number;
            }
        }
        int differenceBuySupply = supplyWord - buyWord;
        String buyString = String.valueOf(buyWord);
        String supplyString = String.valueOf(supplyWord);
        String differenceString = String.valueOf(differenceBuySupply);
        String[] buysAndSupplies = new String[3];
        buysAndSupplies[0] = "supply," + supplyString + System.lineSeparator();
        buysAndSupplies[1] = "buy," + buyString + System.lineSeparator();
        buysAndSupplies[2] = "result," + differenceString;
        return buysAndSupplies;
    }

    private void writeToFile(String toFileName, String[] buysAndSupplies) {
        for (String buySupply : buysAndSupplies) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new
                    FileWriter(toFileName, true))) {
                bufferedWriter.write(buySupply);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
