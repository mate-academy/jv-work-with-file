package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = stringBuilder.toString();
        String [] array = result.split(System.lineSeparator());
        int sumOfSupply = 0;
        int sumOfBuy = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith("supply")) {
                sumOfSupply += Integer.parseInt(array[i].split(",")[1]);
            }
            if (array[i].startsWith("buy")) {
                sumOfBuy += Integer.parseInt(array[i].split(",")[1]);
            }
        }
        String result1 = "";
        result1 = result1.concat("supply,").concat(String.valueOf(sumOfSupply))
                  .concat(System.lineSeparator())
                .concat("buy,").concat(String.valueOf(sumOfBuy)).concat(System.lineSeparator())
               .concat("result,").concat(String.valueOf(sumOfSupply - sumOfBuy));
        File file1 = new File(toFileName);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("file cant be creaded",e);
        }
        try {
            Files.write(file1.toPath(), result1.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file",e);
        }
    }
}
