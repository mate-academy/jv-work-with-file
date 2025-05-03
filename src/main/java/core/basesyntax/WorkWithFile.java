package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeDataTofile(toFileName,getStat(readFromFile(fromFileName)));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder res = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                res.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldnt read from file " + fromFileName,e);
        }
        return res.toString();
    }

    private int[] getStat(String data) {
        String[] inputData = data.split(" ");
        int suply;
        int buy;
        int result;
        suply = buy = 0;
        for (String shopInf : inputData) {
            String[] shopInformation = shopInf.split(",");
            if (shopInformation[0].equals("buy")) {
                buy += Integer.parseInt(shopInformation[1]);

            } else {
                suply += Integer.parseInt(shopInformation[1]);
            }
        }
        result = suply - buy;
        return new int[]{suply, buy, result};
    }

    private void writeDataTofile(String toFileName,int [] statictic) {
        StringBuilder outputToFile = new StringBuilder();
        String out = outputToFile.append("supply,").append(statictic[0])
                .append(System.lineSeparator())
                .append("buy,").append(statictic[1]).append(System.lineSeparator())
                .append("result,").append(statictic[2]).append(System.lineSeparator()).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName,true))) {
            writer.write(out);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Couldnt write to file" + toFileName, e);
        }
    }
}

