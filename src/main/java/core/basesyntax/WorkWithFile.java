package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] allInputDataOnArray;
        StringBuilder stringBuilder = new StringBuilder();
        String resultInfo;
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            allInputDataOnArray = stringBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        for (String i: allInputDataOnArray) {
            if (i.startsWith("supply")) {
                supply += Integer.parseInt(i.replace("supply,",""));
            } else {
                buy += Integer.parseInt(i.replace("buy,",""));
            }
        }

        resultInfo = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,true))) {
            bufferedWriter.write(resultInfo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
