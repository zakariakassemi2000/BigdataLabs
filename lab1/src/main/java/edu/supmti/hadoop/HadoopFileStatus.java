<<<<<<< HEAD
package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("USAGE : HadoopFileStatus <chemin> <nom_fichier> <nouveau_nom>");
            System.exit(1);
        }

        String path = args[0];
        String fileName = args[1];
        String newName = args[2];

        Configuration conf = new Configuration();

        try {
            FileSystem fs = FileSystem.get(conf);

            Path filePath = new Path(path + "/" + fileName);

            if (!fs.exists(filePath)) {
                System.out.println("File does not exist !");
                System.exit(1);
            }

            FileStatus status = fs.getFileStatus(filePath);

            System.out.println("---- FILE INFO ----");
            System.out.println("Name : " + status.getPath().getName());
            System.out.println("Size : " + status.getLen() + " bytes");
            System.out.println("Owner : " + status.getOwner());
            System.out.println("Permission : " + status.getPermission());
            System.out.println("Replication : " + status.getReplication());
            System.out.println("Block size : " + status.getBlockSize());

            BlockLocation[] blocks =
                    fs.getFileBlockLocations(status, 0, status.getLen());

            for (BlockLocation b : blocks) {
                System.out.println("Block offset : " + b.getOffset());
                System.out.println("Block length : " + b.getLength());
                System.out.print("Hosts : ");
                for (String h : b.getHosts()) System.out.print(h + " ");
                System.out.println("\n-------------------");
            }

            // Rename
            fs.rename(filePath, new Path(path + "/" + newName));
            System.out.println("File renamed to : " + newName);

            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
=======
package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("USAGE : HadoopFileStatus <chemin> <nom_fichier> <nouveau_nom>");
            System.exit(1);
        }

        String path = args[0];
        String fileName = args[1];
        String newName = args[2];

        Configuration conf = new Configuration();

        try {
            FileSystem fs = FileSystem.get(conf);

            Path filePath = new Path(path + "/" + fileName);

            if (!fs.exists(filePath)) {
                System.out.println("File does not exist !");
                System.exit(1);
            }

            FileStatus status = fs.getFileStatus(filePath);

            System.out.println("---- FILE INFO ----");
            System.out.println("Name : " + status.getPath().getName());
            System.out.println("Size : " + status.getLen() + " bytes");
            System.out.println("Owner : " + status.getOwner());
            System.out.println("Permission : " + status.getPermission());
            System.out.println("Replication : " + status.getReplication());
            System.out.println("Block size : " + status.getBlockSize());

            BlockLocation[] blocks =
                    fs.getFileBlockLocations(status, 0, status.getLen());

            for (BlockLocation b : blocks) {
                System.out.println("Block offset : " + b.getOffset());
                System.out.println("Block length : " + b.getLength());
                System.out.print("Hosts : ");
                for (String h : b.getHosts()) System.out.print(h + " ");
                System.out.println("\n-------------------");
            }

            // Rename
            fs.rename(filePath, new Path(path + "/" + newName));
            System.out.println("File renamed to : " + newName);

            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
>>>>>>> de504af (first commit)
