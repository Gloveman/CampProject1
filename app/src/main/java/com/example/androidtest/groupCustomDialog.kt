import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.androidtest.R

class groupCustomDialog(context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener:(String)->Unit) {
        this.onClickListener=object:OnDialogClickListener{
            override fun onClicked(groupName: String) {
                listener(groupName)
            }
        }
    }

    fun showDialog() {
        dialog.setContentView(R.layout.group_dialog)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val window = dialog.window
        val layoutParams = WindowManager.LayoutParams().apply {
            copyFrom(window?.attributes)
            width = 1000
            height = 800
        }
        window?.attributes = layoutParams

        val groupEdit = dialog.findViewById<EditText>(R.id.group_edit)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_button)
        val finishButton = dialog.findViewById<Button>(R.id.finish_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        finishButton.setOnClickListener {
            val groupName = groupEdit.text.toString()
            onClickListener.onClicked(groupName)
            dialog.dismiss()
        }

        dialog.show()
    }

    interface OnDialogClickListener {
        fun onClicked(groupName: String)
    }
}
