package com.wilfred.blockchain.blockchain.cyptocurrency;

import com.wilfred.blockchain.blockchain.CyptoCurrencyHelper;
import com.wilfred.blockchain.blockchain.blockchain.BlockChain;
import lombok.Getter;
import lombok.Setter;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
@Getter
@Setter
public class Wallet {
    //for signature
    private PrivateKey privateKey;

    //for verification
    private PublicKey publicKey;

    public Wallet() {
    }

    public Wallet(PrivateKey privateKey, PublicKey publicKey) {
        KeyPair keyPair = CyptoCurrencyHelper.ellipticCurveCrypto();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public Transaction transferMoney(PublicKey receiver, double amount){
        if (calculateBalance() < amount) {
            System.out.println("Not enough funds to send transaction. Transaction discarded.");
            return null;
        }
        //create list of inputs
        java.util.List<TransactionInput> inputs = new java.util.ArrayList<>();

        double total = 0;
        for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
            TransactionOutput transactionOutput = item.getValue();
            if (transactionOutput.isMine(publicKey.toString())) {
                total += transactionOutput.getAmount();
                inputs.add(new TransactionInput(transactionOutput.getId()));
                if (total > amount) {
                    break;
                }
            }
        }
        Transaction newTransaction = new Transaction(publicKey, receiver, amount, inputs);
        newTransaction.generateSignature(privateKey);
        for (TransactionInput input : inputs) {
            BlockChain.UTXOs.remove(input.getTransactionOutputId());
        }
        return newTransaction;
    }
    public  double calculateBalance(){
        double total = 0;
        for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
            TransactionOutput transactionOutput = item.getValue();
            if (transactionOutput.isMine(publicKey.toString())) {
                total += transactionOutput.getAmount();
            }
        }
        return total;
    }
}
