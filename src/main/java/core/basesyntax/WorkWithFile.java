package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String str = bufferedReader.readLine();
            while (str != null) {
                String[] arrStr = str.split(",");
                if (arrStr[0].equals("supply")) {
                    sumSupply += Integer.parseInt(arrStr[1]);
                    str = bufferedReader.readLine();
                } else if (arrStr[0].equals("buy")) {
                    sumBuy += Integer.parseInt(arrStr[1]);
                    str = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = sumSupply - sumBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + sumSupply);
            bufferedWriter.write(System.lineSeparator() + "buy," + sumBuy);
            bufferedWriter.write(System.lineSeparator() + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
