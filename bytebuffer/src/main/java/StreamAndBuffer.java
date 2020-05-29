import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class StreamAndBuffer {
    String streamWayFile = "stream";
    String bufferWayFile = "buffer";

    /**
     * single way r or w java.io stream
     *
     * @throws IOException
     */
    void streamWay() throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(streamWayFile));
        // write first byte
        outputStream.write(1000);
        // write next
        outputStream.write("content".getBytes());
        outputStream.flush();
        outputStream.close();
        InputStream inputStream = new FileInputStream(new File(streamWayFile));
        // read first
        System.out.println(inputStream.read());
        byte[] bytes = new byte[5];
        // read next
        inputStream.read(bytes);
        System.out.println(new String(bytes));

    }

    /**
     * double way r/w java.nio channel + byteBuffer
     *
     * @throws IOException
     */
    void bufferWay() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(streamWayFile);
        FileOutputStream fileOutputStream = new FileOutputStream(bufferWayFile);
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(1024);

        byteBuffer.put("test".getBytes());
        inChannel.write(byteBuffer);
//        long start = System.currentTimeMillis();
//        while (true) {
//            int eof = inChannel.read(byteBuffer);
//            if (eof == -1) break;
//            byteBuffer.flip();
//            outChannel.write(byteBuffer);
//            byteBuffer.clear();
//        }
//        System.out.println("spending : " + (System.currentTimeMillis() - start));
        inChannel.close();
        outChannel.close();

    }

    public static void main(String[] args) throws Exception {
        new StreamAndBuffer().bufferWay();
    }
}
