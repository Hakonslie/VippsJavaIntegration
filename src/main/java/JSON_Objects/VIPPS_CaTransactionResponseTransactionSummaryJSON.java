package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionResponseTransactionSummaryJSON {

@SerializedName("capturedAmount")
@Expose
private Integer capturedAmount;
@SerializedName("remainingAmountToCapture")
@Expose
private Integer remainingAmountToCapture;
@SerializedName("refundedAmount")
@Expose
private Integer refundedAmount;
@SerializedName("remainingAmountToRefund")
@Expose
private Integer remainingAmountToRefund;

public Integer getCapturedAmount() {
return capturedAmount;
}

public void setCapturedAmount(Integer capturedAmount) {
this.capturedAmount = capturedAmount;
}

public Integer getRemainingAmountToCapture() {
return remainingAmountToCapture;
}

public void setRemainingAmountToCapture(Integer remainingAmountToCapture) {
this.remainingAmountToCapture = remainingAmountToCapture;
}

public Integer getRefundedAmount() {
return refundedAmount;
}

public void setRefundedAmount(Integer refundedAmount) {
this.refundedAmount = refundedAmount;
}

public Integer getRemainingAmountToRefund() {
return remainingAmountToRefund;
}

public void setRemainingAmountToRefund(Integer remainingAmountToRefund) {
this.remainingAmountToRefund = remainingAmountToRefund;
}

}