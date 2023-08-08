package com.example.testapplication.ui.mainscreen

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.data.models.Device
import com.example.testapplication.databinding.CardDeviceDesignBinding
import com.example.testapplication.utils.transition

class DeviceAdapter(
    var context: Context,
    var deviceList: List<Device>,
    var viewModel: MainScreenViewModel
) : RecyclerView.Adapter<DeviceAdapter.MViewHolder>() {
    inner class MViewHolder(binding: CardDeviceDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CardDeviceDesignBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: CardDeviceDesignBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_device_design, parent, false)
        return MViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val device = deviceList[position]
        val binding = holder.binding

        binding.device = device

        binding.cardLine.setOnLongClickListener {
            val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        viewModel.deleteByDevicePK(deviceList[position].PK_Device)
                        arrayListOf(deviceList).removeAt(position)
                        notifyDataSetChanged()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }
            val builder = AlertDialog.Builder(context, R.style.MyDialogTheme)
            builder.setMessage("Are you sure to delete?")
                .setPositiveButton("Ok", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener)
                .show()
            false
        }

        binding.cardLine.setOnClickListener { view ->
            val transition = MainScreenFragmentDirections.detailTransition(device = device)
            Navigation.transition(view, transition)
        }
    }
}