import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class NIOSelector {
    public static void main(String[] args) {
        try {
            Selector selector=Selector.open();
//            SelectionKey selectionKey=??
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
