package com.wilfred.blockchain.blockchain;

public class Miner {
    private double reward;

    //it takes some time to find a valid hash!!
    //PoW
    public void mine(Block block, BlockChain blockChain) {
        while (!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }
        System.out.println(block + " mined successfully!");
        System.out.println("Block mined: " + block.getHash());
        blockChain.addBlock(block);
        this.reward += Constants.REWARD;
    }

    private boolean isGoldenHash(Block block) {
        String hash = block.getHash();
        String leadingZeros = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
        return hash.substring(0, Constants.DIFFICULTY).equals(leadingZeros);
    }

    public double getReward() {
        return this.reward;
    }
}
