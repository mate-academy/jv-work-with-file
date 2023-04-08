package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String result = stringResultFromCsv(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }

    }

    private String stringResultFromCsv(String fromFileName) {
        int sumOfsupply = 0;
        int sumOfBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fromFileName);
        try {
            List<String> list = Files.readAllLines(file.toPath());
            for (String line : list) {
                String[] lineSplit = line.split(",");
                if (lineSplit[0].equals("supply")) {
                    sumOfsupply += Integer.parseInt(lineSplit[1]);
                } else {
                    sumOfBuy += Integer.parseInt(lineSplit[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Сan't read file", e);
        }
        return stringBuilder.append("supply,").append(sumOfsupply).append(System.lineSeparator())
            .append("buy,").append(sumOfBuy).append(System.lineSeparator())
            .append("result,").append(sumOfsupply - sumOfBuy).toString();
    }
}
