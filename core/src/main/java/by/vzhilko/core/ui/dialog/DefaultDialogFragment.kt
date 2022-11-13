package by.vzhilko.core.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import by.vzhilko.core.R

open class DefaultDialogFragment : DialogFragment() {

    protected open val model: DefaultDialogModel? = null
    protected open val onPositiveButtonClickAction: (() -> Unit)? = null
    protected open val onNegativeButtonClickAction: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(model?.title ?: getString(R.string.app_name))
            .setMessage(model?.message ?: "")
            .setPositiveButton(
                model?.positiveButtonText ?: getString(R.string.ok_caption)
            ) { dialog, which -> onPositiveButtonClickAction?.invoke() }
            .setNegativeButton(
                model?.negativeButtonText ?: getString(R.string.no_caption)
            ) { dialog, which -> onNegativeButtonClickAction?.invoke() }
            .create()
    }

}