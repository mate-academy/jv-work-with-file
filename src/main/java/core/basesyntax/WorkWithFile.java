package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String sup = "supply";
    private final String buy = "buy";
    private final String res = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int addSupply = 0;
        int addBay = 0;
        int result = 0;

        File fileIn = new File(fromFileName);
        File fileOut = new File(toFileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileIn))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                if (split[0].equals(sup)) {
                    addSupply += Integer.parseInt(split[1]);
                } else if (split[0].equals(buy)) {
                    addBay += Integer.parseInt(split[1]);
                }
                value = bufferedReader.readLine();
            }
            result = addSupply - addBay;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOut, true))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(sup).append(",").append(addSupply).append(System.lineSeparator())
                            .append(buy).append(",").append(addBay).append(System.lineSeparator())
                            .append(res).append(",").append(result);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
