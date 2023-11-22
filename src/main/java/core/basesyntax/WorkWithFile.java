package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File firstFile = new File(fromFileName);
        File secondFile = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int countSupply = 0;
        int countBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(firstFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                if (value == null) {
                    break;
                }
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("fff" + firstFile + e);
        }

        String[] array = stringBuilder.toString().split(",");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(secondFile))) {
            for (int i = 0; i < array.length; i++) {
                switch (array[i]) {
                    case "supply":
                        countSupply += Integer.parseInt(array[i + 1]);
                        break;
                    case "buy":
                        countBuy += Integer.parseInt(array[i + 1]);
                        break;
                    default:
                        break;

                }
            }
            bufferedWriter.write("supply," + countSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + countBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (countSupply - countBuy));
        } catch (IOException e) {
            throw new RuntimeException("dd" + secondFile + e);
        }
    }
}
