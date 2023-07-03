import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.EditText
import com.example.androidtest.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog(context: Context)
{
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(androidx.core.R.layout.custom_dialog)
        dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val edit_name = binding.nameEdit


        dialog.cancel_button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.finish_button.setOnClickListener {
            onClickListener.onClicked(edit_name.text.toString())
            dialog.dismiss()
        }

    }

    interface OnDialogClickListener
    {
        fun onClicked(name: String)
    }

}