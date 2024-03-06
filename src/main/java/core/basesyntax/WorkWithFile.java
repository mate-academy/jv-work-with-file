package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SPLITTER = " ";
    private static final String COMMA_SPLITTER = ",";
    private static final int NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File file = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input = bufferedReader.readLine();
            while (input != null) {
                stringBuilder.append(input).append(DATA_SPLITTER);
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] dataInFile = stringBuilder.toString().split(DATA_SPLITTER);
        String[] names = new String[dataInFile.length];
        String[] number = new String[dataInFile.length];
        for (int i = 0;i < dataInFile.length;i++) {
            String[] curent = dataInFile[i].split(COMMA_SPLITTER);
            names[i] = curent[NAME_INDEX];
            number[i] = curent[COUNT_INDEX];
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
            writeToFile.append("supply").append(",").append(supplyCounter).append(SEPARATOR);
            writeToFile.append("buy").append(",").append(buyCounter).append(SEPARATOR);
            writeToFile.append("result").append(",")
                    .append(supplyCounter - buyCounter).append(SEPARATOR);
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
