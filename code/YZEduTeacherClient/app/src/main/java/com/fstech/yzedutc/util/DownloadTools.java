package com.fstech.yzedutc.util;


import java.io.File;

public class DownloadTools {
    public static int downloadImg(final String fileurl) {
        return new FileDownloader().downloadFile("yzedu/images/", fileurl,
                Constant.BASE_IMG_URL + "?myfile=" + fileurl);
    }

    public static int downloadVideo(final String fileurl) {
        return new FileDownloader().downloadFile("yzedu/videos/", fileurl,
                Constant.BASE_VIDEO_URL + "?myfile=" + fileurl);
    }

    public static int downloadFile(final String fileurl) {
        return new FileDownloader().downloadFile("yzedu/others/", fileurl,
                Constant.BASE_FILE_URL + "?myfile=" + fileurl);
    }


}
