package com.zetung.android.zetpass

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlPullParserHandler {

    private val dataRecordsList = ArrayList<DataRecord>()
    private var dataRecord: DataRecord? = null
    private var text: String? = null

    fun parse(inputStream: InputStream):ArrayList<DataRecord> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream,null)
            var eventType = parser.eventType
            while(eventType != XmlPullParser.END_DOCUMENT){
                val tagname = parser.name
                when(eventType){
                    XmlPullParser.START_TAG -> if (tagname.equals("record", ignoreCase = true)) {
                        dataRecord = DataRecord("","","")
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("record", ignoreCase = true)) {
                        dataRecord?.let { dataRecordsList.add(it) }
                    } else if (tagname.equals("app", ignoreCase = true)) {
                        dataRecord!!.app = text.toString()
                    } else if (tagname.equals("login", ignoreCase = true)) {
                        dataRecord!!.login = text.toString()
                    } else if (tagname.equals("pass", ignoreCase = true)) {
                        dataRecord!!.pass = text.toString()
                    }

                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return dataRecordsList
    }


}