package com.github.pwrlabs.pwrj.record.transaction;

import com.github.pwrlabs.pwrj.Utils.Hex;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.json.JSONObject;

@Getter
@SuperBuilder
public class Transaction {
    public byte chainId;
    private final boolean hasError;
    private final int nonce, size, positionInTheBlock;
    private final String type, sender, receiver, hash, errorMessage;
    private final long timestamp, value, blockNumber, fee, extraFee;
    private final byte[] rawTransaction;

    public Transaction(JSONObject json, long blockNumber, long timestamp, int positionInTheBlock) {
        this.size = json.optInt("size", 0);
        this.positionInTheBlock = positionInTheBlock;
        this.fee = json.optLong("fee", 0L);
        this.extraFee = json.optLong("extraFee", 0L);
        this.type = json.optString("type", "unknown");
        this.sender = json.optString("sender", "0x");
        this.receiver = json.optString("receiver", "0x");
        this.nonce = json.optInt("nonce", 0);
        this.hash = json.optString("hash", "0x");
        this.blockNumber = blockNumber;
        this.timestamp = timestamp;
        this.value = json.optLong("value", 0);
        this.rawTransaction = Hex.decode(json.optString("rawTransaction", ""));
        this.chainId = (byte) json.optInt("chainId", 0);
        this.hasError = !json.optBoolean("success", true);
        this.errorMessage = json.optString("errorMessage", null);
    }

    public boolean hasError() {
        return hasError;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("size", size);
        json.put("positionInTheBlock", positionInTheBlock);
        json.put("fee", fee);
        json.put("extraFee", extraFee);
        json.put("type", type);
        json.put("sender", sender);
        json.put("receiver", receiver);
        json.put("nonce", nonce);
        json.put("hash", hash);
        json.put("blockNumber", blockNumber);
        json.put("nonce", nonce);
        json.put("timestamp", timestamp);
        json.put("value", value);
        json.put("rawTransaction", Hex.toHexString(rawTransaction));
        json.put("chainId", chainId);
        json.put("success", !hasError);
        json.put("errorMessage", errorMessage);

        return json;
    }

    public static Transaction fromJSON(JSONObject json, long blockNumber, long timestamp, int positionInTheBlock) {
        String TransactionType = json.optString("type", "Unknown");

        if (TransactionType.equalsIgnoreCase(TransferTransaction.type)) {
            return new TransferTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(VmDataTransaction.type)) {
            return new VmDataTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(DelegateTransaction.type)) {
            return new DelegateTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(WithdrawTransaction.type)) {
            return new WithdrawTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(JoinTransaction.type)) {
            return new JoinTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ClaimVmIdTransaction.type)) {
            return new ClaimVmIdTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(SetGuardianTransaction.type)) {
            return new SetGuardianTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(PayableVmDataTransaction.type)) {
            return new PayableVmDataTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(GuardianApprovalTransaction.type)) {
            return new GuardianApprovalTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ConduitApprovalTransaction.type)) {
            return new ConduitApprovalTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(RemoveGuardianTransaction.type)) {
            return new RemoveGuardianTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ClaimSpotTransaction.type)) {
            return new ClaimSpotTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(SetConduitsTransactions.type)) {
            return new SetConduitsTransactions(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(AddConduitsTransaction.type)) {
            return new AddConduitsTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(RemoveGuardianTransaction.type)) {
            return new RemoveGuardianTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(MoveStakeTransaction.type)) {
            return new MoveStakeTransaction(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeEarlyWithdrawPenaltyProposalTxn.type)) {
            return new ChangeEarlyWithdrawPenaltyProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeFeePerByteProposalTxn.type)) {
            return new ChangeFeePerByteProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeMaxBlockSizeProposalTxn.type)) {
            return new ChangeMaxBlockSizeProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeMaxTxnSizeProposalTxn.type)) {
            return new ChangeMaxTxnSizeProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeOverallBurnPercentageProposalTxn.type)) {
            return new ChangeOverallBurnPercentageProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeRewardPerYearProposalTxn.type)) {
            return new ChangeRewardPerYearProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeValidatorCountLimitProposalTxn.type)) {
            return new ChangeValidatorCountLimitProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeValidatorJoiningFeeProposalTxn.type)) {
            return new ChangeValidatorJoiningFeeProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeVmIdClaimingFeeProposalTxn.type)) {
            return new ChangeVmIdClaimingFeeProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(ChangeVmOwnerTxnFeeShareProposalTxn.type)) {
            return new ChangeVmOwnerTxnFeeShareProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(OtherProposalTxn.type)) {
            return new OtherProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        } else if (TransactionType.equalsIgnoreCase(VoteOnProposalTxn.type)) {
            return new VoteOnProposalTxn(json, blockNumber, timestamp, positionInTheBlock);
        }
        else {
            return new Transaction(json, blockNumber, timestamp, positionInTheBlock);
        }
    }

    public static void main(String[] args) {
        Hex.decode("");
    }

}
