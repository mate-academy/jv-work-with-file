package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {

        // read fromFileName - metod Files.readAllLines()
        File file = new File(fromFileName);
        List<String> listFromFileName;
        try {
            listFromFileName = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String readString = listFromFileName.toString();
        String stringFromFileName = readString.substring(1, readString.length() - 1);

        // calculation
        int totalSupply = 0;
        int totalBuy = 0;
        String[] splitOneStringResult = stringFromFileName.split(" ");
        for (String splitOneStrRes : splitOneStringResult) {
            String[] splitTwoStrRes = splitOneStrRes.split(",");
            if (splitTwoStrRes[0].equals("supply")) {
                totalSupply += Integer.parseInt(splitTwoStrRes[1]);
            }
            if (splitTwoStrRes[0].equals("buy")) {
                totalBuy += Integer.parseInt(splitTwoStrRes[1]);
            }
        }

        // Write data to file
        StringBuilder stringBuilder = new StringBuilder().append("supply,").append(totalSupply)
                .append(System.lineSeparator()).append("buy,").append(totalBuy)
                .append(System.lineSeparator()).append("result,").append(totalSupply - totalBuy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
