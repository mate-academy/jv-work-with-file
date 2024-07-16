package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] output = readFile(fromFileName);
        String result = getResult(output);
        writeFile(result, toFileName);
    }

    public void writeFile(String result, String toFileName) {
        BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(toFileName, true));
                writer.write(result);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    }

    public int[] readFile(String fromFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String string = stringBuilder.toString();
            String[] split = string.split(System.lineSeparator());
            int supply = 0;
            int buy = 0;
            for (int i = 0; i < split.length; i++) {
                String substring = split[i].substring(split[i].indexOf(",") + 1);
                int parseInt = Integer.parseInt(substring);
                if (split[i].substring(0, split[i].indexOf(",")).equals("supply")) {
                    supply += parseInt;
                } else {
                    buy += parseInt;
                }
            }
            return new int[] {supply, buy, (supply - buy)};
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String getResult(int[] arrays) {
        String result = "supply," + arrays[0] + System.lineSeparator()
                + "buy," + arrays[1] + System.lineSeparator()
                + "result," + arrays[2] + System.lineSeparator();
        return result;
    }
}
