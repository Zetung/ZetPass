package com.zetung.android.zetpass

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.viewpager2.widget.ViewPager2
import com.zetung.android.zetpass.R.id.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException


class MainActivity : AppCompatActivity(), InterfaceCoordinator {

    private lateinit var menuItemsShow: Array<MenuItem>
    private lateinit var addMenuItemShow: MenuItem
    private var dataList:ArrayList<DataRecord>? = null

    private companion object{
        const val RQ_PERMISSION_STORAGE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(toolbar))
        //supportActionBar?.title = ""
//        val pager: ViewPager2 = findViewById(R.id.pager)
//        val pageAdapter = PageAdapter(this)
//        pager.adapter = pageAdapter
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back24)

        supportFragmentManager.setFragmentResultListener("requestNewRecord",this){
                _, bundle ->
            dataList?.add(bundle.getParcelable("newRecord")!!)
        }

        makePermissionToLoad()
        start()
    }

    private fun parseFile(){
        try {
            val parser = XmlPullParserHandler()
            //val istream = assets.open("DataRecordsPass.xml")
            val istream = FileInputStream(
                File(android.os.Environment.getExternalStorageDirectory(),"DataRecordsPass.xml"))
            dataList = parser.parse(istream)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {

        makePermissionToSave()
        super.onDestroy()
    }

    private fun makePermissionToLoad(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)==
            PackageManager.PERMISSION_GRANTED) {
            parseFile()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
                RQ_PERMISSION_STORAGE)
        }
    }

    private fun makePermissionToSave(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==
            PackageManager.PERMISSION_GRANTED) {
            saveRecordsXml()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
                RQ_PERMISSION_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            RQ_PERMISSION_STORAGE -> {
                if(grantResults.all {it == PackageManager.PERMISSION_GRANTED}){
                    parseFile()
                }
            }
        }
    }

    private fun saveRecordsXml(){
        val xmlSaver = XmlSaver("DataRecordsPass.xml")
        val error = xmlSaver.saveAllRecord(dataList)
        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        if (menu != null) {
            menuItemsShow = arrayOf(
                menu.findItem(delete_setting),
                menu.findItem(add_setting))
            addMenuItemShow = menu.findItem(done_setting)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            add_setting -> startRedactFragment()
            delete_setting -> makePermissionToSave()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        startMainFragment()
        return super.onSupportNavigateUp()
    }

    override fun start() {
        startEnterFragment()
    }

    override fun startEnterFragment() {
        supportActionBar?.hide()
        supportFragmentManager
            .beginTransaction()
            .replace(container,EnterFragment())
            .commit()
    }

    override fun startMainFragment() {

        supportActionBar?.show()
        for(item in menuItemsShow){
            item.isVisible = true
        }

        addMenuItemShow.isVisible = false
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = "Ваши записи"


        val binder = bundleOf("DataRecordSession" to dataList)
        val mainFragment = MainFragment()
        mainFragment.arguments = binder

        supportFragmentManager
            .beginTransaction()
            .replace(container,mainFragment)
            .commit()
    }

    override fun startRedactFragment() {
        supportActionBar?.show()
        for(item in menuItemsShow){
            item.isVisible = false
        }

        addMenuItemShow.isVisible = true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Редактирование..."

        supportFragmentManager
            .beginTransaction()
            .replace(container,RedactFragment())
            .commit()
    }

}