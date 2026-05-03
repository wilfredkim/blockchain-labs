package com.wilfred.blockchain.blockchain;

import com.wilfred.blockchain.blockchain.blockchain.Block;
import com.wilfred.blockchain.blockchain.blockchain.BlockChain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BlockChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockChainApplication.class, args);
        BlockChain blockChain = new BlockChain();
        Miner miner = new Miner();

        //create blocks one by one!!!
        Block block0 = new Block(0, Constants.GENESIS_PREV_HASH, "");
        miner.mine(block0, blockChain);

        Block block1 = new Block(1, blockChain.getLastBlock().getHash(), "Alice pays Bob 10 coins");
        miner.mine(block1, blockChain);

        Block block2 = new Block(2, blockChain.getLastBlock().getHash(), "Bob pays Charlie 5 coins");
        miner.mine(block2, blockChain);

        Block block3 = new Block(3, blockChain.getLastBlock().getHash(), "Charlie pays Dave 2 coins");
        miner.mine(block3, blockChain);
        System.out.println(":::: Blockchain: \n" + blockChain);
        System.out.println(":::::::Miner's reward!!!!!!!!: " + miner.getReward());

        //merkle root calling!!
        List<String> transactions = List.of("Alice pays Bob 10 coins", "Bob pays Charlie 5 coins", "Charlie pays Dave 2 coins");
        MerkleRoot merkleRoot = new MerkleRoot(transactions);
        System.out.println("Merkle Root!!!!!!!!!!!!!!!!!!!!!!: " + merkleRoot.getMerkleRoot());

    }

}
