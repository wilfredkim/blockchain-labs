package com.wilfred.blockchain.blockchain.blockchain;

import com.wilfred.blockchain.blockchain.Constants;
import com.wilfred.blockchain.blockchain.SHA256Helper;
import com.wilfred.blockchain.blockchain.cyptocurrency.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private int id;
    private int nonce;
    private long timestamp;
    private String hash;
    private String previousHash;
    private String transaction;
    private List<Transaction> transactions;

    public Block(String previousHash) {
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        generateHash();

    }

    public Block(int id, String previousHash, String transaction) {
        this.id = id;
        this.previousHash = previousHash;
        this.transaction = transaction;
        this.timestamp = new Date().getTime();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = this.id + this.previousHash +
                this.timestamp + this.nonce + this.transaction;
        this.hash = SHA256Helper.generateHash(dataToHash);
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (!previousHash.equals(Constants.GENESIS_PREV_HASH)) {
            if (!transaction.verifySignature()) {
                System.out.println("Transaction is not valid ...");
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
