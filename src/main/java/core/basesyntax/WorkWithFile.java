package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String information;
        int buy = 0;
        int supply = 0;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));

            while ((information = bufferedReader.readLine()) != null) {
                String[] str = information.split(",");
                if (str[FIRST_INDEX].equals("supply")) {
                    supply += Integer.parseInt(str[SECOND_INDEX]);
                } else {
                    buy += Integer.parseInt(str[SECOND_INDEX]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        write(supply, buy, toFileName);
    }

    public void write(int supply, int buy, String toFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy) + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(createResultString(supply, buy));
    }

    public String createResultString(int supply, int buy) {
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply, " + supply + System.lineSeparator())
                .append("buy, " + buy + System.lineSeparator())
                .append("result, " + result + System.lineSeparator());

        return stringBuilder.toString();
    }
}







