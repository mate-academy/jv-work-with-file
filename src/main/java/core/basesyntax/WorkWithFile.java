package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        File file;
        StringBuilder stringBuilder;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            file = new File(toFileName);
            file.createNewFile();
            stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String text = stringBuilder.toString()
                    .replace(System.getProperty("line.separator"), ",");
            String[] sourceFile = text.split(",");

            StringBuilder builderCountData = new StringBuilder();
            for (int i = 0; i < sourceFile.length; i += 2) {
                if (sourceFile[i].equals("supply")) {
                    sumSupply += Integer.parseInt(sourceFile[i + 1]);
                } else if (sourceFile[i].equals("buy")) {
                    sumBuy += Integer.parseInt(sourceFile[i + 1]);
                }
            }
            int result = sumSupply - sumBuy;
            builderCountData.append("supply,").append(sumSupply).append(System.lineSeparator())
                    .append("buy,").append(sumBuy).append(System.lineSeparator())
                    .append("result,").append(result);

            Files.write(file.toPath(), builderCountData.toString()
                    .getBytes(), StandardOpenOption.APPEND);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
