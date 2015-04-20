package com.devtest.firstwearableproject.walletUtil;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.lang.ref.WeakReference;

/**
 * Created by global on 4/12/15.
 */
public class BarcodeCreator {

    private static final int WHITE = 0xFFFFFFFF;
    public static final int BLACK = 0xFF000000;
    public static final int GREY = 0xFFCCCCCC;

    // public static final int GREY = 0xFF8DAAB0;

    public WeakReference<Bitmap> renderBarcode(String barCodeNo, int width, int height, int color)
    {

        // barcode data
        if(TextUtils.isEmpty(barCodeNo))
            barCodeNo = "1427 79789 529 029";
        // barcode image

        return encodeAsBitmap(barCodeNo, BarcodeFormat.CODE_128, width, height, color);

    }


    public WeakReference<Bitmap> renderQRcode(String QRCode, int width, int height, int color)
    {

        // barcode data
        if(TextUtils.isEmpty(QRCode))
            QRCode = "1427 79789 529 029";
        // barcode image

        return encodeQRCodeAsBitmap(QRCode, BarcodeFormat.QR_CODE, width, height, color);

    }

    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     *
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     * http://stackoverflow.com/questions/11697001/android-zxing-get-barcode-image
     */

    private WeakReference< Bitmap > encodeAsBitmap( String barCodeNo, BarcodeFormat code128, int width, int height, int color ) {
        com.google.zxing.Writer c9 = new Code128Writer();
        try {
            BitMatrix bm = c9.encode(barCodeNo,BarcodeFormat.CODE_128, width, height);
            WeakReference<Bitmap> barcode  =  new WeakReference<Bitmap>(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888));

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    barcode.get().setPixel(i, j, bm.get(i, j) ? color : WHITE);
                }
            }
            return barcode;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**************************************************************
     * getting from com.google.zxing.client.android.encode.QRCodeEncoder
     *
     * See the sites below
     * http://code.google.com/p/zxing/
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
     * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
     * http://stackoverflow.com/questions/11697001/android-zxing-get-barcode-image
     */

    private WeakReference< Bitmap > encodeQRCodeAsBitmap( String barCodeNo, BarcodeFormat qrCode, int width, int height, int color ) {
        com.google.zxing.Writer c9 = new QRCodeWriter();
        try {
            BitMatrix bm = c9.encode(barCodeNo,BarcodeFormat.QR_CODE, width, height);
            WeakReference<Bitmap> barcode  =  new WeakReference<Bitmap>(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888));

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    barcode.get().setPixel(i, j, bm.get(i, j) ? color : WHITE);
                }
            }
            return barcode;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
