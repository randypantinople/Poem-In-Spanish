package pantinople;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class PoemInEnglish  implements  Runnable{
    private ArrayBlockingQueue<String> buffer; // create a shared buffer
    private String color;

    public PoemInEnglish(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        List<String> poems = poem(); //call the method poem to get all the lines of poems

        for(String line : poems) { // read each line
            try{
                System.out.println( color + line); // print each line
                //this method will lock the thread until there is room in the buffer to write data
                buffer.put(line); // put each line in the buffer

                // allow the other tread to execute.
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //put the string "EOF" in the buffer after all lines of the poem.
        try {
            buffer.put("EOF");
        } catch(InterruptedException e) {
        }

    }

    public List<String> poem(){
        List<String> poems = new ArrayList<>();
        poems. add("The rose is a rose,");
        poems. add("And was always a rose.");
        poems.add("But the theory now goes");
        poems.add("That the apple’s a rose,");
        poems.add("And the pear is, and so’s");
        poems.add("The plum, I suppose.");
        poems.add("The dear only knows");
        poems.add("What will next prove a rose.");
        poems.add("You, of course, are a rose –");
        poems.add("But were always a rose.");

        return poems;
    }

}
