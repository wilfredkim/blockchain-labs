package com.wilfred.blockchain.blockchain.cyptocurrency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionInput {
    private String transactionOutputId; // Reference to TransactionOutputs -> transactionId
    private TransactionOutput UTXO; // Contains the Unspent transaction output


    public TransactionInput(String id) {
    }
}
