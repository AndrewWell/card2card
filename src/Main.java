import java.io.*;

public class Main {
    static OnlineTransactionPayPayOk onlineTransactionPayPay = new OnlineTransactionPayPayOk("https://pay.pay-ok.org/demo/?REQ");
    static WorkingWithStringPayPayOk workingWithStringPayPayOk;
    static SendFragment sendFragment;

    public static void main(String[] args) throws IOException {
      // System.out.println(onlineTransactionPayPay.sendCheck4Registration("testContacts", "72432", "12351", "Тестовая оплата"));
       //workingWithStringPayPayOk = new WorkingWithStringPayPayOk(onlineTransactionPayPay.getInformationOnCheck(""));
        //System.out.println(onlineTransactionPayPay.getInformationOnCheck("e05acc96-796-741b-a007-30e901fda8fa"));
      /*  System.out.println(workingWithStringPayPayOk.getTINCompany());//ИНН
        System.out.println(workingWithStringPayPayOk.getShiftNumber());//номер смены
        System.out.println(workingWithStringPayPayOk.getCashierDetails());//ФИО кассира
        System.out.println(workingWithStringPayPayOk.getCheckNumber());//номер чека
        System.out.println(workingWithStringPayPayOk.getPaymentDate());//дата оплаты
        System.out.println(workingWithStringPayPayOk.getIDPersonalAccount());//номер л.с.
        System.out.println(workingWithStringPayPayOk.getTaxAuthorityWeb());//сайт ФНС
        System.out.println(workingWithStringPayPayOk.getDeviceRN());//номер фискального аппарата в системе ФНС (РН ККТ)
        System.out.println(workingWithStringPayPayOk.getDeviceSN());//номе фискального аппарата от производителя (ЗН ККТ)
        System.out.println(workingWithStringPayPayOk.getFSNumber());//фискальный номер (ФН)
        System.out.println(workingWithStringPayPayOk.getAmount());// полная сумма транзакции
        System.out.println(workingWithStringPayPayOk.getAmountIPIT());// 20% от транзакции*/
        //TODO провести свадьюу бэка и графики + добавить в пункт Ответа чека кнопку добавления GUI
        sendFragment = new SendFragment();
    }
}