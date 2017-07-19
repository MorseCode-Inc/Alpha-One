package com.morsecodeinc.web.helper;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;

import java.text.NumberFormat;


/**
 * Methods in this class are made available to the view.
 *
 * Notes:
 * The syntax in jade looks like a static call to the method on the class.
 * The docs say that the class name is lowercased in the jade language.
 *
 * Created by morsecode on 7/18/2017.
 */
@JadeHelper
public class Util {

    private static NumberFormat money= NumberFormat.getCurrencyInstance();
    static {
        money.setMaximumFractionDigits(2);
    }

    public String formatMoney(double number) {
        return money.format(number);
    }

}
