package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    public static void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can not find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file", e);
        }
        String[] texts = builder.toString().split(",");
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        int result = 0;
        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equals("supply")) {
                sumOfSupply += Integer.parseInt(texts[i + 1]);
            }
            if (texts[i].equals("buy")) {
                sumOfBuy += Integer.parseInt(texts[i + 1]);
            }
            result = sumOfSupply - sumOfBuy;
        }

        File fileChanged = new File(toFileName);
        String[] array = new String[3];
        array[0] = SUPPLY + sumOfSupply;
        array[1] = BUY + sumOfBuy;
        array[2] = RESULT + result;
        for (String element : array) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileChanged,
                    true))) {
                bufferedWriter.write(element + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can not find the file", e);
            }
        }
    }
}
