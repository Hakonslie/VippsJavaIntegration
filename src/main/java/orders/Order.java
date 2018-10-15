package orders;

public class Order {
	
	private int amount, dbId, capturedAmount, refundedAmount, mobileNumber;
	private String status, transactionText, vippsId, deepLink;
	
	public String getDeepLink() {
		return deepLink;
	}
	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}
	public String getVippsId() {
		return vippsId;
	}
	public void setVippsId(String vippsId) {
		this.vippsId = vippsId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getDbId() {
		return dbId;
	}
	public void setDbId(int dbId) {
		this.dbId = dbId;
	}
	public int getCapturedAmount() {
		return capturedAmount;
	}
	public void setCapturedAmount(int capturedAmount) {
		this.capturedAmount = capturedAmount;
	}
	public int getRefundedAmount() {
		return refundedAmount;
	}
	public void setRefundedAmount(int refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionText() {
		return transactionText;
	}
	public void setTransactionText(String transactionText) {
		this.transactionText = transactionText;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dbId;
		result = prime * result + mobileNumber;
		result = prime * result + ((vippsId == null) ? 0 : vippsId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (dbId != other.dbId)
			return false;
		if (mobileNumber != other.mobileNumber)
			return false;
		if (vippsId == null) {
			if (other.vippsId != null)
				return false;
		} else if (!vippsId.equals(other.vippsId))
			return false;
		return true;
	}
	
	
	
	
}
