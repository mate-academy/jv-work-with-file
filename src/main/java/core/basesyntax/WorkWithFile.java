package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int outputBuy = 0;
        int outputSupply = 0;
        File fileFrom = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom))) {
            String transaction = bufferedReader.readLine();
            while (transaction != null) {
                String[] trasactionInfoSplitted = transaction.split(",");
                if (trasactionInfoSplitted[0].equals("buy")) {
                    outputBuy = outputBuy + Integer.parseInt(trasactionInfoSplitted[1]);
                } else {
                    outputSupply = outputSupply + Integer.parseInt(trasactionInfoSplitted[1]);
                }
                transaction = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        sendStatisticToFile(outputBuy, outputSupply, toFileName);
    }

    private void sendStatisticToFile(int outputBuy, int outputSupply, String fileTo) {
        int result = outputSupply - outputBuy;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write("supply," + outputSupply + System.lineSeparator()
                    + "buy," + outputBuy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileTo, e);
        }
    }
}

