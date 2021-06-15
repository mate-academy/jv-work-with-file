package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fileRead = new File(fromFileName);

        try {
            String line;
            StringBuilder suply = new StringBuilder();
            StringBuilder buy = new StringBuilder();
            String[] numberSuply = new String[0];
            String[] numberBuy = new String[0];
            int valueSuply = 0;
            int valueBuy = 0;
            final int result;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRead));
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");

                for (String word : words) {
                    if (word.startsWith("s")) {
                        suply.append(word);
                    }
                    if (word.startsWith("b")) {
                        buy.append(word);
                    }
                }
                String supplyString = suply.toString();
                String buyString = buy.toString();
                numberSuply = supplyString.split("supply,");
                numberBuy = buyString.split("buy,");
            }
            for (int i = 1; i < numberSuply.length; i++) {
                valueSuply += Integer.parseInt(numberSuply[i]);
            }
            String s = "supply," + valueSuply;
            for (int i = 1; i < numberBuy.length; i++) {
                valueBuy += Integer.parseInt(numberBuy[i]);
            }
            String s1 = "buy," + valueBuy;
            result = (Math.abs(valueSuply - valueBuy));
            String res = "result," + result;

            String[] report = new String[3];
            report[0] = s;
            report[1] = s1;
            report[2] = res;
            File fileWrite = new File(toFileName);
            String rep = new String();
            for (int i = 0; i < report.length; i++) {
                rep += report[i] + "\r\n";
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite));
                bufferedWriter.write(rep);
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file");
        }
    }
}
