package com.faezolfp.dripcontrol.presentation.tracking

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.utils.FormatPersentase
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityTrackingScreenBinding

class TrackingScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTrackingScreenBinding
    private lateinit var viewModel: TrackingViewModel
    private var dataSave = 0
    private var dataInpustMax = 0
    private var dataInpusRealtime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[TrackingViewModel::class.java]

        setDisplay()
    }

    private fun setDisplay() {
        displayButton()
        displayObserveViewModel()
//        showNotification()
    }

    private fun displayButton() {
        binding.buttonMinus.setOnClickListener(this)
        binding.buttonPlus.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonPlus -> {
                if (dataSave < 150) {
                    dataSave += 10
                    viewModel.setDataTpm(dataSave)
                } else {
                    Toast.makeText(this, "Batas Maksimum", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.buttonMinus -> {
                if (dataSave > 0) {
                    dataSave -= 10
                    viewModel.setDataTpm(dataSave)
                } else {
                    Toast.makeText(this, "Batas Minimum", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun displayObserveViewModel() {
        viewModel.getDataInfusMax.observe(this) { data ->
            if (data != null) {
                dataInpustMax = data.toInt()
                Log.d("TRACKING", dataInpustMax.toString())
            }
        }

        viewModel.getDataTpm.observe(this) { data ->
            if (data != null) {
                dataSave = data.toInt()
                binding.buttonMinus.isEnabled = data.toInt() != 0
                binding.progressBar1.progress = data.toInt()
                binding.txtTpm.text = "$data\nTPM"
            } else {
                dataSave = 0
            }
        }

        viewModel.getDataInfus.observe(this) { data ->
            if (data != null) {
                binding.progressBar2.apply {
                    max = dataInpustMax
                    progress = data.toInt()
                }
                dataInpusRealtime = data.toInt()
                Log.d("TRACKING", "$dataInpustMax $dataInpusRealtime")

                val persentase =
                    FormatPersentase.persentaseRealtime(dataInpustMax, dataInpusRealtime)
                binding.txtDtinfuspersen.text = "$persentase%"
            }
        }
    }

//    private fun showNotification() {
//
//        val mNotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val mBuilder = NotificationCompat.Builder(this, CHANEL_ID)
//            .setSmallIcon(R.drawable.baseline_notifications_24).setLargeIcon(
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.baseline_notification_important_24
//                )
//            ).setContentTitle("Content Tittle")
//            .setContentText("Contet text")
//            .setSubText("Sub Text")
//            .setAutoCancel(true)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            /* Create or update. */
//            val channel = NotificationChannel(
//                CHANEL_ID,
//                CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = CHANNEL_NAME
//            mBuilder.setChannelId(CHANEL_ID)
//            mNotificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = mBuilder.build()
//
//        mNotificationManager.notify(NOTIFICATION_ID, notification)
//    }
//
//    companion object {
//        const val CHANEL_ID = "chanel01"
//        private const val NOTIFICATION_ID = 1
//        private const val CHANNEL_NAME = "dicoding channel"
//    }
}
