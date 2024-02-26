package com.github.pwrlabs.pwrj.Transaction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.json.JSONObject;
@Getter
@SuperBuilder
public class TransferTxn extends Transaction {

    public static final String type = "Transfer";

    public TransferTxn(JSONObject json) {
        super(json);
    }
}


