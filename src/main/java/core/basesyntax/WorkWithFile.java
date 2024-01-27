package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {

        File fileRead = new File(fromFileName);

        int sumSupply = 0;
        int sumBuy = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileRead))) {

            StringBuilder stringBuilder = new StringBuilder();

            // Read from file
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = reader.readLine();
            }

            String[] splitBySpace = stringBuilder.toString().split(" ");

            for (int i = 0; i < splitBySpace.length; ++i) {
                String[] splitNameSum = splitBySpace[i].toString().split(",");
                if (splitNameSum[0].equals("supply")) {
                    sumSupply += Integer.parseInt(splitNameSum[1]);
                } else {
                    sumBuy += Integer.parseInt(splitNameSum[1]);
                }
            }

            String[] resultSupply = new String[]{"supply", String.valueOf(sumSupply)};
            String[] resultBuy = new String[]{"buy", String.valueOf(sumBuy)};
            String[] resultResult = new String[]{"result", String.valueOf(sumSupply - sumBuy)};

            // Write to file
            File writeFile = new File(toFileName);
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(writeFile))) {

                bufferedWriter.write(String.join(",", resultSupply));
                bufferedWriter.newLine();
                bufferedWriter.write(String.join(",", resultBuy));
                bufferedWriter.newLine();
                bufferedWriter.write(String.join(",", resultResult));
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }

    }
}
