package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName,
                true))) {
            for (String res : arraysResult(readFile(fromFileName))) {
                bufferedWriter.write(res + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't reader file", e);
        }
    }

    private String readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(",");
                value = bufferedReader.readLine();
            }
            String stringBuilder = builder.toString().replace("[^A-Za-z0-9]", " ");
            return stringBuilder;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't reader file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't reader file", e);
        }
    }

    private String[] arraysResult(String stringBuilder) {
        String[] arrayFromFileName = stringBuilder.split(",");
        int supplyTotal = 0;
        int buyTotal = 0;
        int resultTotal;
        for (int i = 0; i < arrayFromFileName.length; i++) {
            if (arrayFromFileName[i].equals("supply")) {
                supplyTotal += Integer.parseInt(arrayFromFileName[i + 1]);
            } else if (arrayFromFileName[i].equals("buy")) {
                buyTotal += Integer.parseInt(arrayFromFileName[i + 1]);
            }
        }
        resultTotal = supplyTotal - buyTotal;
        String[] results = {"supply," + supplyTotal, "buy," + buyTotal,
                "result," + resultTotal};
        return results;
    }
}
