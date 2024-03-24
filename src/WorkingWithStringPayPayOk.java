import java.text.DecimalFormat;

public class WorkingWithStringPayPayOk {
    private String sourceString;
    private String[] answer;

    public WorkingWithStringPayPayOk(String sourceString) {
        this.sourceString = sourceString;
        answer = new String[28];
        splitSourceString();
    }

    public void getSplitString() {
        for (int j = 0; j < answer.length; j++) {
            System.out.println("[" + j + "] = " + answer[j]);
        }
    }

    private String getStringWithNumber() {
        return extractValueFromString(answer[4], 1, 0).split(";")[4];
    }

    private void splitSourceString() {
        int i = 0;
        for (String temp : sourceString.split(",")) {
            answer[i] = temp;
            i++;
        }
    }
    public boolean checkError(){
        if (answer.length>3)return false;
        return true;
    }

    /**
     * @return check payment date
     */
    public String getPaymentDate() {
        return extractValueFromString(answer[1], 2, 1);
    }

    private String extractValueFromString(String str, int firstParameter, int secondParameter) {
        return str.substring(str.indexOf(":") + firstParameter, str.length() - secondParameter);
    }

    /**
     * @return personal account number
     */
    public String getIDPersonalAccount() {
        return extractValueFromString(answer[2], 2, 1);
    }

    /**
     * @return details of the cashier who punched the check
     */
    public String getCashierDetails() {
        return extractValueFromString(answer[3], 2, 1);
    }

    /**
     * @return taxpayer ID of a legal entity
     */
    public String getTINCompany() {
        String tmp = extractValueFromString(answer[19], 2, 1);
        return tmp.substring(1, tmp.length() - 1);
    }

    /**
     * @return tax authority website
     */
    public String getTaxAuthorityWeb() {
        String tmp = extractValueFromString(answer[18], 2, 1);
        return tmp.substring(1, tmp.length() - 1);
    }

    /**
     * @return check number according to GUID
     */
    public String getCheckNumber() {
        return extractValueFromString(answer[22], 1, 0);
    }

    /**
     * @return shift number in which the transaction was carried out
     */
    public String getShiftNumber() {
        return extractValueFromString(answer[21], 1, 0);
    }

    /**
     * @return registration number of cash register equipment
     */
    public String getDeviceRN() {
        return extractValueFromString(answer[13], 3, 2);
    }

    /**
     * @return unique serial number of the cash register itself
     */
    public String getDeviceSN() {
        return extractValueFromString(answer[12], 3, 2);
    }

    /**
     * @return fiscal number
     */
    public String getFSNumber() {
        return extractValueFromString(answer[14], 3, 2);
    }

    /**
     * @return transaction amounts
     */
    public float getAmount() {
        return Float.parseFloat(extractValueFromString(getStringWithNumber(), 1, 3));
    }

    /**
     * @return amount including personal income tax
     */
    public float getAmountIPIT() {
        return getAmount() / 100 * 20;
    }

    /**
     * @return name of the company that provides transaction services
     */
    public String getNameCompany(){
        return extractValueFromString(answer[15], 3, 2);
    }
}
