/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();

        //genesisBlock.setDifficultyTarget(0x1e0ffff0L);  // this is ok -- fh
        targetTimespan = TARGET_TIMESPAN; // fast diff. adjust in woodcoin, 30 blocks
        interval = INTERVAL;  // -- changed fh
        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        addressHeader = 73;  // was 48, now 73 --- fh
        dumpedPrivateKeyHeader = 128 + addressHeader;
        p2shHeader = 5; // should this be looked at?
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 8338;
        packetMagic = 0xfcd9b7ddL; // was 0xfbc0b6dbL
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1413817324L); // 1413817324  was 1317972665L -- fh
        genesisBlock.setNonce(1591189L); // 1591189  was 2084524493L
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;  // lets check this - what do we do with this?
        spendableCoinbaseDepth = 100; // same with this..
        String genesisHash = genesisBlock.getHashAsString();
	       System.out.println("Checking genesis hash in mainnetparams");
         System.out.println("genesis hash " + genesisHash);
        checkState(genesisHash.equals("30758383eae55ae5c7752b73388c1c85bdfbe930ad25ad877252841ed1e734a4"), genesisHash);

       // checkpoints.put(339143, Sha256Hash.wrap("17f1ef1b7ec9f9b9ae9f4a9edd69a82c578133d3471feadf0b48c8c2819ac048"));
        dnsSeeds = new String[] {
                "dnsseed.stuffseed.org",
                "dnsseed.seedfunk.org"
        };
        httpSeeds = new HttpDiscovery.Details[] {
                // Andreas Schildbach
             /*   new HttpDiscovery.Details(
                        ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
                        URI.create("http://httpseed.bitcoin.schildbach.de/peers")
                )
             */
        };

        addrSeeds = new int[] {
               0xc7e7d7fd, 0x2d37843d, 0x50565541, 0x253b180f, 0x68ec6078, //  0xb5a4b052, 0x21f062d1, 0x72ab89b2, 0x74a45318, 0x8312e6bc, 0xb916965f, 0x8aa7c858, 0xfe7effad,
         };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
	    System.out.println(" Getting MAINNETPARAMS shouldn't do this too many times");
            //log.warn(" Getting MAINNETPARAMS shouldn't do this too many times");
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
