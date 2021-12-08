package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String toArray = readFile(fromFileName);
        int[] result = getFiltering(toArray);
        StringBuilder toFile = new StringBuilder();
        toFile.append("supply,").append(result[1] + "\\n").append("buy,")
                .append(result[0] + "\\n").append("result,").append((result[1] - result[0]));
        writeFile(toFile, file);
    }

    private void writeFile(StringBuilder toFile, File file) {
        String st = new String(toFile);
        try (FileWriter inFile = new FileWriter(file, true)) {
            inFile.write(st);
        } catch (Exception e) {
            throw new RuntimeException("Not file");
        }
    }

    private int[] getFiltering(String toArray) {
        String[] buyArray = toArray.split("supply,|[Bb][A-z]+,\\d+");
        String[] supplyArray = toArray.split("buy,|[Ss][A-z]+,\\d+");
        int[] result = new int[2];
        int numBuy = 0;
        int numSupply = 0;
        int maxLength;
        maxLength = Math.max(buyArray.length, supplyArray.length);
        for (int i = 0; maxLength > i; i++) {
            if (buyArray.length != i + 1) {
                numBuy += Integer.parseInt(buyArray[i]);
            } else if (supplyArray.length != i + 1) {
                numSupply += Integer.parseInt(supplyArray[i]);
            }
        }
        result[0] = numBuy;
        result[1] = numSupply;
        return result;
    }

    private String readFile(String fromFileName) {
        String strToNull = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                builder.append((char) value);
                value = bufferedReader.read();
            }
            String toArray = new String(builder);
            strToNull = toArray;
            return toArray;
        } catch (Exception e) {
            System.out.println("not found File");
        }
        return strToNull;
    }
}
