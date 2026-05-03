package com.wilfred.blockchain.blockchain;

public class Constants {
    public Constants() {
    }
//number of leading zeros
    public static final int DIFFICULTY = 5;
    //reward for mining a block, which is an incentive for miners to participate in the network and secure the blockchain. In a real blockchain, this reward can be a certain amount of cryptocurrency, but here we just use a fixed value for simplicity.
    public static final double REWARD = 6.25;
    public static final int MAX_NONCE = Integer.MAX_VALUE;
//this is the hash of the genesis block, which is the first block in the blockchain. It has no previous block, so we use a string of zeros.
    public static  final  String GENESIS_PREV_HASH = "0000000000000000000000000000000000000000000000000000";
}
