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
        final String buy = "buy";
        final String supply = "supply";
        String[] lineSplitted;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            int supplyInt = 0;
            int buyInt = 0;
            int result = 0;
            while (value != null) {
                String[] splittedArr = value.split(",");
                if (splittedArr[0].equals(supply)) {
                    supplyInt += Integer.parseInt(splittedArr[1]);
                } else if (splittedArr[0].equals(buy)) {
                    buyInt += Integer.parseInt(splittedArr[1]);
                }
                value = bufferedReader.readLine();
                if (value == null) {
                    break;
                }
            }
            result = supplyInt - buyInt;
            builder.append(supply + ",")
                    .append(supplyInt)
                    .append(System.lineSeparator())
                    .append(buy + ",")
                    .append(buyInt)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(result);
        } catch (IOException e) {
            throw new RuntimeException("file not found", e);
        }
        lineSplitted = builder.toString().split(System.lineSeparator());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String s : lineSplitted) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
