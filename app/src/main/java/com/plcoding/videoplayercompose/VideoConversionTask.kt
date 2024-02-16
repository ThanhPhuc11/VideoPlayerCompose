package com.plcoding.videoplayercompose

import android.os.Environment
import android.util.Log
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object VideoConversionTask {

    suspend fun convertVideo() = withContext(Dispatchers.Default) {
        val inputPath = Environment.getExternalStorageDirectory().absolutePath + "/input.ts"
        val outputPath = Environment.getExternalStorageDirectory().absolutePath + "/output.mp4"

        // Lệnh FFmpeg để chuyển đổi từ .ts sang .mp4
        val cmd = arrayOf(
            "-i",
            inputPath,
            "-c",
            "copy",
            outputPath
        )

        // Thực hiện lệnh FFmpeg
        val rc = FFmpegKit.execute("-i $inputPath -c:v mpeg4 $outputPath")

        if (ReturnCode.isSuccess(rc.returnCode)) {
            Log.i("FFmpeg", "Chuyển đổi thành công")
        } else {
            Log.e("FFmpeg", "Chuyển đổi thất bại. Mã trả về: $rc")
        }
    }
}