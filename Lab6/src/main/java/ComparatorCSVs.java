import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;

public class ComparatorCSVs {

    public static void compareCSVs(String file1, String file2, int k) {
        try {
        ArrayList <String> al1 = new ArrayList();
        ArrayList <String> al2 = new ArrayList();

        BufferedReader CSVFile1 = new BufferedReader(new FileReader(file1));
        String dataRow1 = CSVFile1.readLine();
        String Titles=dataRow1;
        while (dataRow1 != null) {
            al1.add(dataRow1);
            dataRow1 = CSVFile1.readLine(); // Read next line of data.
        }

        CSVFile1.close();

        BufferedReader CSVFile2 = new BufferedReader(new FileReader(file2));
        String dataRow2 = CSVFile2.readLine();
        while (dataRow2 != null) {
            al2.add(dataRow2);
            dataRow2 = CSVFile2.readLine(); // Read next line of data.
        }
        CSVFile2.close();

        for(String bs : al2) {
            al1.remove(bs);
        }

        int size = al1.size();

        FileWriter writer=new FileWriter("difference" + k + ".csv");
        writer.append(Titles);
        writer.append("\n");
        int i = 0;
        while(size!=0) {
            i++;
            size--;
            writer.append(al1.get(size));
            writer.append('\n');
        }
        writer.flush();
        writer.close();
        FileWriter log_writer =new FileWriter("log" + k + ".log");
        log_writer.append("К-сть рядків, що відрізняються - " + i);
        log_writer.append('\n');
        log_writer.append("Такі рядки можна переглянути в файлі difference.csv.");
        log_writer.flush();
        log_writer.close();
        Assert.assertEquals(i, 0);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
