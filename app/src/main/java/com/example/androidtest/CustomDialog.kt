import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.androidtest.MemoData
import com.example.androidtest.R


class CustomDialog(context: Context)
{
    private val dialog = Dialog(context)
    private lateinit var listener:MyListener
    var Title=""
    var Memo=""



    fun setMemoData(data:MemoData)
    {
        Title=data.title
        Memo=data.memo
    }
    fun getMemoData():MemoData
    {
        return MemoData(Title,Memo)
    }
    fun showDialog()
    {
        dialog.setContentView(R.layout.memo_background)
        val TitleView=dialog.findViewById<EditText>(R.id.topic_edit)
        val MemoView=dialog.findViewById<EditText>(R.id.context_edit)

        if(Title!="")
            TitleView.setText(Title)
        if(Memo!="")
            MemoView.setText(Memo)

        dialog.findViewById<Button>(R.id.finish_button).setOnClickListener {
            listener.onOKClicked(TitleView.text.toString(),MemoView.text.toString())
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.cancel_button).setOnClickListener{dialog.dismiss()}

        dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()


    }
    fun setonOKClickedListner(listner:(String,String)->Unit){
        this.listener=object:MyListener{
            override fun onOKClicked(title: String, memo: String) {
                listner(title,memo)
            }
        }
    }
    interface  MyListener{
        fun onOKClicked(title:String,memo:String)
    }

}