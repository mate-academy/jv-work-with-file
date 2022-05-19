package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int IDENTIFICATION_DATE_INDEX = 0;
    public static final int CASH_INFO_INDEX = 1;
    public static final int SUPPLY_INDEX = 0;
    public static final int BUY_INDEX = 1;
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int[] info = readInfo(file);
        String report = makeReport(info);
        file = new File(toFileName);
        writeToFile(file, report);
    }

    private int[] readInfo(File file) {
        int[] info = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readString = bufferedReader.readLine();
            while (readString != null) {
                String[] dataArray = readString.split(DELIMITER);
                if (dataArray[IDENTIFICATION_DATE_INDEX].equals("supply")) {
                    info[SUPPLY_INDEX] += Integer.parseInt(dataArray[CASH_INFO_INDEX]);
                } else {
                    info[BUY_INDEX] += Integer.parseInt(dataArray[CASH_INFO_INDEX]);
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

    private String makeReport(int[] info) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,")
                .append(info[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,")
                .append(info[BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,")
                .append(info[SUPPLY_INDEX] - info[BUY_INDEX]);
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
