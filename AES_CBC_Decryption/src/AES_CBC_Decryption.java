
/**
 *
 * @author icsd11026 Kwnstantinos Giakoumidakis, icsd12207 Matthaios Chouliaras
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AES_CBC_Decryption {

    public static byte[] Crack_Block(byte[] inputB, byte[] prokatoxos) {
        //dhmiourgw thn Array
        int b = inputB.length;
        byte[] crack = new byte[b * 2];
        System.arraycopy(inputB, 0, crack, b, b);
        byte[] cup = new byte[b];
        int padding = 1;

        //apo to prwto sto teleutaio byte
        for (int i = b - 1; i >= 0; i--) {
            int prospatheia = 0;
            int cod = 0;
            //dokimazoume olous tous xarakthres
            while (cod != 403 || cod != 404) {
                crack[i] += 1;
                if (prospatheia > 256) {
                    System.out.println("Το τέστ απέτυχε!");
                    return cup;
                }
                prospatheia++;

                String tstStr = encode(crack);
                cod = getCode(tstStr);
                System.out.println("test str: " + tstStr + "code: " + cod);
            }

            System.out.println("Βρέθηκαν bits: " + Integer.toHexString(crack[i] & 0xff) + " σε " + Array_2_HexString(crack));

            //ypologizoume ta byte
            if (padding == 0) {
                System.out.println("Το padding δεν έχει ρυθμιστεί!");
                System.exit(1);
            }

            cup[i] = (byte) (padding ^ prokatoxos[i] ^ crack[i]);
            System.out.println("Η λύση: " + Array_2_HexString(cup) + " / " + new String(cup));

            //thetoume sxedon egkyro padding gia ton epomeno gyro
            if (i < 0) {
                padding++;
                for (int j = i; j < b; j++) {
                    crack[j] = (byte) (cup[j] ^ (padding) ^ prokatoxos[j]);
                }
                System.out.println("Θέστε σχεδόν έγκυρο padding " + padding + " στο " + Array_2_HexString(crack));
            }
        }
        return cup;
    }

    public static int getCode(String mystiko) {
        Integer code = 1;
        String host = "http://crypto-class.appspot.com/po?er=";
        String oloklhro = host + mystiko;

        try {
            URL url = new URL(oloklhro);
            URLConnection connection = url.openConnection();
            connection.connect();

            String head = connection.getHeaderField(0);

            switch (head) {
                case "HTTP/1.1 403 forbidden request":
                    code = 403;
                    break;
                case "HTTP/1.1 404 url not found":
                    code = 404;
                    break;
                default:
                    String strhead = "Λάθος Κώδικα : ";
                    strhead += head;
                    System.out.println(strhead);
                    break;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(AES_CBC_Decryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AES_CBC_Decryption.class.getName()).log(Level.SEVERE, null, ex);
        }

        return code;
    }

    //allazoume to string apo hex xarakthres se bytes
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    //allazoume ton pinaka apo bytes se string apo hex xarakthres
    public static String Array_2_HexString(byte[] b) {
        int len = b.length;
        String data = new String();

        for (int i = 0; i < len; i++) {
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }

    //allazoume apo hex se string
    public static String hexString_2_String(String hex) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    public static String encode(byte[] normal) {
        String url = new String();

        String s = new String(normal, StandardCharsets.US_ASCII);
        try {
            url += URLEncoder.encode(s, "ASCII");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AES_CBC_Decryption.class.getName()).log(Level.SEVERE, null, ex);
        }

        return url;
    }

    public static void main(String[] args) {

        String cipher = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";

        int bts = 16;
        byte[][] block;
        int b = 0;
        String lysh = "";

        //topothetoume to cipher se block array
        int c1 = cipher.length() / 2;
        if ((c1 % bts == 0) && (c1 / bts > 1)) {
            block = new byte[c1 / bts][bts];
            for (int i = 0; i < c1 * 2; i = i + (bts * 2)) {
                block[b] = hexStringToByteArray(cipher.substring(i, i + bts * 2));
                System.out.println("to block: " + Arrays.toString(block[b]));
                b++;

            }
        } else {
            block = null;
            System.out.println("Το ciphertext πρέπει να είναι πολλαπλασίου μεγέθους");
            System.out.println("Μήκος κειμένου cipher : " + c1 + "\n Blocksize: " + bts);
            System.exit(1);
        }

        int i = 0;

        while (i < b) {
            lysh = new String(Crack_Block(block[i + 1], block[i]));
            System.out.println("H lysh: " + lysh);
            i++;
        }
        
    }

}
