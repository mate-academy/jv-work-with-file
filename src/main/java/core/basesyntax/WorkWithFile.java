package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        BufferedWriter writer = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            String[] arraySplit = stringBuilder.toString().split("\\W+");
            int supply = 0;
            int buy = 0;
            int result = 0;
            for (int i = 0; i < arraySplit.length; i++) {
                if (arraySplit[i].equals("supply")) {
                    supply += Integer.parseInt(arraySplit[i + 1]);
                }
                if (arraySplit[i].equals("buy")) {
                    buy += Integer.parseInt(arraySplit[i + 1]);
                }
                result = supply - buy;
            }
            try {
                writer = new BufferedWriter(new FileWriter(toFileName, true));
                String[] resultArray = new String[]{"supply,", String.valueOf(supply)
                        + System.lineSeparator() + "buy,", String.valueOf(buy)
                        + System.lineSeparator() + "result,", String.valueOf(result)};
                for (String data : resultArray) {
                    writer.write(data);
                    writer.flush();
                }
            } catch (IOException ex) {
                throw new RuntimeException("Can't write the data to the file" + toFileName, ex);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't write data to the file" + toFileName, e);
                }
            }
        }
    }
}
