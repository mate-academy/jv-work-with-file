package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File myFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        StringBuilder calculation = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
        String[] split1 = builder.toString().split(" ");
        String[] firstE = new String[] {"supply", "buy"};
        int buy = 0;
        int supply = 0;
        for (String name : firstE) {
            int sumAmount = 0;
            for (int i = 0; i < split1.length; i++) {
                String[] tmp = split1[i].split(",");
                if (name.equals(tmp[0])) {
                    sumAmount += Integer.parseInt(tmp[1]);
                }
            }
            if (name.equals("buy")) {
                buy = sumAmount;
            } else {
                supply = sumAmount;
            }
            calculation.append(name).append(",").append(sumAmount).append(System.lineSeparator());
        }
        calculation.append("result").append(",").append(supply - buy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculation.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
