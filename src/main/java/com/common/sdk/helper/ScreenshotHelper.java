package com.common.sdk.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;


/**
 * 
 * Description: Capture device screens in real time using the media projection API
 */

public class ScreenshotHelper {
    private final String TAG = this.getClass().getSimpleName();
    public static ScreenshotHelper instance;
    private final WindowManager mWindowManager;
    private final ImageReader mImageReader;
    private final Context mContext;
    private final MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private final int mScreenDensity;
    private int mResultCode = 0;
    private Intent mResultData = null;
    private VirtualDisplay mVirtualDisplay;
    private final int mWindowWidth;
    private final int mWindowHeight;

    public static ScreenshotHelper getInstance(Context con) {
        if (instance == null) {
            instance = new ScreenshotHelper(con);
        }
        return instance;
    }

    private ScreenshotHelper(Context context) {
        this.mContext = context.getApplicationContext();
        mMediaProjectionManager = (MediaProjectionManager) mContext.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWindowWidth = mWindowManager.getDefaultDisplay().getWidth();
        mWindowHeight = mWindowManager.getDefaultDisplay().getHeight();
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenDensity = metrics.densityDpi;
        mImageReader = ImageReader.newInstance(mWindowWidth, mWindowHeight + getNavigationBarHeight(context), 0x1, 2); //ImageFormat.RGB_565
        Log.i(TAG, "prepared the virtual environment");
    }

    /**
     * Get the screenshot intent
     *
     * @param activity
     * @param requestCode
     */
    public void creatScreenCaptureIntent(Activity activity, int requestCode) {
        activity.startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), requestCode);
    }

    /**
     * Set the screen capture intentions, by calling the startActivityForResult (mMediaProjectionManager. CreateScreenCaptureIntent (), REQUEST_MEDIA_PROJECTION);To obtain
     * you need to set the intent before calling the screenshot
     *
     * @param resultCode
     * @param data
     */
    public void setResult(int resultCode,  Intent data) {
        this.mResultCode = resultCode;
        this.mResultData = data;
    }

    public int getResultCode() {
        return mResultCode;
    }

    public Intent getIntentData() {
        return mResultData;
    }

    /**
     * Start the screenshots
     *
     * @return
     */
    public Bitmap screenshot() {
        startVirtual();
        return startCapture();
    }

    /**
     * Create media projection and virtual display
     */
    public void startVirtual() {
        if (mMediaProjection != null) {
            Log.i(TAG, "want to display virtual");
            virtualDisplay();
        } else {
            Log.i(TAG, "start screen capture intent");
            Log.i(TAG, "want to build mediaprojection and display virtual");
            setUpMediaProjection();
            virtualDisplay();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpMediaProjection() {
        Log.i(TAG, "setUpMediaProjection  mResultCode = " + mResultCode + " mResultData" + mResultData);
        if (mMediaProjectionManager != null && mResultCode != 0 && mResultData != null) {
            mMediaProjection = mMediaProjectionManager.getMediaProjection(mResultCode, mResultData);
        }
        Log.i(TAG, "mMediaProjection = " + mMediaProjection);
        Log.i(TAG, "mMediaProjection defined");
    }

    /**
     * virtual displayed
     */
    private void virtualDisplay() {
        if (mMediaProjection != null) {
            mVirtualDisplay = mMediaProjection.createVirtualDisplay("screen-mirror",
                    mWindowWidth, mWindowHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    mImageReader.getSurface(), null, null);
            Log.i(TAG, "virtual displayed");
        }
    }

    /**
     * Converts recorded screen content into images
     */
    public Bitmap startCapture() {
        if (mMediaProjection == null) {
            return null;
        }
        Image image = mImageReader.acquireLatestImage();
        int width = image.getWidth();
        int height = image.getHeight();
        final Image.Plane[] planes = image.getPlanes();
        final ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        int rowStride = planes[0].getRowStride();
        int rowPadding = rowStride - pixelStride * width;
        Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height );  //按范围截取图片
        image.close();
        Log.i(TAG, "image data captured");

        //Write local
//        final Bitmap finalBitmap = bitmap;
//        new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    if( finalBitmap != null) {
//                        try{
//                            File fileImage = new File(getLocalPath());
//                            if(!fileImage.exists()){
//                                fileImage.createNewFile();
//                                Log.i(TAG, "image file created");
//                            }
//                            FileOutputStream out = new FileOutputStream(fileImage);
//                            if(out != null){
//                                finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//                                out.flush();
//                                out.close();
//                                //发送广播刷新图库
//                                Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                                Uri contentUri = Uri.fromFile(fileImage);
//                                media.setData(contentUri);
//                                mContext.sendBroadcast(media);
//                                Log.i(TAG, "screen image saved");
//                            }
//                        }catch(Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
        return bitmap;
    }

    /**
     * Stops projection.
     */
    public void tearDownMediaProjection() {
        if (mMediaProjection != null) {
            mMediaProjection.stop();
            mMediaProjection = null;
        }
        Log.i(TAG, "mMediaProjection undefined");
    }

    /**
     * virtual display stopped
     */
    public void stopVirtual() {
        if (mVirtualDisplay == null) {
            return;
        }
        mVirtualDisplay.release();
        mVirtualDisplay = null;
        Log.i(TAG, "virtual display stopped");
    }

    /**
     * 获取底部虚拟按键的高度
     *
     * @param context
     * @return
     */
    public int getNavigationBarHeight(Context context) {
        int height = 0;
        try {
            int resourceId = context.getResources().getIdentifier("navigation_bar_height",
                    "dimen", "android");
            //获取NavigationBar的高度
            height = context.getResources().getDimensionPixelSize(resourceId);
            Log.d("getScreenHeight", "height = " + height );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return height;
    }

    /**
     * Local file path
     *
     * @return
     */
    private String getLocalPath() {
        // Get the built-in SD card path
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        String strDate = dateFormat.format(new java.util.Date());
        String sdCardPath = Environment.getExternalStorageDirectory() + File.separator + "/translate/";
        File dirPath = new File(sdCardPath);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        // Image file path
        String filePath = "screenshot_" + sdCardPath + strDate + ".png";
        return filePath;
    }
}
