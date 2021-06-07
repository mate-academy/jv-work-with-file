package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int scoreSupply = 0;
        int scoreBuy = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String infoLine;
            while ((infoLine = bufferedReader.readLine()) != null) {
                String[] valuesLine = infoLine.split(",");
                if (valuesLine[0].equals("supply")) {
                    scoreSupply += Integer.valueOf(valuesLine[1]);
                } else {
                    scoreBuy += Integer.parseInt(valuesLine[1]);
                }
            }
            stringBuilder.append("supply,").append(scoreSupply).append(System.lineSeparator())
                    .append("buy,").append(scoreBuy).append(System.lineSeparator())
                    .append("result,").append(scoreSupply - scoreBuy);
            writeFile(toFileName,stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    public void writeFile(String file, String info) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
