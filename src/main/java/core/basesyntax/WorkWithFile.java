package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] split = builder.toString().replaceAll("(\r\n|\n)", " ").split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int supplyInt = 0;
        int buyInt = 0;
        String buyConstant = "buy";
        String supplyConstant = "supply";
        for (String strings: split) {
            int index = strings.indexOf(',');
            int length = strings.length();
            String count = strings.substring(index + 1, length);
            if (strings.substring(0,index).equals(supplyConstant)) {
                supplyInt += Integer.parseInt(count);
            }
            if (strings.substring(0,index).equals(buyConstant)) {
                buyInt += Integer.parseInt(count);
            }
        }
        String resultTotal = "result";
        stringBuilder.append(supplyConstant).append(",").append(supplyInt).append("\n");
        stringBuilder.append(buyConstant).append(",").append(buyInt).append("\n");
        stringBuilder.append(resultTotal).append(",").append((supplyInt - buyInt)).append("\n");
        File fileTo = new File(toFileName);
        for (char chars: stringBuilder.toString().toCharArray()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo, true))) {
                writer.write(chars);
            } catch (IOException e) {
                throw new RuntimeException("Can't write file " + toFileName, e);
            }
        }
    }
}
