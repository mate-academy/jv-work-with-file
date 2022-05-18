package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int IDENTIFICATION_DATE = 0;
    public static final int CASH_INFO = 1;
    public static final int SUPPLY = 0;
    public static final int BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int[] info = readInfo(file);
        String report = report(info);
        file = new File(toFileName);
        writeToFile(file, report);
    }

    private int[] readInfo(File file) {
        int[] info = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readString = bufferedReader.readLine();
            while (readString != null) {
                String[] dataArray = readString.split(",");
                if (dataArray[IDENTIFICATION_DATE].equals("supply")) {
                    info[SUPPLY] += Integer.parseInt(dataArray[CASH_INFO]);
                } else {
                    info[BUY] += Integer.parseInt(dataArray[CASH_INFO]);
                }
                readString = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found ", e);
        } catch (IOException e) {
            throw new RuntimeException("Cant read file ", e);
        }
        return info;
    }

    private String report(int[] info) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,")
                .append(info[SUPPLY])
                .append(System.lineSeparator())
                .append("buy,")
                .append(info[BUY])
                .append(System.lineSeparator())
                .append("result,")
                .append(info[SUPPLY] - info[BUY]);
        return builder.toString();
    }

    private void writeToFile(File file, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write file ", e);
        }
    }
}
