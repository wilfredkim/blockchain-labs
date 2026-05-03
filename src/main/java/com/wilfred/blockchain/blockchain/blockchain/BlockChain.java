package com.wilfred.blockchain.blockchain.blockchain;

import com.wilfred.blockchain.blockchain.cyptocurrency.Transaction;
import com.wilfred.blockchain.blockchain.cyptocurrency.TransactionOutput;

import java.util.*;

public class BlockChain {
    //immutable!
    private static List<Block> blockChain;
    //we store every unspent transactions!!
    public static Map<String, TransactionOutput> UTXOs; //list of all unspent transactions.

    public BlockChain() {
        BlockChain.UTXOs = new HashMap<>();
        BlockChain.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        this.blockChain.add(block);
    }

    public List<Block> getBlockChain() {
        return this.blockChain;
    }

    public int siZe() {
        return this.blockChain.size();
    }

    public Block getLastBlock() {
        if (this.blockChain.isEmpty()) {
            return null;
        }
        return this.blockChain.get(this.blockChain.size() - 1);
    }


    @Override
    public String toString() {
        String s = "";
        for (Block block : this.blockChain) {
            s += block.toString() + "\n";
        }
        return s;
    }
}
