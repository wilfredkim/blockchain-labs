package com.wilfred.blockchain.blockchain;

public class CyptoCurrencyHelper {
    public static  String hash(String data) {
        return SHA256Helper.generateHash(data);
    }
}
