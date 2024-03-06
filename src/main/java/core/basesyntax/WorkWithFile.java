package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        StringBuilder stringBuilder = new StringBuilder();
        final int NameIndex = 0;
        final int CountIndex = 1;
        final String Separator = System.lineSeparator();

        try {
            File file = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input = bufferedReader.readLine();
            while (input != null) {
                stringBuilder.append(input).append(" ");
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        String[] dataInFile = stringBuilder.toString().split(" ");
        String[] names = new String[dataInFile.length];
        String[] number = new String[dataInFile.length];
        for (int i = 0;i < dataInFile.length;i++) {
            String[] curent = dataInFile[i].split(",");
            names[i] = curent[NameIndex];
            number[i] = curent[CountIndex];
        }
        StringBuilder writeToFile = new StringBuilder();
        int supplyCounter = 0;
        int buyCounter = 0;
        File file1 = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            for (int i = 0;i < names.length;i++) {
                if (names[i].equals("supply")) {
                    supplyCounter += Integer.parseInt(number[i]);
                } else {
                    buyCounter += Integer.parseInt(number[i]);
                }
            }
            writeToFile.append("supply").append(",").append(supplyCounter).append(Separator);
            writeToFile.append("buy").append(",").append(buyCounter).append(Separator);
            writeToFile.append("result").append(",")
                    .append(supplyCounter - buyCounter).append(Separator);
            bufferedWriter.write(writeToFile.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file",e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't close file",e);
            }
        }
    }
}
