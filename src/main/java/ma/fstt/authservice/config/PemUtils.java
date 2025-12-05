package ma.fstt.authservice.config;

import java.util.Base64;

public class PemUtils {

    public static byte[] parsePrivateKey(String pem) {
        return parsePem(pem, "PRIVATE KEY");
    }

    public static byte[] parsePublicKey(String pem) {
        return parsePem(pem, "PUBLIC KEY");
    }

    private static byte[] parsePem(String pem, String type) {
        String header = "-----BEGIN " + type + "-----";
        String footer = "-----END " + type + "-----";
        pem = pem.replace(header, "").replace(footer, "").replaceAll("\\s", "");
        return Base64.getDecoder().decode(pem);
    }
}
