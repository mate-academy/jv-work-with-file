package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result;

        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();

            while (value != null) {
                builder.append(value);
                String[] temp = value.split(",");
                if (temp[0].equals("supply")) {
                    supply += Integer.parseInt(temp[1]);
                } else {
                    buy += Integer.parseInt(temp[1]);
                }
                value = reader.readLine();
            }

            result = supply - buy;
            System.out.println("supply," + supply + System.lineSeparator()
                                + "buy," + buy + System.lineSeparator()
                                + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }

        String finalSupply = "supply," + supply;
        String finalBuy = "buy," + buy;
        String finalResult = "result," + result;

        String[] res = new String[]{finalSupply, finalBuy, finalResult};

        for (String resources : res) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
                writer.write(resources);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
