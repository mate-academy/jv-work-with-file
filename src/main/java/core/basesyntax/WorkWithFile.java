package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(getCalculated(getFromFile(fromFileName)), toFileName);
    }

    private String getFromFile(String fromFileName) {
        File myFile = new File(fromFileName);
        StringBuilder dataFromFile = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
        return dataFromFile.toString();
    }

    private String getCalculated(String builder) {
        StringBuilder calculation = new StringBuilder();
        String[] splitData = builder.split(" ");
        String[] fieldsToCalc = new String[]{"supply", "buy"};
        int buy = 0;
        int supply = 0;
        for (String name : fieldsToCalc) {
            int sumAmount = 0;
            for (int i = 0; i < splitData.length; i++) {
                String[] temp = splitData[i].split(",");
                if (name.equals(temp[0])) {
                    sumAmount += Integer.parseInt(temp[1]);
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
        return calculation.toString();
    }

    private void writeToFile(String calculation, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(calculation);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
