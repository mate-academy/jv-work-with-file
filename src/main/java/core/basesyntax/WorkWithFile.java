package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final String BUY_WORD = "buy";
    private static final String CSV_DELIMITER = ",";
    private static final String RESULT_WORD = "result";
    private static final String SUPPLY_WORD = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fromFileName)));
            String value = bufferedReader.readLine();
            while (value != null){
                String[] temp = value.split(CSV_DELIMITER);
                if (temp[0].equals(SUPPLY_WORD)) {
                    totalSupply += Integer.parseInt(temp[1]);
                }
                if (temp[0].equals(BUY_WORD)) {
                    totalBuy += Integer.parseInt(temp[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(SUPPLY_WORD + CSV_DELIMITER)
                     .append(totalSupply)
                     .append(System.lineSeparator());
        stringBuilder.append(BUY_WORD + CSV_DELIMITER)
                     .append(totalBuy)
                     .append(System.lineSeparator());
        stringBuilder.append(RESULT_WORD + CSV_DELIMITER)
                     .append(totalSupply - totalBuy)
                     .append(System.lineSeparator());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(toFileName)));
            String[] outputStrings = stringBuilder.toString().split(System.lineSeparator());
            for (String outputString : outputStrings) {
                bufferedWriter.write(outputString);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t write in file", e);
        }
    }
}
