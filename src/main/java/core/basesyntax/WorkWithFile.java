package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    //final String Splitter_Symbol = ",";
    public void getStatistic(String fromFileName, String toFileName) {
        String[][] result = {{"supply", ""},{"buy", ""},{"result", ""}};
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int supplySum = 0;
        int buySum = 0;

        for (List<String> s : records) {
            if (s.get(0).equals(result[0][0])) {
                supplySum += Integer.parseInt(s.get(1));
            } else if (s.get(0).equals(result[1][0])) {
                buySum += Integer.parseInt(s.get(1));
            }
        }

        result[0][1] = Integer.toString(supplySum);
        result[1][1] = Integer.toString(buySum);
        result[2][1] = Integer.toString(supplySum - buySum);

        try {
            FileWriter fileWriter = new FileWriter(toFileName, false);
            for (String[] s : result) {
                String tempStr = new String(s[0] + "," + s[1] + System.lineSeparator());
                fileWriter.append(tempStr);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
    }
}
