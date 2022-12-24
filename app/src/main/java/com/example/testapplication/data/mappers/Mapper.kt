package com.example.testapplication.data.mappers

import com.example.testapplication.data.database.modules.BinNumberDb
import com.example.testapplication.domain.modules.database.BinNumber

class Mapper {


    private fun binDbToUi(db: BinNumberDb): BinNumber =  BinNumber(
        bin = db.bin
    )

    fun binUiToDb(ui: BinNumber): BinNumberDb = BinNumberDb(
        bin = ui.bin
    )

    fun listBinDbToUi(listBinDb: List<BinNumberDb>) =
        listBinDb.map {
            binDbToUi(it)
        }

}