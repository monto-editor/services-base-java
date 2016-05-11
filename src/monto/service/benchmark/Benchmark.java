package monto.service.benchmark;

import monto.service.types.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Benchmark {

    protected String filetype;

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

    public void runBenchmark(Path corpus, Path csvOutput, int repititions, long interval) throws Exception {
        long[] overall = new long[repititions];
        long[] productiveTime = new long[repititions];
        setup();

        try {
//			{
//				int i = 1;
//				int files = new File(corpus.toString()).listFiles().length;
//				for(Path file: Files.newDirectoryStream(corpus, "*.java")) {
//					System.out.printf("warmup [%d/%d]: %s\n", i++, files, file);
//					try{
//						warmup(new Source(file.toString()), new String(Files.readAllBytes(file), StandardCharsets.UTF_8));
//					} catch(Exception e) {
//						System.err.println(e);
//						restart();
//					}
//				}
////			}
            final Duration timeout = Duration.ofSeconds(10);
            ExecutorService executor = Executors.newSingleThreadExecutor();

            boolean started = false;
            try (PrintWriter csv = new PrintWriter(Files.newBufferedWriter(csvOutput), true)) {
                csv.println("file,bytes,overall,productive");

                for (Path file : Files.newDirectoryStream(corpus, filetype)) {

                    String contents = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                    int lines = countLineNumbers(contents);

                    Thread.sleep(100);
//					
//					while(true) {
//						try {
//							final Future<Void> future = executor.submit(() -> {
                    for (int i = 0; i < repititions; i++) {
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
//								return null;
//							});
//							future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
//							break;
//						} catch(Throwable e) {
//							System.err.println("timeout");
//							restart();
//						}
//					}

                    for (int i = 0; i < repititions; i++)
                        csv.printf("%s,%d,%d,%d\n", file.getFileName(), contents.length(), overall[i], productiveTime[i]);
                    System.out.printf("file: %s\nlines: %d\n%s\n\n",
                            file.getFileName(),
                            lines,
                            statistics(overall));
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