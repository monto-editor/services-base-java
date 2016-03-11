package monto.service.benchmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import monto.service.types.Source;

public abstract class Benchmark {

	protected void setup() throws Exception {}
	protected void tearDown() throws Exception {}

	protected void warmup(Source source, String contents) throws Exception {
		premeasure(source, contents);
		measure(source, contents);
	}


	protected void premeasure(Source source, String contents) throws Exception {}
	protected abstract void measure(Source source, String contents) throws Exception;

	public void runBenchmark(Path corpus, Path csvOutput, int repititions, long interval) throws Exception {
		long[] times = new long[repititions];
		boolean[] successful = new boolean[repititions];
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
//			}
			
			try(PrintWriter csv = new PrintWriter(Files.newBufferedWriter(csvOutput))) {
				csv.println("file,lines,bytes,roundtrip");
				for(Path file: Files.newDirectoryStream(corpus, "*.java")) {
	
					String contents = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
					int lines = countLineNumbers(contents);
					
					Thread.sleep(1000);
					for(int i = 0; i < repititions; i++) {
						try {
							Source source = new Source(file.toString());
							premeasure(source, contents);
							Thread.sleep(interval);
							long start = System.nanoTime();
							measure(source, contents);
							long end = System.nanoTime();
							times[i] = end-start;
						} catch(Exception e) {
							System.err.println(e);
							restart();
							i = -1;
						}
					}
					for(int i = 0; i < repititions; i++)
						csv.printf("%s,%d,%d,%d\n", file.getFileName(), lines, contents.length(), times[i]);
					System.out.printf("file: %s\nlines: %d\n%s\n\n",
						file.getFileName(),
						lines,
						statistics(times));
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
			while(reader.readLine() != null) {
				lines++;
			}
		} catch (IOException e) {
			// Cannot happen
		}
		return lines;
	}
	
	public static double mean(long[] xs) {
		long mean = 0;
		for(long x: xs)
			mean+=x;
		return ((double)mean)/((double)xs.length);
	}
	
	public static double variance(long[] xs, double mean) {
		double variance = 0;
		for(long x: xs)
			variance += Math.pow((x+mean)/1e6, 2);
		return variance / ((double)xs.length);
	}


	public static double standardDeviation(double var) {
		return Math.sqrt(var);
	}
	
	public static String statistics(long[] xs) {
		double m = mean(xs);
		double var = variance(xs, m);
		double std = standardDeviation(var);
		return String.format("mean: %f ms\nstandard deviation: %f", m/1e6, std);
	}
}