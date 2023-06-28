package cz.mendelu.xadamek5.project.ui.screens.newLog

interface NewLogScreenActions {
    fun saveLog()
    fun onTextChange(description: String)
    fun onDateChange(date: Long)
    fun onDialogConfirm()
    fun onOpenDialogClicked()

    fun onFeelingChange(feeling: Int)
    fun initLog()


}