package com.github.pwrlabs.pwrj.record.transaction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONArray;
import org.json.JSONObject;

@SuperBuilder
@Getter
public class SetConduitsTransactions extends Transaction {
    public static final String type = "Set Conduits";

    private final long vmId;
    private final String[] conduits;

    public SetConduitsTransactions(JSONObject json, long blockNumber, long timestamp, int positionInTheBlock) {
        super(json, blockNumber, timestamp, positionInTheBlock);
        this.vmId = json.optLong("vmId", 0);
        this.conduits = json.getJSONArray("conduits").toList().toArray(new String[0]);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject Transaction = super.toJSON();
        Transaction.put("vmId", vmId);

        JSONArray conduits = new JSONArray();
        for(String c : this.conduits) {
            conduits.put(c);
        }

        Transaction.put("conduits", conduits);
        return Transaction;
    }

}
