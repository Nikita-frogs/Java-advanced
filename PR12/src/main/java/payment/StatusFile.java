package payment;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class StatusFile {

    public static void createFileWithZeros(File file, int nBytes) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(nBytes);
        }
    }


    public static void updateStatus(File file, int index, byte status) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
             FileChannel channel = raf.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1);
            buffer.put(status);

            buffer.flip();

            channel.write(buffer, index);
        }
    }

    public static byte readStatus(File file, int index) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1);

            channel.read(buffer, index);

            buffer.flip();

            return buffer.get();
        }
    }
}
