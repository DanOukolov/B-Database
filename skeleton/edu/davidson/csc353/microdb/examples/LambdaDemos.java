package edu.davidson.csc353.microdb.examples;

import java.util.function.Supplier;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaDemos {

	public static void main(String[] args) {
		// The basic Runnables
		
		Runnable myRunnable1 = () -> System.out.println("Hello World!");

		myRunnable1.run();

		Runnable myRunnable2 = () -> { 
			System.out.print("More than one command?... ");
			System.out.println("Use braces!");
		};

		myRunnable2.run();

		// Supplier

		Supplier<String> mySupplier1 = () -> { return "I am a string"; };

		System.out.println(mySupplier1.get());

		Supplier<String> mySupplier2 = () -> "Return is implicit";

		System.out.println(mySupplier2.get());
		
		// Function
		
		Function<Integer, String> myFunction1 = (argument) -> String.valueOf(argument);

		System.out.println(myFunction1.apply(3));
		System.out.println(myFunction1.apply(5));
		
		// Why is it useful?
		
		String[] users = { "Alice" , "Bob", "Carlos", "Daniela", "Efrahim", "Fabiana" };
		
		// Sort forward
		Arrays.sort(users, (String a, String b) -> { return a.compareTo(b); });
		
		System.out.println(Arrays.toString(users));

		// Sort backward
		Arrays.sort(users, (String a, String b) -> { return - a.compareTo(b); });
		
		System.out.println(Arrays.toString(users));

		// Sort by second letter
		Arrays.sort(users, (String a, String b) -> { return a.charAt(1) - b.charAt(1); });
		
		System.out.println(Arrays.toString(users));

		// Sort the reverse strings
		Arrays.sort(users, (String a, String b) -> { 
			StringBuilder ra = new StringBuilder(a);
			StringBuilder rb = new StringBuilder(b);

			a  = ra.reverse().toString();
			b = rb.reverse().toString();

			return a.compareTo(b);
		});
		
		System.out.println(Arrays.toString(users));
		
		// Can I make my own functions that receive functions? SURE!
		LambdaDemos demos = new LambdaDemos();
		
		demos.runFunctionalDemos();
	}
	
	private void runFunctionalDemos() {
		/////////////////////////////////////
		// One-type generic class/function //
		/////////////////////////////////////

		GenericClass1<Integer> generic1 = new GenericClass1<Integer>();
		
		int length1 = generic1.myDynamicFunction("abc", (String arg1) -> arg1.length() );
		
		System.out.println(length1);

		/////////////////////////////////////
		// One-type generic class/function //
		/////////////////////////////////////

		GenericClass2<String, Double, Double> generic2 = new GenericClass2<String, Double, Double>();
		
		double length2 = generic2.myDynamicFunction("abc", Math.PI, (String arg1, Double arg2) -> {
			return arg1.length() * arg2;
		});
		
		System.out.println(length2);
	}
	
	class GenericClass1<T> {
		public T myDynamicFunction(String parameter, Function<String, T> myFunction) {
			return myFunction.apply(parameter);
		}
	}

	class GenericClass2<I,J,O> {
		public O myDynamicFunction(I parameter1, J parameter2, BiFunction<I,J,O> myFunction) {
			return myFunction.apply(parameter1, parameter2);
		}
	}

	// BTW: Here's a "fixed-type" one
	public static Integer myDynamicFunction(String parameter, Function<String, Integer> myFunction) {
		return myFunction.apply(parameter);
	}
}
