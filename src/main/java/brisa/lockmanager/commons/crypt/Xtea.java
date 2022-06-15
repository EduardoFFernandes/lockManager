package brisa.lockmanager.commons.crypt;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class Xtea {

    private static final int DELTA      = 0x9E3779B9;
    private static final int ROUNDS     = 32;
    public  static final int KEY_SIZE   = 16;
    private static final int BLOCK_SIZE = 8;
    private static final int INT_SIZE   = 4; // 4 bytes

    private final int[] key;

    // ---------------------------------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------------------------------
    public Xtea(final String key) {

        final byte[] keyBytes = key.getBytes(StandardCharsets.US_ASCII);
        if (keyBytes.length != KEY_SIZE) {
            throw new IllegalArgumentException("The XTEA key size must be 16 bytes");
        }
        this.key = this.toIntArrayLittleEndian(keyBytes);
    }

    // ---------------------------------------------------------------------------------------------
    // Public Methods
    // ---------------------------------------------------------------------------------------------
    public String encrypt(final String clearMessage) {

        final byte[] messageBytes = clearMessage.getBytes(StandardCharsets.US_ASCII);
        final byte[] encryptedBytes = this.encrypt(messageBytes);
        return DatatypeConverter.printHexBinary(encryptedBytes);
    }

    public String decrypt(final String hexCipherMessage) {

        final byte[] messageBytes = DatatypeConverter.parseHexBinary(hexCipherMessage);
        final byte[] decryptedBytes = this.decrypt(messageBytes);
        return new String(decryptedBytes, StandardCharsets.US_ASCII).trim();
    }

    public byte[] encrypt(final byte[] message) {

        final byte[] xteaMessage = this.fixXteaMessageSize(message);

        final int[] buffer = this.toIntArrayLittleEndian(xteaMessage);

        for (int readPos = 0; readPos < xteaMessage.length / INT_SIZE; readPos += 2) {
            int v0 = buffer[readPos];
            int v1 = buffer[readPos + 1];
            int sum = 0;

            for (int i = 0; i < ROUNDS; i++) {

                v0 += (v1 << 4 ^ v1 >>> 5) + v1 ^ sum + this.key[sum & 3];
                sum += DELTA;
                v1 += (v0 << 4 ^ v0 >>> 5) + v0 ^ sum + this.key[sum >>> 11 & 3];
            }
            buffer[readPos] = v0;
            buffer[readPos + 1] = v1;
        }
        return this.toByteArrayLittleEndian(buffer);
    }

    public byte[] decrypt(final byte[] message) {

        final byte[] xteaMessage = this.fixXteaMessageSize(message);

        final int[] buffer = this.toIntArrayLittleEndian(xteaMessage);

        for (int readPos = 0; readPos < xteaMessage.length / INT_SIZE; readPos += 2) {
            int v0 = buffer[readPos];
            int v1 = buffer[readPos + 1];
            int sum = DELTA * ROUNDS;

            for (int i = 0; i < ROUNDS; i++) {

                v1 -= (v0 << 4 ^ v0 >>> 5) + v0 ^ sum + this.key[sum >>> 11 & 3];
                sum -= DELTA;
                v0 -= (v1 << 4 ^ v1 >>> 5) + v1 ^ sum + this.key[sum & 3];
            }
            buffer[readPos] = v0;
            buffer[readPos + 1] = v1;
        }
        return this.toByteArrayLittleEndian(buffer);
    }

    // ---------------------------------------------------------------------------------------------
    // Private Methods
    // ---------------------------------------------------------------------------------------------
    private byte[] fixXteaMessageSize(final byte[] message) {

        int messageSize = message.length;
        int remainder = messageSize % BLOCK_SIZE;
        while (remainder != 0) {
            messageSize++;
            remainder = messageSize % BLOCK_SIZE;
        }
        return Arrays.copyOf(message, messageSize);
    }

    private int[] toIntArrayLittleEndian(final byte[] byteArray) {

        final int size = byteArray.length / INT_SIZE;
        final ByteBuffer byteBuffer = ByteBuffer.allocate(size * INT_SIZE);
        byteBuffer.put(byteArray);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        final int[] result = new int[size];
        ((Buffer)byteBuffer).rewind();
        while (byteBuffer.remaining() > 0) {
            result[byteBuffer.position() / INT_SIZE] = byteBuffer.getInt();
        }
        return result;
    }

    private byte[] toByteArrayLittleEndian(final int[] intArray) {

        final ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * INT_SIZE);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        final IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(intArray);
        return byteBuffer.array();
    }
}