package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CHECK_SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String[] calculatedStatistic = calculateStatistic(dataFromFile);
        writeFile(calculatedStatistic, toFileName);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString().split(" ");
    }

    private void writeFile(String[] calculatedStatistic, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String data : calculatedStatistic) {
                bufferedWriter.write(data);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }

    }

    private String[] calculateStatistic(String[] dataFromFile) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        int ammount = 0;
        for (String line : dataFromFile) {
            String[] lineSeparation = line.split(",");
            ammount = Integer.parseInt(lineSeparation[1]);
            if (lineSeparation[0].equals(CHECK_SUPPLY)) {
                sumOfSupply += ammount;
            } else {
                sumOfBuy += ammount;
            }
        }
        int result = sumOfSupply - sumOfBuy;
        return new String[] {"supply," + sumOfSupply, "buy," + sumOfBuy, "result," + result};
    }
}
