package com.wilfred.blockchain.blockchain;

import java.util.List;

public class MerkleRoot {
    private List<String> transactions;

    public MerkleRoot(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getMerkleRoot() {
        return construct(this.transactions).get(0);
    }

    private List<String> construct(List<String> transactions) {
        if (transactions.size() == 1) {
            return transactions;
        }
        List<String> newLevel = new java.util.ArrayList<>();
        for (int i = 0; i < transactions.size(); i += 2) {
            String hash1 = transactions.get(i);
            String hash2 = (i + 1 < transactions.size()) ? transactions.get(i + 1) : hash1;
            newLevel.add(mergeHash(hash1, hash2));
        }
        return construct(newLevel);
    }

    private String mergeHash(String hash1, String hash2) {
        return CyptoCurrencyHelper.hash(hash1 + hash2);
    }
}
