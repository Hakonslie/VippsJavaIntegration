package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionTransactionInfoJSON {

@SerializedName("amount")
@Expose
private Integer amount;
@SerializedName("transactionText")
@Expose
private String transactionText;

public Integer getAmount() {
return amount;
}

public void setAmount(Integer amount) {
this.amount = amount;
}

public String getTransactionText() {
return transactionText;
}

public void setTransactionText(String transactionText) {
this.transactionText = transactionText;
}

}