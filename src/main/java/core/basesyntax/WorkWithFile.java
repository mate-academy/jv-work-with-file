package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] record;
        String recordString = "";
        int supply = 0;
        int buy = 0;
        File inputData = new File(fromFileName);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Problem", e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            recordString = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
        while (recordString != null) {
            record = recordString.split(",");
            if (record[0].equals("supply")) {
                supply += Integer.parseInt(record[1]);
            } else {
                buy += Integer.parseInt(record[1]);
            }
            try {
                recordString = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Problem", e);
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
        File outputData = new File(toFileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputData);
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(new StringBuilder("supply,")
                    .append(String.valueOf(supply))
                    .append(System.lineSeparator())
                    .append("buy,")
                    .append(String.valueOf(buy))
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(String.valueOf(supply - buy))
                    .toString());
        } catch (IOException e) {
            throw new RuntimeException("Problem", e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException("Problem", e);
            }
        }
    }
}
