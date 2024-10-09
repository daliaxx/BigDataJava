package org.example;

import org.example.MatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.lang.management.ManagementFactory;
import org.example.MatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime) // Measures execution time for a single operation
@OutputTimeUnit(TimeUnit.MILLISECONDS) // Outputs the result in milliseconds
public class MatrixMultiplicationBenchmarking {

	@State(Scope.Thread)
	public static class Operands {
		@Param({"10", "100", "512", "1024"}) // Testing different matrix sizes
		private int n;
		private double[][] a;
		private double[][] b;

		@Setup
		public void setup() {
			a = new double[n][n];
			b = new double[n][n];
			Random random = new Random();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = random.nextDouble();
					b[i][j] = random.nextDouble();
				}
			}
		}
	}

	@Benchmark
	public void multiplication(Operands operands) {
		// Measure the execution time
		long startTime = System.nanoTime();

		new MatrixMultiplication().execute(operands.a, operands.b);

		long endTime = System.nanoTime();
		long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

		// Output the execution time
		System.out.println("Execution Time: " + durationInMilliseconds + " ms");

		// Measure memory usage
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage(); // Heap memory used by the JVM
		long usedMemory = heapUsage.getUsed(); // Memory used in bytes

		// Output the memory usage
		System.out.println("Memory Used: " + (usedMemory / 1024 / 1024) + " MB");
	}
}
