import java.nio.ByteBuffer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DirectBufferVsByteBuffer {
    private int cnt = 10000000;
    private int nano2ms = 1000 * 1000;
    private int allocateSize = 4;

    public void relativeReadWriteIntCmp() {
        long start = System.nanoTime();
        ByteBuffer buffer = ByteBuffer.allocate(4 * cnt);
        for (int i = 0; i < cnt; i++) {
            buffer.putInt(i);
        }
        buffer.flip();
        for (int i = 0; i < cnt; i++) {
            buffer.getInt();
        }
        log.info("堆缓冲区读写耗时 {}", (System.nanoTime() - start) / nano2ms);

        start = System.nanoTime();
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(4 * cnt);
        for (int i = 0; i < cnt; i++) {
            buffer2.putInt(i);
        }
        buffer2.flip();
        for (int i = 0; i < cnt; i++) {
            buffer2.getInt();
        }
        log.info("直接缓冲区读写耗时：{}", (System.nanoTime() - start) / nano2ms);
    }


    public void allocateCmp() {
        long start = System.nanoTime();
        for (int i = 0; i < cnt; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(allocateSize);
        }
        log.info("堆缓冲区创建时间：{}", (System.nanoTime() - start) / nano2ms);

        start = System.currentTimeMillis();
        for (int i = 0; i < cnt; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(allocateSize);
        }
        log.info("直接缓冲区创建时间：{}", (System.nanoTime() - start) / nano2ms);
    }

    public static void main(String[] args) {
        DirectBufferVsByteBuffer db = new DirectBufferVsByteBuffer();
        db.relativeReadWriteIntCmp();
        db.allocateCmp();
    }
}
