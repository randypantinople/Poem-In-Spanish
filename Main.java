package pantinople;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {

    	//Display the title of the poem and the author
		System.out.println(ThreadColor.ANSI_BLUE + "The Rose Family -- A Poem By Robert Frost \n");

		//create a shared buffer.
		ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(1);

		//create  thread pools.
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		//Create an object for each class.
		PoemInEnglish poemInEnglish = new PoemInEnglish(buffer, ThreadColor.ANSI_RED);
		PoemInSpanish poemInSpanish = new PoemInSpanish(buffer, ThreadColor.ANSI_YELLOW);

		//execute each threads
		executorService.execute(poemInEnglish);
		executorService.execute(poemInSpanish);

		//close the execution
		executorService.shutdown();

	}

}
