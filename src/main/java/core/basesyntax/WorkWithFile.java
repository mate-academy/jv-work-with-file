package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public static void getStatistic(String fromFileName, String toFileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fromFileName));

        int totalSupply = 0;
        int totalBuy = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String operation = fields[0];
            int amount = Integer.parseInt(fields[1]);
            if (operation.equals(OPERATION_SUPPLY)) {
                totalSupply += amount;
            } else if (operation.equals(OPERATION_BUY)) {
                totalBuy += amount;
            }
        }

        reader.close();

        int result = totalSupply - totalBuy;
        FileWriter writer = new FileWriter(toFileName);
        writer.write("supply," + totalSupply + "\n");
        writer.write("buy," + totalBuy + "\n");
        writer.write("result," + result + "\n");
        writer.close();
    }
}
