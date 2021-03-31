package lyon.pop.chat.bar

import android.os.Bundle
import android.os.Handler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {
    lateinit var pop :PopMessage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        pop = PopMessage(this)

        pop.setBackgroundColor(this?.resources?.getColor(R.color.text_gray_block)!!)
        pop.setColor(this?.resources?.getColor(R.color.white)!!)
        pop.setName("注意")
        pop.setIntroduction("這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，這是Lyon 寫的泡泡框 顯示物件！方便程式設計師在不想使用Dialog時使用，")
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            pop.setViewLocation(view)
            pop.show()
            Handler().postDelayed({
                pop.dismiss()
            }, 30*1000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}