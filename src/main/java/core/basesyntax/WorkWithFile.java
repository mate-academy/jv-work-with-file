package core.basesyntax;

import java.io.*;

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
            throw new RuntimeException(e);
        }

        String output = builder.toString();t
        String[] lines = output.split(",|\\s+");
        String[][] results = new String[lines.length / 2][2];
        int firstItem = 0;
        int secondItem = 1;


        for (int lineCounter = 0; lineCounter < results.length; lineCounter++) {
            results[lineCounter][0] = lines[firstItem];
            results[lineCounter][1] = lines[secondItem];
            firstItem+=2;
            secondItem+=2;
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

        String[][] resultCSV = new String[identityCounter + 1][2];

        for (int i = 0; i < identityCounter; i++) {
            resultCSV[i][0] = temp[i];
        }
        for (int i = 0; i < identityCounter; i++) {
            int amount = 0;
            for (String[] strings : results) {
                if (strings[0].equals(temp[i])) {
                    amount += Integer.parseInt(strings[1]);
                }
            }
            resultCSV[i][1] = Integer.toString(amount);
        }

        int sum = 0;

        if (Integer.parseInt(resultCSV[0][1]) < Integer.parseInt(resultCSV[1][1])) {
            String[] temp2 = resultCSV[0];
            resultCSV[0] = resultCSV[1];
            resultCSV[1] = temp2;
        }

        sum = Integer.parseInt(resultCSV[0][1]) - Integer.parseInt(resultCSV[1][1]);
        resultCSV[resultCSV.length - 1][0] = "result";
        resultCSV[resultCSV.length - 1][1] =  Integer.toString(sum);

        StringBuilder builder1 = new StringBuilder();
        for (String[] strings : resultCSV) {
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
            throw new RuntimeException(e);
        }
    }
}
