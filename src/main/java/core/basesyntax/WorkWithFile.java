package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder res = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                res.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException k) {
            throw new RuntimeException("Couldnt read from file",k);
        }
        String data = res.toString();
        String [] inputData = data.split(" ");
        int suply;
        int buy;
        int result;
        suply = buy =  0;
        for (String shopInf :inputData) {
            String [] shopInformation = shopInf.split(",");
            if (shopInformation[0].equals("buy")) {
                buy += Integer.parseInt(shopInformation[1]);

            } else {
                suply += Integer.parseInt(shopInformation[1]);
            }
        }
        result = suply - buy;
        StringBuilder outputToFile = new StringBuilder();
        String out = outputToFile.append("supply,").append(suply).append(System.lineSeparator())
                        .append("buy,").append(buy).append(System.lineSeparator())
                        .append("result,").append(result).append(System.lineSeparator()).toString();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName,true));
            writer.write(out);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException("Couldnt write to file", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Couldnt close file",e);
                }
            }
        }
    }
}

