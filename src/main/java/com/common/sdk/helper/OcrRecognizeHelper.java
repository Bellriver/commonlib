package com.common.sdk.helper;

import android.content.Context;
import android.content.ServiceConnection;

/**
 * Created by jiangjiang.zhong on 2018/11/19.
 * Description:
 * compile 'com.google.android.gms:play-services-vision:16.2.0'
 */

public class OcrRecognizeHelper {
    private String TAG = this.getClass().getSimpleName();
    private ServiceConnection mScreenshotConnection;

//
//    /**
//     * It is recommended to call on the non-main thread
//     * @param context
//     * @param bitmap
//     * @return
//     */
//    public List<TextBlock> startRecognize(Context context,@NonNull Bitmap bitmap) {
//        List<TextBlock> textBlocks;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        int quality = 100;
//        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
//        try {
//            if (!textRecognizer.isOperational()) {
//                Toast.makeText(context,"Text recognizer could not be set up on your device",Toast.LENGTH_SHORT).show();
//                return null;
//            }
//
//            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//            SparseArray<TextBlock> origTextBlocks = textRecognizer.detect(frame);
//            textBlocks = new ArrayList<>();
//            for (int i = 0; i < origTextBlocks.size(); i++) {
//                TextBlock textBlock = origTextBlocks.valueAt(i);
//                textBlocks.add(textBlock);
//            }
//            Collections.sort(textBlocks, new Comparator<TextBlock>() {
//                @Override
//                public int compare(TextBlock o1, TextBlock o2) {
//                    int diffOfTops = o1.getBoundingBox().top - o2.getBoundingBox().top;
//                    int diffOfLefts = o1.getBoundingBox().left - o2.getBoundingBox().left;
//                    if (diffOfTops != 0) {
//                        return diffOfTops;
//                    }
//                    return diffOfLefts;
//                }
//            });
//        } finally {
//            textRecognizer.release();
//        }
//        return textBlocks;
//    }
//
//    public StringBuilder parseLineStr(List<TextBlock> textBlocks) {
//        StringBuilder detectedText = new StringBuilder();
//        for (TextBlock textBlock : textBlocks) {
//            if (textBlock != null && textBlock.getValue() != null) {
//                detectedText.append(textBlock.getValue());
//                detectedText.append("\n");
//            }
//        }
//        return detectedText;
//    }
}
