package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int generalSupply = 0;
        int generalBuy = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] temp = value.split(",");
                if (temp[0].equals("supply")) {
                    generalSupply += Integer.parseInt(temp[1]);
                } else if (temp[0].equals("buy")) {
                    generalBuy += Integer.parseInt(temp[1]);
                }
            }
            result = generalSupply - generalBuy;
            bufferedWriter.write("supply," + generalSupply + System.lineSeparator());
            bufferedWriter.write("buy," + generalBuy + System.lineSeparator());
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
