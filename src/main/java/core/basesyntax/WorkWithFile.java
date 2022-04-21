package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String file = builder.toString();
        String[] split = file.split(" ");
        for (int i = 0; i < split.length; i++) {
            String[] divided = split[i].split(",");
            if (divided[0].equals("supply")) {
                supply = supply + Integer.valueOf(divided[1]);
            } else {
                buy = buy + Integer.valueOf(divided[1]);
            }
        }
        int result = supply - buy;
        String[] finalFile = new String[3];
        finalFile[0] = "supply," + Integer.toString(supply);
        finalFile[1] = "buy," + Integer.toString(buy);
        finalFile[2] = "result," + Integer.toString(result);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(toFileName));
            for (String res: finalFile) {
                writer.write(res);
                writer.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException("Can`t close the file");
            }
        }
    }
}
