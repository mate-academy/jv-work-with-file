package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        //let's read
        StringBuilder sb = new StringBuilder();
        int supply = 0;
        int buy = 0;
        boolean flag = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFileName));
            int value = br.read();
            while (value != -1) {
                // '/n' is 10; 'b' is 98; 's' is 115;
                if (value == 115) {
                    br.skip(6);
                    value = br.read();
                    flag = true;
                }
                if (value == 98) {
                    br.skip(3);
                    value = br.read();
                    flag = false;
                }
                if (value != 10) {
                    sb.append((char) value);
                    value = br.read();
                } else if (flag) {
                    supply += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                    value = br.read();
                } else {
                    buy += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                    value = br.read();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Check your file", e);
        }
        int result = supply - buy;

        //let's write
        String text = "supply," + supply + System.lineSeparator() + "buy," + buy + System.lineSeparator() + "result," + result;
        File file = new File(fromFileName);
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write", e);
        }
    }
}
