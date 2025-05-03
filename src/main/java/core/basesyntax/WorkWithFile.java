package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] parts = readFromFile(fromFileName).split(" ");
        int sumBuy = getSumBuy(parts);
        int sumSupply = getSumSupply(parts);
        int difference = getDifference(sumBuy, sumSupply);
        String[] resultArray = getResultArray(sumSupply, sumBuy, difference);
        getWrite(resultArray, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder dataFromFile = new StringBuilder();
            String elementFromData = bufferedReader.readLine();
            while (elementFromData != null) {
                dataFromFile.append(elementFromData).append(" ");
                elementFromData = bufferedReader.readLine();
            }
            return dataFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private int getSumBuy(String[] parts) {
        int sumBuy = 0;
        for (int i = 0; i < parts.length; i++) {
            String[] miniPart = parts[i].split(",");
            if (miniPart[0].startsWith("buy")) {
                sumBuy = sumBuy + Integer.parseInt(miniPart[1]);
            }
        }
        return sumBuy;
    }

    private int getSumSupply(String[] parts) {
        int sumSupply = 0;
        for (int i = 0; i < parts.length; i++) {
            String[] miniPart = parts[i].split(",");
            if (miniPart[0].startsWith("s")) {
                sumSupply = sumSupply + Integer.parseInt(miniPart[1]);
            }
        }
        return sumSupply;
    }

    private int getDifference(int sumBuy, int sumSupply) {
        return Math.abs(sumBuy - sumSupply);
    }

    private void getWrite(String[] resultArray, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (int i = 0; i < resultArray.length; i++) {
                bufferedWriter.write(resultArray[i]);
                bufferedWriter.newLine();
            }
        } catch (IOException r) {
            throw new RuntimeException("Can't write to file", r);
        }
    }

    private String[] getResultArray(int sumSupply,
                                 int sumBuy, int difference) {
        String[] array = new String[3];
        if (sumBuy > sumSupply) {
            array[0] = "buy," + sumBuy;
            array[1] = "supply," + sumSupply;
            array[2] = "result," + difference;
            return array;
        } else {
            array[0] = "supply," + sumSupply;
            array[1] = "buy," + sumBuy;
            array[2] = "result," + difference;
            return array;
        }
    }
}

