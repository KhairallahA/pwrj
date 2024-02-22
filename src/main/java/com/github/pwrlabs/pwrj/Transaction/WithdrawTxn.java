package com.github.pwrlabs.pwrj.Transaction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.json.JSONObject;

@Getter
@SuperBuilder
public class WithdrawTxn extends Transaction {
    private final String validator;
    private final long shares;

    public WithdrawTxn(JSONObject json) {
        super(json);
        this.validator = json.optString("0x");
        this.shares = json.optLong("shares", 0);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject txn = super.toJSON();
        txn.put("validator", validator);
        txn.put("shares", shares);
        return txn;
    }

}
