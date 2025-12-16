package du.ismagi.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    public static void main(String[] args) throws Exception {

        // Vérification des arguments
        if (args.length != 2) {
            System.err.println("Usage: WordCount <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");

        // Classe principale
        job.setJarByClass(WordCount.class);

        // Mapper
        job.setMapperClass(TokenizerMapper.class);

        // Combiner et Reducer
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        // Types de sortie
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Fichier d'entrée
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // Dossier de sortie
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Lancer le job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
