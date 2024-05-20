package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fileFrom = new File(fromFileName);
        File fileTo = new File(toFileName);
        try {
            Scanner scanner = new Scanner(fileFrom);
            scanner.useDelimiter(",");
            StringBuilder stringBuilder = new StringBuilder();
            String text = String.valueOf(stringBuilder);
            while (scanner.hasNext()) {
                text = String.valueOf(stringBuilder.append(scanner.next()).append(" "));
            }
            BufferedWriter writer = getBufferedWriter(text, fileTo);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private static BufferedWriter getBufferedWriter(String text, File fileTo) throws IOException {
        String[] textData = text.split("\\W+");
        int supplyResult = 0;
        int buyResult = 0;
        for (int i = 0; i < textData.length; i++) {
            if (textData[i].equals("supply")) {
                supplyResult += Integer.parseInt(textData[i + 1]);
            } else if (textData[i].equals("buy")) {
                buyResult += Integer.parseInt(textData[i + 1]);
            }
        }
        int totalResult = supplyResult - buyResult;
        String result = "supply," + supplyResult
                + "\nbuy," + buyResult
                + "\nresult," + totalResult;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileTo));
        writer.write(result);
        return writer;
    }
}
