package com.team12.foodforall.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreatePayment {

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(
            Integer total, String description, String currency, String price,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{

        //Constant variables
        final String intent = "SALE";
        final String method = "Paypal";
        double total_price;

        Amount amount = new Amount();
        amount.setCurrency(currency);
        System.out.println(price);
        double cost = new BigDecimal(Float.parseFloat(price)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        total_price = total*cost;
        amount.setTotal(String.format("%.2f", total_price));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

}
