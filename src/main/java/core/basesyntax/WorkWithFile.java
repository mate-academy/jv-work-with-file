package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int FIELDS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File tofile = new File(toFileName);
        int allSupply = 0;
        int allBuy = 0;
        String[] tempData = new String[FIELDS];
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                tempData = value.split(",");
                switch (tempData[0]) {
                    case BUY:
                        allBuy += Integer.valueOf(tempData[1]);
                    case SUPPLY:
                        allSupply += Integer.valueOf(tempData[1]);
                        break;
                }
                value = reader.readLine();
            }
            int result = allSupply - allBuy;
            writer = new BufferedWriter(new FileWriter(tofile));
            StringBuilder outData = new StringBuilder();
            outData.append(SUPPLY)
                    .append(",")
                    .append(allSupply)
                    .append(System.lineSeparator())
                    .append(BUY)
                    .append(",")
                    .append(allBuy)
                    .append(System.lineSeparator())
                    .append("result")
                    .append(",")
                    .append(result);
            writer.write(outData.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close file");
                }
            }
        }
    }
}
