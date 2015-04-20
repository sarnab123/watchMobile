package com.devtest.firstwearableproject.walletUtil;

import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by global on 4/13/15.
 */
public class WalletUtils {

    public static final String KOHLSCASH_TYPECODE = "kohlscash";

    public static final String OFFER_TYPECODE = "offer";

    public static final String LOYALTY_TYPECODE = "loyalty";

    protected static final Integer AVAILABLE = 9092;

    protected static final Integer PENDING = 9093;

    protected static final Integer REDEEMED = 9094;

    protected static final Integer EXPIRED = 9095;


    public static ArrayList<String> getKohlsCash(WalletResponse walletResponse)
    {


        walletResponse = getWalletResponse(walletResponse);

        List< WalletItemResponse > walletData = walletResponse.getWalletItems();

        ArrayList<String> kohlsCashdata = new ArrayList<>();

        for( WalletItemResponse walletItem : walletData )
        {
            if(walletItem.getTypeCode().equalsIgnoreCase( KOHLSCASH_TYPECODE ))
            {
                String state = null;
                if(walletItem.getCouponState() == AVAILABLE)
                {
                    state = "AVAILABLE";
                }
                else if(walletItem.getCouponState() == PENDING)
                {
                    state = "PENDING";
                }
                else if(walletItem.getCouponState() == EXPIRED)
                {
                    state = "EXPIRED";
                }
                else if(walletItem.getCouponState() == REDEEMED)
                {
                    state = "REDEEMDED";
                }
                String kohlsCash = walletItem.getId() + "," + state+","+walletItem.getValue();
                kohlsCashdata.add(kohlsCash);
            }

        }

        return kohlsCashdata;
    }

    private static WalletResponse getWalletResponse(WalletResponse walletResponse)
    {
        List< WalletItemResponse > walletData = walletResponse.getWalletItems();

        sortWalletData( walletResponse );

        List< WalletItemResponse > available = new ArrayList< WalletItemResponse >();
        List< WalletItemResponse > redeemed = new ArrayList< WalletItemResponse >();
        List< WalletItemResponse > pending = new ArrayList< WalletItemResponse >();
        List< WalletItemResponse > expired = new ArrayList< WalletItemResponse >();
        TimeZone mCentralTimezone = TimeZone.getTimeZone( "US/Central" );
        for( WalletItemResponse walletItem : walletData )
        {
            if( !walletItem.getTypeCode().equalsIgnoreCase( KOHLSCASH_TYPECODE )
                    && !walletItem.getTypeCode().equalsIgnoreCase( LOYALTY_TYPECODE ) )
            {
                continue;
            }
            Calendar currentTime = Calendar.getInstance();

            Calendar startTime = Calendar.getInstance(mCentralTimezone);
            startTime.setTimeInMillis(Long.valueOf( walletItem.getEffectiveStartDate()));

            Calendar endTime = Calendar.getInstance(mCentralTimezone);
            endTime.setTimeInMillis(Long.valueOf( walletItem.getEffectiveEndDate()));
            endTime.set( endTime.get( Calendar.YEAR ), endTime.get( Calendar.MONTH ), endTime.get( Calendar.DAY_OF_MONTH ), 23, 59 );

            if(endTime.getTime().before((currentTime.getTime())))
            {
                walletItem.setCouponState( EXPIRED );
                expired.add( walletItem );
            }
            else if(startTime.getTime().after(currentTime.getTime()) )
            {
                walletItem.setCouponState( PENDING );
                pending.add( walletItem );
            }
            else if( Double.valueOf( walletItem.getValue() ) <= 0 )
            {

                walletItem.setCouponState( REDEEMED );
                redeemed.add( walletItem );
            }
            else
            {
                walletItem.setCouponState( AVAILABLE );
                available.add( walletItem );
            }

        }

        walletData.clear();

        Collections.sort(pending, new Comparator<WalletItemResponse>() {

            @Override
            public int compare(WalletItemResponse lhs, WalletItemResponse rhs) {

                return lhs.getEffectiveStartDate().compareTo(rhs.getEffectiveStartDate());

            }
        });

        walletData.addAll( available );
        walletData.addAll( pending );
        walletData.addAll( redeemed );
        walletData.addAll( expired );

        walletResponse.setWalletItems( walletData );

        return walletResponse;
    }

    protected static void sortWalletData(WalletResponse walletResponse) {
        Collections.sort(walletResponse.getWalletItems(), new Comparator<WalletItemResponse>() {

            @Override
            public int compare(WalletItemResponse lhs,
                               WalletItemResponse rhs) {

                if (lhs.getEffectiveEndDate().compareTo(rhs.getEffectiveEndDate()) != 0) {
                    return lhs.getEffectiveEndDate().compareTo(rhs.getEffectiveEndDate());
                }
                return Double.valueOf(lhs.getValue()) >= (Double.valueOf(rhs.getValue())) ? -1 : 1;

            }
        });

    }


    private static String generatePinWithBarcodeNumber(String barCode) {

        String pin = "";

        if (barCode.length() > 14) {

            String tmpStr1 = barCode.substring(11);

            String tmpStr2 = tmpStr1.substring(0, 3);

            int intVal = Integer.parseInt(tmpStr2);

            int pinIntVal = 9999 - (1000 - intVal) * 7;

            pin = String.format("%d", pinIntVal);

        }

        else {

            pin = "";

        }

        return pin;

    }

    public static ArrayList<String> getKohlsOffers(WalletResponse walletResponse) {
        List< WalletItemResponse > walletData = walletResponse.getWalletItems();

        ArrayList<String> kohlsOffers = new ArrayList<>();

        for(WalletItemResponse response:walletData)
        {
            String offerData = "AVAILABLE";

            String expirationDate, availableDate = "";
            if (response.isShortTermOffer())
                expirationDate =
                        DateUtils.getRelativeTimeSpanString(Long.valueOf(response.getEffectiveEndDate()),
                                Calendar.getInstance().getTimeInMillis(),
                                0)
                                .toString();

            if (response.getCouponState() == EXPIRED ) {
                offerData = "Expired ";

            } else if (response.getCouponState() == REDEEMED) {

                offerData = "Redeemed ";
            }


            offerData = offerData + " Bar Code =" + (response.getId());
            offerData = offerData + " PIN: " + generatePinWithBarcodeNumber(response.getId());

            offerData = offerData+" Use Promo Code: " + response.getPromoCode();

            kohlsOffers.add(offerData);
        }


        return kohlsOffers;

    }
}
