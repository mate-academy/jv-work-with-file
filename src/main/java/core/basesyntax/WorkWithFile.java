package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int FIELDS = 2;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private String[] key = new String[FIELDS];
    private Integer[] value = new Integer[FIELDS];
    private int pos = 0;
    
    public void getStatistic(String fromFileName, String toFileName) {
        inputFromFile(fromFileName);
        outputToFile(toFileName);
    }
    
    private void inputFromFile(String fromFileName) {
        try (BufferedReader bri = new BufferedReader(new FileReader(fromFileName))) {
            while (bri.ready()) {
                String line = bri.readLine();
                applyInput(line);
            }
            bri.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + "Problem with input from CSV file");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e.getMessage() + "Numeric parse exception");
        }
    }
    
    private void outputToFile(String toFileName) {
        try (BufferedWriter bro = new BufferedWriter(new FileWriter(toFileName))) {
            String result = applyOutput();
            bro.write(result);
            bro.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + "Problem with output to CSV file");
        }
    }

    private void applyInput(String line) {
        String[] em = line.split(DELIMITER);
        if (em.length == FIELDS) {
            var tmp1 = em[FIRST].trim();
            var tmp2 = em[SECOND].trim();
            var ind = containsKey(tmp1);
            if (ind == -1) {
                key[pos] = tmp1;
                value[pos] = Integer.parseInt(tmp2);
                pos++;
            } else {
                value[ind] += Integer.parseInt(tmp2);
            }
        }
    }

    private String applyOutput() {
        StringBuilder tmp = new StringBuilder();
        if (pos == FIELDS) {        
            sort();
            int dif = 0;
            boolean first = true;
            for (int i = 0; i < key.length; i++) {
                tmp.append(key[i]).append(DELIMITER).append(value[i])
                    .append(System.lineSeparator());
                if (first) {
                    dif = value[i];
                    first = false;
                } else {
                    dif -= value[i];
                }
            }
            tmp.append("result").append(DELIMITER).append(Math.abs(dif))
                .append(System.lineSeparator());
            clear();
        }
        return tmp.toString();
    }
    
    private int containsKey(String fkey) {
        for (int i = 0; i < key.length; i++) {
            if (key[i] != null && key[i].equals(fkey) == true) {
                return i;
            }
        }
        return -1;
    }
    
    private void sort() {
        for (int i = 0; i < FIELDS - 1; i++) {
            if (key[i].hashCode() > key[i + 1].hashCode()) {
                var k = key[i];
                key[i] = key[i + 1];
                key[i + 1] = k;
                var v = value[i];
                value[i] = value[i + 1];
                value[i + 1] = v;
            }
        }
    }

    private void clear() {
        key = new String[FIELDS];
        value = new Integer[FIELDS];
        pos = 0;
    }
}
