package core.basesyntax;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_WORD = "supply";
    private static final String BUY_WORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        //code?????

    }


    public void readFromFileName() {
        int resultSupply = 0;
        int resultBuy = 0;

        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            String[] lines = strings.toArray(new String[strings.size()]);
            for (String line : lines) {
                String[] everyLine = line.split(",");
                if (everyLine[0].equals("supply")) {
                    resultSupply = resultSupply + Integer.parseInt(everyLine[1]);
                } else {
                    resultBuy = resultBuy + Integer.parseInt(everyLine[1]);
                }
            }

            StringBuilder builder = new StringBuilder();
            builder.append("supply").append(",").append(resultSupply).append(System.lineSeparator());
            builder.append("buy").append(",").append(resultBuy).append(System.lineSeparator());
            builder.append("result").append(",").append(resultSupply - resultBuy);
            System.out.println(builder);
            String text = builder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file ", e);
        }

        }



    }

    public void writeToFileName() {
        try {
            FileWriter output = new FileWriter(toFileName); // (File fileObj)
            output.write(text);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

}
