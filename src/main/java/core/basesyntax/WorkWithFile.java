package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] supply = new int[10];
        int sumSupply = 0;
        int counterSupply = 0;
        int[] buy = new int[10];
        int sumBuy = 0;
        int counterBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File file = new File(fromFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String workingString = bufferedReader.readLine();
            while (workingString != null) {
                if (workingString.substring(0, workingString.indexOf(',')).equals("supply")) {
                    int i = 0;
                    supply[counterSupply] = Integer.parseInt(workingString.substring(workingString.indexOf(',') + 1));
                    counterSupply++;
                } else {
                    int i = 0;
                    buy[counterBuy] = Integer.parseInt(workingString.substring(workingString.indexOf(',') + 1));
                    counterBuy++;
                }
                workingString = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }

        for (int i = 0; i < supply.length; i++) {
            sumSupply += supply[i];
            sumBuy += buy[i];
        }

        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create new file", e);
        }
        try {
            stringBuilder.delete(0, stringBuilder.length());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(String.valueOf(stringBuilder.append("supply,").append(sumSupply)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.write(String.valueOf(stringBuilder.append("\r\nbuy,").append(sumBuy)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.write(String.valueOf(stringBuilder.append("\r\nresult,").append(sumSupply - sumBuy)));
            stringBuilder.delete(0, stringBuilder.length());
            bufferedWriter.close();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Can`t write to file, e");
        }
    }

    public void writeToFile() {

    }

    public void readFromFile() {

    }
}
