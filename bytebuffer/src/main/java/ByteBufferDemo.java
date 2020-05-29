import java.nio.ByteBuffer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteBufferDemo {
    private ByteBuffer byteBuffer = ByteBuffer.allocate(8);

    private void put() {
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        byte b = 10;
        byteBuffer.put(b);
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        byteBuffer.putInt(100);
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("----------finish put-----------------");
    }

    private void get() {
        // before read, flip()
        byteBuffer.flip();
        byte b = byteBuffer.get();
        log.info("value is {}", b);
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("value is {}", byteBuffer.getInt());
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("---------------finish sequencial read-------------");
    }

    void mainOperations() {
        put();
        get();
        // absolute read 1
        byteBuffer.position(1);
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("value is {}", byteBuffer.getInt());
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("--------------finish absolute read 1-------------");
        byteBuffer.position(1);
        // absolute read 2
        log.info("value is {}", byteBuffer.getInt(1));
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
        log.info("--------------finish absolute read 2-------------");
        // clear when not used
        byteBuffer.clear();
        log.info("capacity: {}, positoin: {},limit: {} ",
            byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit());
    }

    public static void main(String[] args) {
        ByteBufferDemo demo = new ByteBufferDemo();
        demo.mainOperations();
    }
}
