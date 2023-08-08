package com.example.bars

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bars.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        //Functions
        setOnClickListener()
        registerForContextMenu(binding.contextMenuTryText)

    }


    private fun setOnClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this@MainActivity, "Navigation Menu Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnSnackbar.setOnClickListener {

            showSnackBar(this, it, resources, "Undo the Deleted item", "Undo") {
                Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPopUp.setOnClickListener {
            showPopUpMenu(it)
        }
    }

    private fun showPopUpMenu(view: View){
        val popupMenu = PopupMenu(this,view)
        popupMenu.inflate(R.menu.context_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.actionDelete -> {
                    Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
                }

                R.id.actionHistory -> {
                    Toast.makeText(this@MainActivity, "History", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu, menu)
        val isLightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_NO
        if (isLightMode) {
            if (menu != null) {
                for (i in 0 until menu.size()) {
                    val menuItem = menu.getItem(i)
                    val spanString = SpannableString(menuItem.title)
                    spanString.setSpan(ForegroundColorSpan(Color.BLACK), 0, spanString.length, 0)
                    menuItem.title = spanString
                }
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.add -> {
                Toast.makeText(this@MainActivity, "Add Clicked", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.notification ->{
                Toast.makeText(this@MainActivity, "Notification clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.setHeaderTitle("Select Action")
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionDelete -> {
//                Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.actionHistory -> {
                Toast.makeText(this@MainActivity, "History", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)

    }
}





