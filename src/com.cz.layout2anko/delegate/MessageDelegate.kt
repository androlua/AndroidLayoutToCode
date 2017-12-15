package com.cz.layout2anko.delegate

import com.intellij.notification.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint

/**
 * Created by cz on 2017/12/13.
 */
object MessageDelegate {
    val GROUP_DISPLAY = "Layout2Anko"
    val GROUP_DISPLAY_LOG = "Layout2Anko LOG"
    val GROUP_DISPLAY_ID_INFO = NotificationGroup(GROUP_DISPLAY, NotificationDisplayType.BALLOON, true)
    val GROUP_DISPLAY_ID_LOG = NotificationGroup(GROUP_DISPLAY_LOG, NotificationDisplayType.NONE, true)

    fun onPluginExceptionHandled(exception: KotlinPluginException) {
        showMessage(exception.message, exception.header)
    }

    fun logEventMessage(message: String) {
        val notification = GROUP_DISPLAY_ID_LOG.createNotification(message, NotificationType.INFORMATION)
        sendNotification(notification)
    }

    fun showSuccessMessage() {
        val notification = GROUP_DISPLAY_ID_INFO.createNotification("Generation Success!", NotificationType.INFORMATION)
        sendNotification(notification)
    }

    private fun sendNotification(notification: Notification) {
        ApplicationManager.getApplication().invokeLater {
            val projects = ProjectManager.getInstance().openProjects
            Notifications.Bus.notify(notification, projects[0])
        }
    }

    fun showMessage(message: String?, header: String?=null) {
        Messages.showDialog(message, header, arrayOf("ok"), -1, null)
    }


    /**
     * Display simple notification - error

     * @param project
     * *
     * @param text
     */
    fun showErrorNotification(project: Project, text: String) {
        showNotification(project, MessageType.ERROR, text)
    }

    /**
     * Display simple notification of given type

     * @param project
     * *
     * @param type
     * *
     * @param text
     */
    fun showNotification(project: Project, type: MessageType, text: String) {
        val statusBar = WindowManager.getInstance().getStatusBar(project)

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.component), Balloon.Position.atRight)
    }


}
