package com.wilfred.blockchain.blockchain;

import java.util.LinkedList;
import java.util.List;

public class BlockChain {
    //immutable!
    private List<Block> blockChain;

    public BlockChain() {
        this.blockChain = new LinkedList<>();
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
