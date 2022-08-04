package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
   public final static int ZERO_VALUE=0;
   public final static int SECOND_POSITION=1;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        doNeedForm(fromFileName);
        writeToFilee(fromFileName,toFileName);
    }
    public String readFromFile(String adres) {
        File file = new File(adres);
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
        return result;
    }

    public String doNeedForm(String adres) {

        String[] array = readFromFile(adres).split(System.lineSeparator());
        int sumOfSupply = ZERO_VALUE;
        int sumOfBuy = ZERO_VALUE;
        for (int i = ZERO_VALUE; i < array.length; i++) {
            if (array[i].startsWith("supply")) {
                sumOfSupply += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
            if (array[i].startsWith("buy")) {
                sumOfBuy += Integer.parseInt(array[i].split(",")[SECOND_POSITION]);
            }
        }
        String result1 = "";
        result1 = result1.concat("supply,").concat(String.valueOf(sumOfSupply))
                .concat(System.lineSeparator())
                .concat("buy,").concat(String.valueOf(sumOfBuy)).concat(System.lineSeparator())
                .concat("result,").concat(String.valueOf(sumOfSupply - sumOfBuy));
        return result1;
    }

            public void writeToFilee(String from,String to ){
            File file1 = new File(to);
            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("file cant be creaded",e);
            }
            try {
                Files.write(file1.toPath(), doNeedForm(from).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Can not write data to file",e);
            }
         }
    }


