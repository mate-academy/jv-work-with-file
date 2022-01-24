package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File file1 = new File(toFileName);
        String str = "";
        String str1 = "";
        try {
            BufferedReader bufRead = new BufferedReader(new FileReader(file));
            BufferedWriter bufWrite = new BufferedWriter(new FileWriter(file1));
            str = bufRead.readLine();
            int cnt = 0;
            while (str1 != null) {
                str = (cnt == 0) ? str + str1 : str + " " + str1;
                str1 = bufRead.readLine();
                cnt++;
            }
            bufRead.close();
            String [] str2 = str.split(" ");
            Arrays.sort(str2);
            String str3 = str2 [0].substring(0, (str2 [0].indexOf(",")));
            cnt = 0;
            int cnt1 = 0;
            for (int i = 0; i < str2.length; i++) {
                if (i == str2.length - 1) {
                    if (str3.equals(str2 [i].substring(0, (str2 [i].indexOf(","))))) {
                        cnt = Integer.parseInt(str2 [i].substring(str2 [i].indexOf(",")
                                + 1, str2 [i].length())) + cnt;
                        if (cnt1 <= cnt) {
                            bufWrite.close();
                            BufferedReader bufRead1 = new BufferedReader(new FileReader(file1));
                            str = bufRead1.readLine();
                            BufferedWriter bufWrite2 = new BufferedWriter(new FileWriter(file1));
                            bufWrite2.write(str3 + "," + cnt + System.lineSeparator());
                            bufWrite2.write(str + System.lineSeparator());
                            bufWrite2.write("result" + ","
                                    + Math.abs(cnt - cnt1));
                            bufRead1.close();
                            bufWrite2.close();
                            continue;
                        }
                        bufWrite.write(str3 + "," + cnt + System.lineSeparator());
                        bufWrite.write("result" + ","
                                + Math.abs(cnt - cnt1));
                        continue;
                    } else {
                        bufWrite.write(str3 + "," + cnt + System.lineSeparator());
                        str3 = str2 [i].substring(0, (str2 [i].indexOf(",")));
                        cnt1 = cnt;
                        cnt = Integer.parseInt(str2 [i].substring(str2 [i].indexOf(",")
                                + 1, str2 [i].length()));
                        if (cnt1 < cnt) {
                            bufWrite.close();
                            BufferedReader bufRead1 = new BufferedReader(new FileReader(file1));
                            str = bufRead1.readLine();
                            BufferedWriter bufWrite2 = new BufferedWriter(new FileWriter(file1));
                            bufWrite2.write(str3 + "," + cnt + System.lineSeparator());
                            bufWrite2.write(str + System.lineSeparator());
                            bufWrite2.write("result" + ","
                                    + Math.abs(cnt - cnt1));
                            bufRead1.close();
                            bufWrite2.close();
                            continue;
                        }
                        bufWrite.write(str3 + "," + cnt + System.lineSeparator());
                        bufWrite.write("result" + ","
                                + Math.abs(cnt - cnt1));
                        continue;
                    }

                }
                if (str3.equals(str2 [i].substring(0, (str2 [i].indexOf(","))))) {
                    cnt = Integer.parseInt(str2[i].substring(str2[i].indexOf(",")
                            + 1, str2[i].length())) + cnt;
                } else {
                    bufWrite.write(str3 + "," + cnt + System.lineSeparator());
                    str3 = str2 [i].substring(0, (str2 [i].indexOf(",")));
                    cnt1 = cnt;
                    cnt = Integer.parseInt(str2 [i].substring(str2 [i].indexOf(",")
                            + 1, str2 [i].length()));
                }
            }
            bufWrite.close();
        } catch (IOException e) {
            throw new RuntimeException("boo");
        }
    }
}
