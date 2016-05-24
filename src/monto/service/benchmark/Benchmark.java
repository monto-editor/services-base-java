package monto.service.benchmark;

import monto.service.types.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Benchmark {

    protected String fileType;

    protected void setup() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    protected void warmup(Source source, String contents) throws Exception {
        premeasure(source, contents);
        measure(source, contents);
    }

    protected void premeasure(Source source, String contents) throws Exception {
    }

    protected abstract long measure(Source source, String contents) throws Exception;

    public void runBenchmark(Path corpus, Path csvOutput, int repetitions, long interval, int warmUpRounds) throws Exception {
        long[] overall = new long[repetitions];
        long[] productiveTime = new long[repetitions];
        setup();

        try {
            List<Path> files = Files.walk(corpus)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith("." + fileType))
                    .collect(Collectors.toList());

            int warmUpCount = 1;
            for (int i = 1; i <= warmUpRounds; i++) {
                for (Path file : files) {
                    System.out.printf("warmup [%d/%d]: %s\n", warmUpCount++, files.size() * warmUpRounds, file);
                    try {
                        warmup(new Source(file.toString()), new String(Files.readAllBytes(file), StandardCharsets.UTF_8));
                    } catch (Exception e) {
                        System.err.println(e);
                        restart();
                    }
                }
            }
            try (PrintWriter csv = new PrintWriter(Files.newBufferedWriter(csvOutput), true)) {
                csv.println("file,bytes,overall,productive");

                for (Path file : files) {
                    String contents = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                    int lines = countLineNumbers(contents);

                    System.out.printf("file: %s\nlines: %d\n", file.getFileName(), lines);

                    Thread.sleep(100);
                    for (int i = 0; i < repetitions; i++) {
                        try {
                            Source source = new Source(file.toString());
                            premeasure(source, contents);
                            Thread.sleep(interval);
                            long start = System.nanoTime();
                            long productive = measure(source, contents);
                            long end = System.nanoTime();
                            overall[i] = end - start;
                            productiveTime[i] = productive;
                        } catch (Throwable e) {
                            System.err.println(e);
                            restart();
                            i = -1;
                        }
                    }
                    for (int i = 0; i < repetitions; i++)
                        csv.printf("%s,%d,%d,%d\n", file.getFileName(), contents.length(), overall[i], productiveTime[i]);
                    System.out.println(statistics(overall));
                    System.out.println();
                }
            }
        } finally {
            tearDown();
        }
    }

    private void restart() throws Exception {
        tearDown();
        setup();
    }

    public static int countLineNumbers(String file) {
        int lines = 0;
        BufferedReader reader = new BufferedReader(new StringReader(file));
        try {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            // Cannot happen
        }
        return lines;
    }

    public static double mean(long[] xs) {
        long mean = 0;
        for (long x : xs)
            mean += x;
        return ((double) mean) / ((double) xs.length);
    }

    public static double variance(long[] xs, double mean) {
        double variance = 0;
        for (long x : xs)
            variance += Math.pow((x + mean) / 1e6, 2);
        return variance / ((double) xs.length);
    }


    public static double standardDeviation(double var) {
        return Math.sqrt(var);
    }

    public static String statistics(long[] xs) {
        double m = mean(xs);
        double var = variance(xs, m);
        double std = standardDeviation(var);
        return String.format("mean: %f ms\nstandard deviation: %f", m / 1e6, std);
    }
}