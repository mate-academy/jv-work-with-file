package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("I can't read read file" + toFileName, e);
        }
        String[] dataFromMarket = stringBuilder.toString().split(System.lineSeparator());
        String report = createReport(dataFromMarket);
        try {
            File toFilFile = new File(toFileName);
            toFilFile.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException("I can't create file" + toFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new RuntimeException("I can't write information to the file" + toFileName, e);
        }
    }

    public String createReport(String[] dataFromMarket) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataFromMarket.length; i++) {
            if (dataFromMarket[i].contains("supply")) {
                supply += Integer.parseInt(dataFromMarket[i].substring(dataFromMarket[i].
                        indexOf(",") + 1, dataFromMarket[i].length()));
            } else if (dataFromMarket[i].contains("buy")) {
                buy += Integer.parseInt(dataFromMarket[i].substring(dataFromMarket[i].
                        indexOf(",") + 1, dataFromMarket[i].length()));
            }
        }
        return "supply," + supply
                + "\nbuy," + buy
                + "\nresult," + (supply - buy);
    }
}
