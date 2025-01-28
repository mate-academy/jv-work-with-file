package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))){

            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts[0].equals("buy")) {
                    totalBuy += Integer.parseInt(parts[1]);

                } else {
                    totalSupply += Integer.parseInt(parts[1]);

                }
                value = reader.readLine();
            }
            int result = totalSupply - totalBuy;

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("supply,").append(totalSupply).append(System.lineSeparator());
                stringBuilder.append("buy,").append(totalBuy).append(System.lineSeparator());
                stringBuilder.append("result,").append(result).append(System.lineSeparator());
                bufferedWriter.write(String.valueOf(stringBuilder));
            }

        } catch (IOException e) {
            throw new RuntimeException("Oops file is dead", e);

        }
    }
}
