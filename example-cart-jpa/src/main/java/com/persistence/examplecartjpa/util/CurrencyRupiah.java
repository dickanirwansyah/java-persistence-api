package com.persistence.examplecartjpa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CurrencyRupiah {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRupiah.class);

    public static DecimalFormat rpCurrency(double currency){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        kursIndonesia.format(currency);
        log.info("CURRENCY : "+kursIndonesia.format(currency));
        return kursIndonesia;
    }
}
