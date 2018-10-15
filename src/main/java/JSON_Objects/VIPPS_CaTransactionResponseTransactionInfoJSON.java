package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionResponseTransactionInfoJSON {

@SerializedName("amount")
@Expose
private Integer amount;
@SerializedName("timeStamp")
@Expose
private String timeStamp;
@SerializedName("transactionText")
@Expose
private String transactionText;
@SerializedName("status")
@Expose
private String status;
@SerializedName("transactionId")
@Expose
private String transactionId;

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

public String getTimeStamp() {
return timeStamp;
}

public void setTimeStamp(String timeStamp) {
this.timeStamp = timeStamp;
}

public String getTransactionText() {
return transactionText;
}

public void setTransactionText(String transactionText) {
this.transactionText = transactionText;
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

}
