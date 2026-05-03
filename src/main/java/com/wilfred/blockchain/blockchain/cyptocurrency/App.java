package com.wilfred.blockchain.blockchain.cyptocurrency;

import com.wilfred.blockchain.blockchain.Constants;
import com.wilfred.blockchain.blockchain.CyptoCurrencyHelper;
import com.wilfred.blockchain.blockchain.Miner;
import com.wilfred.blockchain.blockchain.blockchain.Block;
import com.wilfred.blockchain.blockchain.blockchain.BlockChain;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.Security;
import java.util.Base64;

public class App {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        KeyPair keyPair = CyptoCurrencyHelper.ellipticCurveCrypto();
        System.out.println("Public Key: " + keyPair.getPublic());
        System.out.println("Private Key: " + keyPair.getPrivate());
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        Wallet userA = new Wallet();
        Wallet userB = new Wallet();
        Wallet lender = new Wallet();
        BlockChain blockChain = new BlockChain();
        Miner miner = new Miner();
        //lender mines the genesis block and gets rewarded
        Transaction genesisTransaction = new Transaction(lender.getPublicKey(), userA.getPublicKey(), 500, null);
        genesisTransaction.generateSignature(lender.getPrivateKey());
        genesisTransaction.setTransactionId("0");
        genesisTransaction.getOutputs().add(new TransactionOutput(genesisTransaction.getReceiver(), genesisTransaction.getAmount(), genesisTransaction.getTransactionId()));
        BlockChain.UTXOs.put(genesisTransaction.getOutputs().get(0).getId(), genesisTransaction.getOutputs().get(0));
        System.out.println("Creating and Mining Genesis block... ");
        Block genesis = new Block( Constants.GENESIS_PREV_HASH);
        genesis.addTransaction(genesisTransaction);
        miner.mine(genesis, blockChain);

        Block block1= new Block(genesis.getHash());
        block1.addTransaction(userA.transferMoney(userB.getPublicKey(), 40));
        miner.mine(block1, blockChain);
        Block block2 = new Block(block1.getHash());
        block2.addTransaction(userA.transferMoney(userB.getPublicKey(), 40));
        miner.mine(block2, blockChain);


        System.out.println("Miners Reward" +miner.getReward());



    }
}
