package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int[] supply = new int[10];
    private int sumSupply = 0;
    private int counterSupply = 0;
    private int[] buy = new int[10];
    private int sumBuy = 0;
    private int counterBuy = 0;
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        for (int i = 0; i < supply.length; i++) {
            sumSupply += supply[i];
            sumBuy += buy[i];
        }
        writeToFile(toFileName);
    }

    public void writeToFile(String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file", e);
        }
        try {
            stringBuilder.delete(0, stringBuilder.length());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(String.valueOf(stringBuilder
                    .append("supply,").append(sumSupply)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.write(String.valueOf(stringBuilder
                    .append("\r\nbuy,").append(sumBuy)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.write(String.valueOf(stringBuilder
                    .append("\r\nresult,").append(sumSupply - sumBuy)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.close();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Can`t write to file, e");
        }
    }

    public void readFromFile(String fromFileName) {
        try {
            File file = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String workingString = bufferedReader.readLine();
            while (workingString != null) {
                if (workingString.substring(0, workingString.indexOf(',')).equals("supply")) {
                    supply[counterSupply] = Integer.parseInt(
                            workingString.substring(workingString.indexOf(',') + 1));
                    counterSupply++;
                } else {
                    buy[counterBuy] = Integer.parseInt(
                            workingString.substring(workingString.indexOf(',') + 1));
                    counterBuy++;
                }
                workingString = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }
    }
}
