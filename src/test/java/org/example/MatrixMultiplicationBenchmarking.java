package org.example;

import org.example.MatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MatrixMultiplicationBenchmarking {

	@State(Scope.Thread)
	public static class Operands{
		@Param({"10", "100", "512", "1024", "2048", "10000"}) // Testing different matrix sizes
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
	public void multiplication(Operands operands){
		new MatrixMultiplication().execute(operands.a, operands.b);
	}
}










