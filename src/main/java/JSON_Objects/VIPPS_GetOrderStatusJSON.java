package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_GetOrderStatusJSON {

@SerializedName("orderId")
@Expose
private String orderId;
@SerializedName("transactionInfo")
@Expose
private VIPPS_TransactionInfoJSON transactionInfo;

public String getOrderId() {
return orderId;
}

public void setOrderId(String orderId) {
this.orderId = orderId;
}

public VIPPS_TransactionInfoJSON getTransactionInfo() {
return transactionInfo;
}

public void setTransactionInfo(VIPPS_TransactionInfoJSON transactionInfo) {
this.transactionInfo = transactionInfo;
}

}
