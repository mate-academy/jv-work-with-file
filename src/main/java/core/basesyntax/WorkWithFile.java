package core.basesyntax;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + toFileName, e);
        }

        String output = builder.toString();
        String[] lines = output.split(",|\\s+");

        if (lines.length % 2 != 0) {
            throw new IllegalArgumentException("Need an even number of lines");
        }

        String[][] results = new String[lines.length / 2][2];
        int firstItem = 0;
        int secondItem = 1;

        for (int lineCounter = 0; lineCounter < results.length; lineCounter++) {
            results[lineCounter][0] = lines[firstItem];
            results[lineCounter][1] = lines[secondItem];
            firstItem += 2;
            secondItem += 2;
        }

        String[] temp = new String[results.length];
        int identityCounter = 0;

        for (String[] result : results) {
            boolean isIdentity = false;
            for (int j = 0; j < identityCounter; j++) {
                isIdentity = result[0].equals(temp[j]);
                if (isIdentity) {
                    break;
                }
            }
            if (!isIdentity) {
                temp[identityCounter] = result[0];
                identityCounter++;
            }
        }

        String[][] resultCsv = new String[identityCounter + 1][2];

        for (int i = 0; i < identityCounter; i++) {
            resultCsv[i][0] = temp[i];
        }
        for (int i = 0; i < identityCounter; i++) {
            int amount = 0;
            for (String[] strings : results) {
                if (strings[0].equals(temp[i])) {
                    amount += Integer.parseInt(strings[1]);
                }
            }
            resultCsv[i][1] = Integer.toString(amount);
        }

        if (resultCsv.length > 1) {
            int sum = 0;

            if (Integer.parseInt(resultCsv[0][1]) < Integer.parseInt(resultCsv[1][1])) {
                String[] temp2 = resultCsv[0];
                resultCsv[0] = resultCsv[1];
                resultCsv[1] = temp2;
            }

            sum = Integer.parseInt(resultCsv[0][1]) - Integer.parseInt(resultCsv[1][1]);
            resultCsv[resultCsv.length - 1][0] = "result";
            resultCsv[resultCsv.length - 1][1] = Integer.toString(sum);
        }

        StringBuilder builder1 = new StringBuilder();
        for (String[] strings : resultCsv) {
            builder1.append(strings[0]);
            builder1.append(",");
            builder1.append(strings[1]);
            builder1.append(System.lineSeparator());
        }

        String textResult = builder1.toString();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(textResult);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName,e);
        }
    }
}
