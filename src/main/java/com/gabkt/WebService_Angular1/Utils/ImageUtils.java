package com.gabkt.WebService_Angular1.Utils;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.util.Base64;

public class ImageUtils {
    // FUNCIONANDO PARCIALMENTE CORRIGIR LOGICA ERRO COM FORMATO WEBP
    public static String detectMimeType(byte[] imgBytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
            String mimeType = URLConnection.guessContentTypeFromStream(bis);
            return mimeType;
        } catch (Exception e) {
            e.printStackTrace();
            return "application/octet-stream";
        }
    }

    public static String convertToBase64(byte[] imgBytes) {
        String mimeType = detectMimeType(imgBytes);
        return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(imgBytes);

    }

    public static byte[] convertToByte(String base64Image) {
        String base64Data = base64Image.split(",")[1];
        return Base64.getDecoder().decode(base64Data);
    }
}
