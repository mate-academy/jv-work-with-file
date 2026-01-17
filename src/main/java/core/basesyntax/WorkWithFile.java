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

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] entry = value.split(",");
                if (entry[0].equals("supply")) {
                    totalSupply += Integer.parseInt(entry[1]);
                } else if (entry[0].equals("buy")) {
                    totalBuy += Integer.parseInt(entry[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;
        
        // Обрати внимание: плюсы теперь в начале строк, как просил Checkstyle
        String report = "supply," + totalSupply
                + System.lineSeparator() + "buy," + totalBuy
                + System.lineSeparator() + "result," + result;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
