package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_TransactionInfoJSON {

@SerializedName("amount")
@Expose
private Integer amount;
@SerializedName("status")
@Expose
private String status;
@SerializedName("transactionId")
@Expose
private String transactionId;
@SerializedName("timeStamp")
@Expose
private String timeStamp;

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getTransactionId() {
return transactionId;
}

public void setTransactionId(String transactionId) {
this.transactionId = transactionId;
}

public String getTimeStamp() {
return timeStamp;
}

public void setTimeStamp(String timeStamp) {
this.timeStamp = timeStamp;
}

}