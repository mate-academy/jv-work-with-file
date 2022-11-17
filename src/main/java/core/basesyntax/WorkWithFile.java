package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        File file1 = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file1", e);
        }

        String fileString = stringBuilder.toString();
        String operationSupply = "supply";
        String operationBuy = "buy";
        int summaSupply = 0;
        int summaBuy = 0;
        int summaResult;

        String[] arrayFromFileName = fileString.split("\n");
        for (String s : arrayFromFileName) {
            if (s.contains(operationSupply)) {
                summaSupply += parseInt(s.split(",")[1]);
            } else {
                summaBuy += parseInt(s.split(",")[1]);
            }
        }
        summaResult = summaSupply - summaBuy;
        String mainString = operationSupply
                + "," + summaSupply
                + "\n" + operationBuy + "," + summaBuy
                + "\n" + "result" + "," + summaResult;

        File file2 = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file2, true));
            bufferedWriter.write(mainString);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file ", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }
}

