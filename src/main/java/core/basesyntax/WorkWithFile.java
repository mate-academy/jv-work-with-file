package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int FIELDS = 2;
    private HashMap<String, Integer> stat;
    
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            stat = new HashMap<>();
            readFromCsv(fromFileName);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + "Problem with input from CSV file");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e.getMessage() + "Numeric parse exception");
        }
        try {
            writeToCsv(toFileName);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + "Problem with output to CSV file");
        }
    }

    private void readFromCsv(String fromFileName) throws IOException, NumberFormatException {
        BufferedReader bri = new BufferedReader(new FileReader(fromFileName));
        while (true) {
            String line = bri.readLine();
            if (line == null) {
                break;
            }
            String[] em = line.split(DELIMITER);
            if (em.length == FIELDS) {
                var tmp1 = em[0].trim();
                var tmp2 = em[1].trim();
                if (stat.containsKey(tmp1)) {
                    stat.replace(tmp1, stat.get(tmp1) + Integer.parseInt(tmp2));
                } else {
                    stat.put(tmp1, Integer.parseInt(tmp2));
                }
            }
        }
        bri.close();
    }

    private void writeToCsv(String toFileName) throws IOException {
        BufferedWriter bro = new BufferedWriter(new FileWriter(toFileName));
        if (!stat.isEmpty()) {
            int dif = 0;
            int i = 0;
            StringBuilder tmp = new StringBuilder();
            for (String key : stat.keySet()) {
                tmp.insert(0, String.format("%s,%d\n", key, stat.get(key)));
                if (i == 0) {
                    dif = stat.get(key);
                    i++;
                } else {
                    dif -= stat.get(key);
                }
            }
            bro.write(tmp.toString());
            bro.write(String.format("result,%d\n", Math.abs(dif)));
            bro.close();
        }
    }
}
