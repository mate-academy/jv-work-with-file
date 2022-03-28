package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            String[] arr = builder.toString().split(System.lineSeparator());
            int [] result = new int[] {0, 0};
            for (String s : arr) {
                String[] res = s.split(",");
                if (res[0].equals("supply")) {
                    result[0] += Integer.parseInt(res[1]);
                } else {
                    result[1] += Integer.parseInt(res[1]);
                }
            }
            String[] res = new String[3];
            res[0] = "supply," + result[0];
            res[1] = System.lineSeparator() + "buy," + result[1];
            res[2] = System.lineSeparator() + "result," + (result[0] - result[1]);
            for (String dat : res) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(toFileName, true))) {
                    bufferedWriter.write(dat);
                } catch (IOException e) {
                    throw new RuntimeException("Can't write file" + toFileName, e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

    }
}
