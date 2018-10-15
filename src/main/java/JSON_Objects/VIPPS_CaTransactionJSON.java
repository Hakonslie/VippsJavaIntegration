package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionJSON {

@SerializedName("merchantInfo")
@Expose
private VIPPS_CaTransactionMerchantInfoJSON merchantInfo;
@SerializedName("transaction")
@Expose
private VIPPS_CaTransactionTransactionInfoJSON transaction;

public VIPPS_CaTransactionMerchantInfoJSON getMerchantInfo() {
return merchantInfo;
}

public void setMerchantInfo(VIPPS_CaTransactionMerchantInfoJSON merchantInfo) {
this.merchantInfo = merchantInfo;
}

public VIPPS_CaTransactionTransactionInfoJSON getTransaction() {
return transaction;
}

public void setTransaction(VIPPS_CaTransactionTransactionInfoJSON transaction) {
this.transaction = transaction;
}

}