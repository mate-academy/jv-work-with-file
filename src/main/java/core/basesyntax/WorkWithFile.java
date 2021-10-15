package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        String valueBaySupply = new String();
        int valueBay;
        int valueSupply;
        int valueDayBay = 0;
        int valueDaySupply = 0;
        int result = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                int indexComa = value.indexOf(',');
                valueBaySupply = value.substring(indexComa + 1, value.length());

                switch (indexComa) {
                    case 3:
                        valueBay = Integer.parseInt(valueBaySupply);
                        valueDayBay += valueBay;
                        break;
                    case 6:
                        valueSupply = Integer.parseInt(valueBaySupply);
                        valueDaySupply += valueSupply;
                        break;
                    default: break;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        result = valueDaySupply - valueDayBay;
        System.out.println("supply," + valueDaySupply);
        System.out.println("bay," + valueDayBay);
        System.out.println("result," + result);
        File file1 = new File(toFileName);

        try {
            if (file1.delete()) {
                file1.createNewFile();
            }
        } catch (IOException e1) {
            throw new RuntimeException("Can't delete/create file", e1);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file1, true))) {
            bufferedWriter.write("supply," + valueDaySupply + "\n");
            bufferedWriter.write("bay," + valueDayBay + "\n");
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
