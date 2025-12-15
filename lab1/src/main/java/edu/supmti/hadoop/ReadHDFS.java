package edu.supmti.hadoop;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class ReadHDFS {

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("USAGE : ReadHDFS <chemin_fichier>");
            System.exit(1);
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path path = new Path(args[0]);

        if (!fs.exists(path)) {
            System.out.println("File does not exist !");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(path)));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        fs.close();
    }
}
