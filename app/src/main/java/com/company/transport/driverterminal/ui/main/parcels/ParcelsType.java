package com.company.transport.driverterminal.ui.main.parcels;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.company.transport.driverterminal.ui.main.parcels.ParcelsType.COMPLETED;
import static com.company.transport.driverterminal.ui.main.parcels.ParcelsType.INCOMING;

@IntDef({INCOMING, COMPLETED})
@Retention(RetentionPolicy.SOURCE)
public @interface ParcelsType {
    public static final int INCOMING = 0;
    public static final int COMPLETED = 1;
};
