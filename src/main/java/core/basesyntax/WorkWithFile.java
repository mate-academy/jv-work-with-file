package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String information;
        int buy = 0;
        int supply = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));

            while ((information = bufferedReader.readLine()) != null) {
                String[] str = information.split(",");

                if (str[0].equals("supply")) {
                    supply += Integer.parseInt(str[1]);
                } else {
                    buy += Integer.parseInt(str[1]);
                }
            }
            bufferedReader.close();
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + (supply - buy) + "\n");
            writer.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName + e);
        }
    }
}
