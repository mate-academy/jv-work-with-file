package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String[] result;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                int value = bufferedReader.read();
                while (value != -1) {
                    stringBuilder.append((char) value);
                    value = bufferedReader.read();
                }
                result = stringBuilder.toString().split("\\W+");

            } catch (IOException e) {
                throw new RuntimeException("can't read to the file" + e);

            }
        } catch (FileNotFoundException e) {
            System.out.println("can't find the file" + e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getStatisticHelper(result));
        } catch (IOException e) {
            throw new RuntimeException("can't write date to file" + e);
        }

    }

    public String getStatisticHelper(String[] date) {
        int supply = 0;
        int buy = 0;
        boolean booSupply = false;
        boolean booBuy = false;
        for (String string : date) {
            switch (string) {
                case "supply" :
                    booSupply = true;
                    break;
                case "buy" :
                    booBuy = true;
                    break;
                default:
                    if (booSupply == true) {
                        supply += Integer.parseInt(string);
                        booSupply = false;
                    } else if (booBuy == true) {
                        buy += Integer.parseInt(string);
                        booBuy = false;
                    }
            }
        }
        String resultString = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
        System.out.println(resultString);
        return resultString;
    }
}
