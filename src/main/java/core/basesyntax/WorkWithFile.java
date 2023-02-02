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
        try (BufferedReader bri = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bro = new BufferedWriter(new FileWriter(toFileName))) {
            while ((line = bri.readLine()) != null) {
                String[] em = line.split(",");
                if (em.length == 2) {
                    if (st.containsKey(em[0].trim())) {
                        st.replace(em[0].trim(), st.get(em[0].trim()) + Integer.parseInt(em[1]
                                .trim()));
                    } else {
                        st.put(em[0].trim(), Integer.parseInt(em[1].trim()));
                    }
                }
            }
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
            e.printStackTrace();
        }
    }
}
