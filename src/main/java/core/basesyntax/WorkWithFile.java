package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] innerArray;
        String stringSupply = "supply";
        String stringBuy = "buy";
        int intSupply = 0;
        int intBuy = 0;
        int result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String innerFile = bufferedReader.readLine();
            while (innerFile != null) {
                innerArray = innerFile.split(",");
                if (innerArray[0].equals(stringSupply)) {
                    intSupply += Integer.parseInt(innerArray[1]);
                }
                if (innerArray[0].equals(stringBuy)) {
                    intBuy += Integer.parseInt(innerArray[1]);
                }
                innerFile = bufferedReader.readLine();
            }
            result = intSupply - intBuy;
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringSupply + "," + intSupply);
            bufferedWriter.newLine();
            bufferedWriter.write(stringBuy + "," + intBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result" + "," + result);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
