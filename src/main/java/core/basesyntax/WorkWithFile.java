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
    private static final int SUPLLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String strInFile = readFile(fromFileName);
        String[] names = splitNames(strInFile);
        String[] number = splitNumber(strInFile);
        int[] report = report(names,number);
        writeReportToFile(report,toFileName);
    }

    public String[] splitNames(String input) {
        String[] dataInFile = input.split(DATA_SPLITTER);
        String[] names = new String[dataInFile.length];
        for (int i = 0;i < dataInFile.length;i++) {
            String[] curent = dataInFile[i].split(COMMA_SPLITTER);
            names[i] = curent[NAME_INDEX];
        }
        return names;
    }

    public String[] splitNumber(String input) {
        String[] dataInFile = input.split(DATA_SPLITTER);
        String[] number = new String[dataInFile.length];
        for (int i = 0;i < dataInFile.length;i++) {
            String[] current = dataInFile[i].split(COMMA_SPLITTER);
            number[i] = current[COUNT_INDEX];
        }
        return number;
    }

    public String writeInFile(int supplyCount, int buyCount) {
        StringBuilder writeToFile = new StringBuilder();
        writeToFile.append("supply").append(",").append(supplyCount).append(SEPARATOR);
        writeToFile.append("buy").append(",").append(buyCount).append(SEPARATOR);
        writeToFile.append("result").append(",")
                .append(supplyCount - buyCount).append(SEPARATOR);
        return writeToFile.toString();
    }

    public String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File file = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String input = bufferedReader.readLine();
            while (input != null) {
                stringBuilder.append(input).append(DATA_SPLITTER);
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return stringBuilder.toString();
    }

    public int[] report(String[] names, String[] number) {
        int[] reportCount = new int[2];
        for (int i = 0;i < names.length;i++) {
            if (names[i].equals("supply")) {
                reportCount[SUPLLY_INDEX] += Integer.parseInt(number[i]);
            } else {
                reportCount[BUY_INDEX] += Integer.parseInt(number[i]);
            }
        }
        return reportCount;
    }

    public void writeReportToFile(int[] report, String fileName) {
        File file1 = new File(fileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write(writeInFile(report[SUPLLY_INDEX],report[BUY_INDEX]));

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
