package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionResponseJSON {

@SerializedName("orderId")
@Expose
private String orderId;
@SerializedName("transactionInfo")
@Expose
private VIPPS_CaTransactionResponseTransactionInfoJSON transactionInfo;
@SerializedName("transactionSummary") 
@Expose
private VIPPS_CaTransactionResponseTransactionSummaryJSON transactionSummary;

public String getOrderId() {
return orderId;
}

public void setOrderId(String orderId) {
this.orderId = orderId;
}

public VIPPS_CaTransactionResponseTransactionInfoJSON getTransactionInfo() {
return transactionInfo;
}

public void setTransactionInfo(VIPPS_CaTransactionResponseTransactionInfoJSON transactionInfo) {
this.transactionInfo = transactionInfo;
}

public VIPPS_CaTransactionResponseTransactionSummaryJSON getTransactionSummary() {
return transactionSummary;
}

public void setTransactionSummary(VIPPS_CaTransactionResponseTransactionSummaryJSON transactionSummary) {
this.transactionSummary = transactionSummary;
}

}