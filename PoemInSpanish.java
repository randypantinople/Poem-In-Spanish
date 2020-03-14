package pantinople;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class PoemInSpanish implements Runnable{

    private static final String EOF = "EOF"; // this will be read by the thread
    private ArrayBlockingQueue<String> buffer; // create a shared buffer
    private String color;

    public PoemInSpanish(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        List<String> poems = poemas(); // call the method poemas() to get all the spanish lines of the phones

        while (true) {
            synchronized (buffer) { // synchronized all buffers in the loop
                try {
                    //if the buffer is empty , the other thread will start
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    // check if the buffer has 'EOF' string. If it does, it will exit program.
                    if (buffer.peek().equals(EOF)) {
                        break;
                    // if buffer is not empty and the string is not"EOF".
                    } else {
                        //loop through each line
                        for(String line : poems) {
                            //this method will lock the thread until there's new data to read.
                            buffer.take();
                            System.out.println("\t"  + color + line ); // print each line with color
                            }
                        }

                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }

        }


    public static  List<String> poemas(){
        List<String> poema = new ArrayList<>();

        poema.add("La rosa es una rosa");
        poema.add("Y siempre eso será.");
        poema.add("Pero ahora va la teoría");
        poema.add("Que la manzana es una rosa");
        poema.add("Y tambien es la pera, y");
        poema.add("La ciruela, sopongo.");
        poema.add("La querida solamente sabe");
        poema.add("Lo que próximo será una rosa.");
        poema.add("Usted, por supuesto es una rosa -");
        poema.add("Pero eras siempre.");

        return poema;

    }
}
