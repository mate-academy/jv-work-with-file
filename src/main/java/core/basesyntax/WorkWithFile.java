package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_COLUMN = 0;
    private static final int AMOUNT_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,
                           true))) {
            String value = reader.readLine();
            int amountSupply = 0;
            int amountBuy = 0;
            while (value != null) {
                String[] record = value.split(",");
                if (record[OPERATION_TYPE_COLUMN].equals("supply")) {
                    amountSupply += Integer.parseInt(record[AMOUNT_COLUMN]);
                } else {
                    amountBuy += Integer.parseInt(record[AMOUNT_COLUMN]);
                }
                value = reader.readLine();
            }
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("supply,")
                    .append(amountSupply)
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(amountBuy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(amountSupply - amountBuy);
            bufferedWriter.write(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
    }
}
