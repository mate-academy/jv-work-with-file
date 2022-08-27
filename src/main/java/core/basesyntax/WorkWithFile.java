package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int totalSupply = 0;
    private int totalBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    public void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                if (split[0].equals("supply")) {
                    totalSupply += Integer.parseInt(split[1]);
                } else {
                    totalBuy += Integer.parseInt(split[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t open file",e);
        } catch (IOException e) {
            throw new RuntimeException("Runtime Exception", e);
        }
    }

    public void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + totalSupply + System.lineSeparator() + "buy,"
                    + totalBuy + System.lineSeparator() + "result," + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Runtime Exception", e);
        }
    }
}
