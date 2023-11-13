package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        int supply = 0;
        int buy = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] str = value.split(",");
                if (str[0].equals("supply")) {
                    supply += Integer.parseInt(str[1]);
                } else if (str[0].equals("buy")) {
                    buy += Integer.parseInt(str[1]);
                }
                value = bufferedReader.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException("Can't read file",e);
        }

        String s = "supply," + supply + System.lineSeparator() + "buy," + buy
                        + System.lineSeparator() + "result," + (supply - buy);
        try {
            Files.write(Path.of(toFileName),s.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file",e);
        }
    }
}
