package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(fromFileName, toFileName);
    }

    public void writeFile(String fromFileName, String toFileName) {
        StringBuilder result = readFile(fromFileName);
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file" + toFileName, e);
        }
    }

    public StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfPurchases = 0;
        int numberOfSupply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fileStr;
            while ((fileStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(fileStr).append(COMMA);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
        String[] arrayFromFileName = stringBuilder.toString().split(",");
        for (int i = 0; i < arrayFromFileName.length; i++) {
            if (arrayFromFileName[i].equalsIgnoreCase(SUPPLY)) {
                numberOfSupply += Integer.parseInt(arrayFromFileName[i + 1]);
            }
            if (arrayFromFileName[i].equalsIgnoreCase(BUY)) {
                numberOfPurchases += Integer.parseInt(arrayFromFileName[i + 1]);
            }
        }
        stringBuilder.setLength(0);
        return stringBuilder.append("supply,")
                .append(numberOfSupply)
                .append(System.lineSeparator())
                .append("buy,").append(numberOfPurchases)
                .append(System.lineSeparator())
                .append("result,").append(numberOfSupply - numberOfPurchases);
    }
}
