package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    private int supply;
    private int buy;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFileAndCount(fromFileName);
    }

    private void readFromFileAndCount(String fromFileName) {
        supply = 0;
        buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] info = line.split(",");
                if (info[0].equals("supply")) {
                    supply += Integer.parseInt(info[1]);
                } else if (info[0].equals("buy")) {
                    buy += Integer.parseInt(info[1]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void writeToFile(String toFileName) {
        String supplyInfo = "supply," + supply;
        String buyInfo = "buy," + buy;
        String resultInfo = "result," + (supply - buy);
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        if (!file.exists()) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
                bufferedWriter.write(supplyInfo);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(buyInfo);
                bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write(resultInfo);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException("Can't close BufferedWriter", e);
                    }
                }
            }
        }
    }

    // I have also come up with this idea, and I couldn't decide what would be better.
    private void readFromFileAndCount1(String fromFileName) {
        supply = 0;
        buy = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            StringBuilder builder = new StringBuilder();
            while (line != null) {
                builder.append(line).append(",");
                line = bufferedReader.readLine();
            }
            String[] info = builder.toString().split(",");
            for (int i = 0; i < info.length - 1; i += 2) {
                if (info[i].equals("supply")) {
                    supply += Integer.parseInt(info[i + 1]);
                } else if (info[i].equals("buy")) {
                    buy += Integer.parseInt(info[i + 1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }
}