package com.wilfred.blockchain.blockchain.cyptocurrency;

import com.wilfred.blockchain.blockchain.CyptoCurrencyHelper;
import com.wilfred.blockchain.blockchain.blockchain.BlockChain;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaction {
    private String transactionId; // this is also the hash of the transaction.
    private PublicKey sender; // senders address/public key.
    private PublicKey receiver; // Recipients address/public key.
    private double amount;
    private byte[] signature; // This is to prevent anybody else from spending funds in our wallet.
    private List<TransactionInput> inputs;
    private List<TransactionOutput> outputs;

    public Transaction(PublicKey sender, PublicKey receiver, double amount, List<TransactionInput> inputs) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.inputs = inputs;
        calculateHash();
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = sender.toString() + receiver.toString() + amount;
        signature = CyptoCurrencyHelper.sign(privateKey, data);
    }

    public boolean verifySignature() {
        String data = sender.toString() + receiver.toString() + amount;
        boolean verifySignature = CyptoCurrencyHelper.verifySignature(sender, data, signature);
        if (!verifySignature) {
            System.out.println("Transaction signature failed to verify");
            return false;
        }
        for (TransactionInput transactionInput : inputs) {
            transactionInput.setUTXO(BlockChain.UTXOs.get(transactionInput.getTransactionOutputId()));
            //send value to recipient
            outputs.add(new TransactionOutput(receiver, amount, transactionId));
            //send the left over 'change' back to sender
            outputs.add(new TransactionOutput(sender, getInputsSum() - amount, transactionId));

            //update the UTXO list by removing transaction inputs and adding transaction outputs
            for (TransactionOutput output : outputs) {
                BlockChain.UTXOs.put(output.getId(), output);
            }
            for (TransactionInput input : inputs) {
                if (input.getUTXO() != null) {
                    BlockChain.UTXOs.remove(input.getUTXO().getId());
                }
            }

        }
        return true;
    }

    public double getInputsSum() {
        double total = 0;
        for (TransactionInput input : inputs) {
            if (input.getUTXO() != null) {
                total += input.getUTXO().getAmount();
            }
        }
        return total;
    }

    public void calculateHash() {
        String data = sender.toString() + receiver.toString() + amount;
        this.transactionId = CyptoCurrencyHelper.hash(data);
    }
}
