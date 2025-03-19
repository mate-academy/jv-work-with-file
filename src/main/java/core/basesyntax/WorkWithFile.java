package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        String[] splitArray = builder.toString().split("\\W+");
        int sumOfSupply = 0;
        int sumOfBuy = 0;

        for (int i = 0; i < splitArray.length; i++) {
            if (splitArray[i].startsWith("s")) {
                sumOfSupply += Integer.parseInt(splitArray[i + 1]);
            } else if (splitArray[i].startsWith("b")) {
                sumOfBuy += Integer.parseInt(splitArray[i + 1]);
            }
        }

        int result = sumOfSupply - sumOfBuy;


        StringBuilder resultToFile = new StringBuilder();
        resultToFile.append("supply,").append(sumOfSupply).
                append(System.lineSeparator()).append("buy,").
                append(sumOfBuy).append(System.lineSeparator()).
                append("result,").append(result);

        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(resultToFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }

    }
}
