package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);
        try {
            int supply = 0;
            int buy = 0;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            String line = bufferedReader.readLine();
            while (line != null){
                String[] split = line.split(",");
                switch (split[0]) {
                    case ("supply"):
                        supply += Integer.parseInt(split[1]);
                        break;
                    case ("buy"):
                        buy += Integer.parseInt(split[1]);
                        break;
                }
                line = bufferedReader.readLine();
            }
            bufferedWriter.write("supply," + supply + System.lineSeparator() + "buy," + buy + System.lineSeparator() + "result," + (supply - buy));
            bufferedReader.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found",e);
        } catch (IOException e) {
            throw new RuntimeException("Runtime Exception", e);
        }
    }
}
