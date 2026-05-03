package com.wilfred.blockchain.blockchain;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;


public class CyptoCurrencyHelper {
    public static String hash(String data) {
        return SHA256Helper.generateHash(data);
    }

    public static byte[] sign(PrivateKey privateKey, String input) {
        try {
            java.security.Signature signature = java.security.Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(input.getBytes());
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifySignature(PublicKey publicKey, String data, byte[] signature) {
        try {
            java.security.Signature sig = java.security.Signature.getInstance("ECDSA", "BC");
            sig.initVerify(publicKey);
            sig.update(data.getBytes());
            return sig.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //generate public key and private key pair
    //private key: 256 bits random integer
    //public key: point on the elliptic curve
    public static KeyPair ellipticCurveCrypto() {
        try {
            java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecGenParameterSpec);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
