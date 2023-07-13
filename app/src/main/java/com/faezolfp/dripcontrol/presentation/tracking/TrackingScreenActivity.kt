package com.faezolfp.dripcontrol.presentation.tracking

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.core.domain.model.Pasiens
import com.faezolfp.dripcontrol.core.service.AlarmNotificationService
import com.faezolfp.dripcontrol.core.service.NotificationBroadcastReceiver
import com.faezolfp.dripcontrol.core.utils.FormatPersentase
import com.faezolfp.dripcontrol.core.utils.ViewModelFactory
import com.faezolfp.dripcontrol.databinding.ActivityTrackingScreenBinding
import com.google.android.gms.dynamic.IFragmentWrapper

class TrackingScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTrackingScreenBinding
    private lateinit var viewModel: TrackingViewModel
    private var dataSave = 0
    private var dataInpustMax = 0
    private var dataInpusRealtime = 0
    private lateinit var dataMove: Pasiens
    private var presentase: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        try {
            dataMove = intent.getParcelableExtra<Pasiens>(DATA_MOVE) as Pasiens
        }catch (e: Exception){
            dataMove = Pasiens(
                id = 0,
                nama = "DEVA MASRESLINA",
                umur = "20",
                brtbadan = "57",
                banyakcairaninfus = "100",
                lamapemberianinfus = "100",
                tetsanpermenit = "60",
                kamar = 1
            )
        }
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[TrackingViewModel::class.java]

        setDisplay()
    }

    private fun setDisplay() {
        displayButton()
        displayObserveViewModel()
        display()
//        showNotification()
    }

    private fun display(){
        val persentase = FormatPersentase.persentaseRealtime(dataInpustMax, dataInpusRealtime)
        if (dataMove != null){

            var dataBB: String? = null
            when (dataMove!!.kamar) {
                1 -> {
                    dataBB = "B1"
                    binding.materialCardView.setCardBackgroundColor(this.resources.getColor(R.color.colorshape1))
                }
                2 -> {
                    dataBB = "B2"
                    binding.materialCardView.setCardBackgroundColor(this.resources.getColor(R.color.colorshape2))
                }
                3 -> {
                    dataBB = "B3"
                    binding.materialCardView.setCardBackgroundColor(this.resources.getColor(R.color.colorshape3))
                }
                else -> {
                    dataBB = "B4"
                    binding.materialCardView.setCardBackgroundColor(this.resources.getColor(R.color.colorshape4))
                }
            }

            binding.apply {
                txtNama.setText(dataMove!!.nama)
                txtUmur.setText("${dataMove!!.umur} Tahun")
                txtBeratbadan.setText("${dataMove!!.brtbadan} KG")
                txtBbb.setText(dataBB)
                txtStatusinfus.setText("${persentase}%")
            }
        }
    }

    private fun displayButton() {
        binding.buttonMinus.setOnClickListener(this)
        binding.buttonPlus.setOnClickListener(this)
        binding.btnSetmakro.setOnClickListener(this)
        binding.btnSetmikro.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonPlus -> {
                if (dataSave < 150) {
                    dataSave += 1
                    viewModel.setDataTpm(dataSave)
                } else {
                    Toast.makeText(this, "Batas Maksimum", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.buttonMinus -> {
                if (dataSave > 0) {
                    dataSave -= 1
                    viewModel.setDataTpm(dataSave)
                } else {
                    Toast.makeText(this, "Batas Minimum", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_setmakro -> {
                viewModel.setDataTpm(20)
            }
            R.id.btn_setmikro -> {
                viewModel.setDataTpm(60)
            }

        }

    }

    private fun displayObserveViewModel() {
        viewModel.getDataInfusMax.observe(this) { datamax ->
            if (datamax != null) {
                dataInpustMax = datamax.toInt()
                Log.d("TRACKING", dataInpustMax.toString())

                viewModel.getDataInfus.observe(this) { data ->
                    if (data != null) {
                        binding.progressBar2.apply {
                            max = datamax
                            progress = data.toInt()
                        }
                        dataInpusRealtime = data.toInt()
                        display()
                        Log.d("TRACKING", "$datamax $dataInpusRealtime")

                        val persentase =
                            FormatPersentase.persentaseRealtime(datamax, dataInpusRealtime)
                        presentase = persentase
                        if (persentase > 1){
                            viewModel.setStatusInfus("LANCAR")
                        }else{
                            viewModel.setStatusInfus("TERSUMBAT")
                        }
                        binding.txtDtinfuspersen.text = "$persentase%"
                        if (persentase <= 20){
                            shownotif()
                        }

                    }
                }

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
        viewModel.getStatusInfus.observe(this){statusInfus ->
            if (statusInfus.isNotEmpty()){
                binding.apply {
                    txtStatus.setText(statusInfus)
                }
            }
        }
    }

    private fun shownotif(){
//        val intent = Intent(this, NotificationBroadcastReceiver::class.java)
//        intent.action = "ACTION_SHOW_NOTIFICATION"
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        pendingIntent.send()
        val serviceIntent = Intent(this, AlarmNotificationService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
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

    companion object{
        const val DATA_MOVE = "data_MOVE"
    }
}
