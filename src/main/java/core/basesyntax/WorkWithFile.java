package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        HashMap<String, Integer> st = new HashMap<>();
        try (BufferedReader bri = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bri.readLine()) != null) {
                String[] em = line.split(",");
                if (em.length == 2) {
                    var tmp1 = em[0].trim();
                    var tmp2 = em[1].trim();
                    if (st.containsKey(tmp1)) {
                        st.replace(tmp1, st.get(tmp1) + Integer.parseInt(tmp2));
                    } else {
                        st.put(tmp1, Integer.parseInt(tmp2));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            if (e instanceof IOException) {
                throw new RuntimeException(e.getMessage() + "Problem with input file");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        try (BufferedWriter bro = new BufferedWriter(new FileWriter(toFileName))) {
            if (!st.isEmpty()) {
                int dif = 0;
                int i = 0;
                String tmp = "";
                for (String key : st.keySet()) {
                    tmp = String.format("%s,%d\n", key, st.get(key)) + tmp;
                    if (i == 0) {
                        dif = st.get(key);
                    } else {
                        dif -= st.get(key);
                    }
                    i++;
                }
                bro.write(tmp);
                bro.write(String.format("result,%d\n", Math.abs(dif)));
            }
        } catch (IOException | NumberFormatException e) {
            if (e instanceof IOException) {
                throw new RuntimeException(e.getMessage() + "Problem with output file");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
