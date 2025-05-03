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
        toFile.append("supply,").append(result[1] + System.lineSeparator())
                .append("buy,").append(result[0] + System.lineSeparator())
                .append("result,").append((result[1] - result[0]));
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
        String[] dataArray = toArray.split(" ");
        int[] result = new int[2];
        int numBuy = 0;
        int numSupply = 0;
        for (int i = 0; dataArray.length > i; i++) {
            if (dataArray[i].contains("buy,")) {
                dataArray[i] = dataArray[i].replaceAll("\\D", "");
                numBuy += Integer.parseInt(dataArray[i]);

            } else if (dataArray[i].contains("supply,")) {
                dataArray[i] = dataArray[i].replaceAll("\\D", "");
                numSupply += Integer.parseInt(dataArray[i]);

            }
        }
        result[0] = numBuy;
        result[1] = numSupply;
        return result;
    }

    private String readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value.replaceAll("\\s+", "")).append(" ");
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException("not found File");
        }
    }
}
