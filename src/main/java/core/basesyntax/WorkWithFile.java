package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File from = new File(fromFileName);
        File to = new File(toFileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(from));
            String line = bufferedReader.readLine();
            System.out.println(line);
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName);
        }
        String[] rows = stringBuilder.toString().split(System.lineSeparator());
        int supplyValue = 0;
        int buyValue = 0;
        for (String row : rows) {
            String[] values = row.split(",");
            if (values[0].equals("supply")) {
                supplyValue += Integer.parseInt(values[1]);
            } else if (values[0].equals("buy")) {
                buyValue += Integer.parseInt(values[1]);
            } else {
                throw new RuntimeException("Wrong name value " + values[0]);
            }
        }
        String report = "supply," + supplyValue + System.lineSeparator()
                + "buy," + buyValue + System.lineSeparator()
                + "result," + (supplyValue - buyValue);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to));
            for (String line : report.split(System.lineSeparator())) {
                bufferedWriter.write(line + System.lineSeparator());
                bufferedWriter.flush();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + to);
        }
    }
}
