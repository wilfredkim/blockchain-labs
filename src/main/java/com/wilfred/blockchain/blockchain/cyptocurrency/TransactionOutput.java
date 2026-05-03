package com.wilfred.blockchain.blockchain.cyptocurrency;

import com.wilfred.blockchain.blockchain.CyptoCurrencyHelper;
import lombok.Getter;
import lombok.Setter;

import java.security.PublicKey;
@Getter
@Setter
public class TransactionOutput {
    //identifier of the transaction output, also known as the id of the transaction this output was created in
    private String id;
    private PublicKey receiver; // also known as the new owner of these coins.
    private double amount; // the amount of coins they own
    private String parentTransactionId; // the id of the transaction this output was created in

    public TransactionOutput( PublicKey receiver, double amount, String parentTransactionId) {
        this.id = CyptoCurrencyHelper.hash(receiver.toString() + amount + parentTransactionId);
        this.receiver = receiver;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
    }

    // Check if coin belongs to you
    public boolean isMine(String publicKey) {
        return receiver.toString().equals(publicKey);
    }

    @Override
    public String toString() {
        return "TransactionOutput{" +
                "id='" + id + '\'' +
                ", recipient='" + receiver + '\'' +
                ", value=" + amount +
                ", parentTransactionId='" + parentTransactionId + '\'' +
                '}';
    }
}
