package com.example.tablelayouttest

import android.content.res.Resources.getSystem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataMap = hashMapOf(Pair("Android 9.0", "Pie"), Pair("Android 8.0", "Oreo"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTableLayout()

        addBtn.setOnClickListener {
            if (isVersionValid() && isCodeNameValid() && isVersionAndCodeNameNotExist()) {
                addNewItemRow()
            } else {
                showErrorToast()
            }
        }

    }

    private fun addNewItemRow() {
        dataMap[androidVersionET.text.toString()] = androidCodeNameET.text.toString()
        addNewRaw(androidVersionET.text.toString(), androidCodeNameET.text.toString())
    }


    private fun showErrorToast() {
        val message = when {
            !isVersionValid() -> "Android Version is not valid!"
            !isCodeNameValid() -> "Code Name is not valid!"
            !(isVersionAndCodeNameNotExist()) -> "Version & Code Name already exist!"
            else -> "Something went wrong!"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpTableLayout() {
        dataMap.forEach {
            addNewRaw(it.key, it.value)
        }
    }

    private fun addNewRaw(version: String, codeName: String) {
        val tableRowView = TableRow(this).apply {
            layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT).also {
                it.topMargin = 10
                it.bottomMargin = 10
            }
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_custom))
            addView(
                AppCompatTextView(this@MainActivity).also {
                    it.text = version
                }
            ,0)
            addView(
                AppCompatTextView(this@MainActivity).also {
                    it.text = codeName
                }
            ,1)
        }
        androidTL.addView(tableRowView)
    }

    private fun isVersionValid() = !androidVersionET.text.isNullOrEmpty()

    private fun isCodeNameValid() = !androidCodeNameET.text.isNullOrEmpty()

    private fun isVersionAndCodeNameNotExist(): Boolean =
        !dataMap.containsKey(androidVersionET.text.toString()) &&
                !dataMap.containsValue(androidCodeNameET.text.toString())

}