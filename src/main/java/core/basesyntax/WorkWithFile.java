package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFileName = getInfoFromFileName(fromFileName);
        String reportSupplyAndBuy = getInfoSupplyAndBuy(infoFromFileName);
        writeToFile(toFileName, reportSupplyAndBuy);
    }

    public String[] getInfoFromFileName(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            if (value == null) {
                return new String[0];
            }
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String sentence = stringBuilder.toString();
        return sentence.split(System.lineSeparator());
    }

    public String getInfoSupplyAndBuy(String[] split) {
        int index;
        int sumSupply = 0;
        int sumBuy = 0;
        StringBuilder builder = new StringBuilder();
        for (String word : split) {
            index = word.indexOf(",");
            if (word.substring(0, index).equals("supply")) {
                sumSupply += Integer.parseInt(word.substring(index + 1));
            } else {
                sumBuy += Integer.parseInt(word.substring(index + 1));
            }
        }
        builder.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy);
        return builder.toString();
    }

    public void writeToFile(String toFileName, String recordingInfo) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(recordingInfo);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
