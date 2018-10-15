package JSON_Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VIPPS_CaTransactionMerchantInfoJSON {

@SerializedName("merchantSerialNumber")
@Expose
private String merchantSerialNumber;

public String getMerchantSerialNumber() {
return merchantSerialNumber;
}

public void setMerchantSerialNumber(String merchantSerialNumber) {
this.merchantSerialNumber = merchantSerialNumber;
}

}