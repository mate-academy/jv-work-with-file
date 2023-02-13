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
    private String[] key = new String[FIELDS];
    private Integer[] value = new Integer[FIELDS];
    private int pos = 0;
    
    public void getStatistic(String fromFileName, String toFileName) {
        try {
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
                var ind = containsKey(tmp1);
                if (ind == -1) {
                    key[pos] = tmp1;
                    value[pos] = Integer.parseInt(tmp2);
                    pos ++;
                } else {
                    value[ind] += Integer.parseInt(tmp2);
                }
            }
        }
        bri.close();
    }

    private void writeToCsv(String toFileName) throws IOException {
        BufferedWriter bro = new BufferedWriter(new FileWriter(toFileName));
        if (!stat.isEmpty()) {
            int dif = 0;
            boolean first = true;
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < key.length; i++) {
                tmp.insert(0, String.format("%s,%d\n", key[i], value[i]));
                if (first) {
                    dif = value[i];
                    first = false;
                } else {
                    dif -= value[i];
                }
            }
            bro.write(tmp.toString());
            bro.write(String.format("result,%d\n", Math.abs(dif)));
            bro.close();
        }
    }
    
    private int containsKey(String fkey) {
        for(int i = 0; i < key.length; i++) {
            if(key[i] != null && key[i].equals(fkey) == true) {
                return i;
            }
        }
        return -1;
    }
}
