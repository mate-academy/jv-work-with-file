package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String readFromFileName = stringBuilder.toString();
        String[] readFromFileNameArray = readFromFileName.split(System.lineSeparator());
        for (String nameAndValue : readFromFileNameArray) {
            String[] nameAndValueSplit = nameAndValue.split(",");
            if (nameAndValueSplit[0].equals("supply")) {
                supplyAmount += Integer.parseInt(nameAndValueSplit[1]);
            }
            if (nameAndValueSplit[0].equals("buy")) {
                buyAmount += Integer.parseInt(nameAndValueSplit[1]);
            }
        }
        int result = supplyAmount - buyAmount;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                    .append("buy,").append(buyAmount).append(System.lineSeparator())
                    .append("result,").append(result);
            bufferedWriter.write(builder.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
