package core.basesyntax;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> buf = readFile(fromFileName);
        int buy = 0;
        int supply = 0;
        int sum;
        for (String s : buf) {
            if (s != null && !s.equals("null")) {
                String[] split = s.split(",");
                if (split[0].equals("buy")) {
                    buy += Integer.parseInt(split[1]);
                } else if (split[0].equals("supply")) {
                    supply += Integer.parseInt(split[1]);
                }
            }
        }
        sum = supply - buy;
        writeFile(toFileName, supply, buy, sum);
    }
    public List<String> readFile(String fromFileName) {
        List<String> buf = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                buf.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("cant read a file", e);
        }

        return buf;
    }
    public void writeFile(String toFileName, int supply, int buy, int sum) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            String[] result = new String[] {"supply," + supply,"buy," + buy, "result," + sum};
            for (String s : result) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cant write to file", e);
        }
    }
}
