
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author icsd11026 Kwnstantinos Giakoumidakis, icsd12207 Matthaios Chouliaras
 */
public class Statheres {

    //hex
    private String pshfia = "0123456789abcdef";

    public static void to_Hex(byte[] data, int mhkos) {
    }

    public static void to_Hex(byte[] data) {
    }

    public static void Hex_2_Bytes(String str) {
    }

    public static SecretKey createKeyForAES(@SuppressWarnings("unused") int bitLength, SecureRandom random) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
        generator.init(256, random);

        return generator.generateKey();
    }

    public static IvParameterSpec createCtrIvForAES(int messageNumber, SecureRandom random) {
        byte[] ivBytes = new byte[16];

        // initially randomize
        random.nextBytes(ivBytes);

        // set the message number bytes
        ivBytes[0] = (byte) (messageNumber >> 24);
        ivBytes[1] = (byte) (messageNumber >> 16);
        ivBytes[2] = (byte) (messageNumber >> 8);
        ivBytes[3] = (byte) (messageNumber >> 0);

        // set the counter bytes to 1
        for (int i = 0; i != 7; i++) {
            ivBytes[8 + i] = 0;
        }

        ivBytes[15] = 1;

        return new IvParameterSpec(ivBytes);
    }
    
    public static void toString(byte[] bytes, int length){ }
    
    public static void toString(byte[] bytes){ }
    
    public static void String_2_Byte(String string){ }

}
