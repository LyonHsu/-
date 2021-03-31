package lyon.pop.chat.bar

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Runnable

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    lateinit var pop :PopMessage
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pop = PopMessage(context)

        pop.setBackgroundColor(context?.resources?.getColor(R.color.text_gray_block)!!)
        pop.setColor(context?.resources?.getColor(R.color.white)!!)
        pop.setName("注意2")
        pop.setIntroduction("這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，")
        button_first.setOnClickListener {
            pop.setViewLocation(button_first)
             pop.show()
            Handler().postDelayed({
                pop.dismiss()
            }, 30*1000)
        }

    }
}