package com.vestrel00.contacts.debug

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract

fun Context.logRawContactsTable() {
    if (!hasReadPermission()) {
        log("#### RawContacts table - read contacts permission not granted")
        return
    }

    log("#### RawContacts table")

    logRawContactsTable(ContactsContract.RawContacts.CONTENT_URI)
}

internal fun Context.logRawContactsTable(contentUri: Uri) {
    val cursor = contentResolver.query(
        contentUri,
        arrayOf(
            ContactsContract.RawContacts._ID,
            ContactsContract.RawContacts.CONTACT_ID,
            ContactsContract.RawContacts.ACCOUNT_NAME,
            ContactsContract.RawContacts.ACCOUNT_TYPE,
            ContactsContract.RawContacts.STARRED,
            ContactsContract.RawContacts.TIMES_CONTACTED,
            ContactsContract.RawContacts.LAST_TIME_CONTACTED,
            ContactsContract.RawContacts.CUSTOM_RINGTONE,
            ContactsContract.RawContacts.SEND_TO_VOICEMAIL
        ),
        null,
        null,
        null
    )

    cursor ?: return

    while (cursor.moveToNext()) {
        // Use getString instead of getLong, getInt, etc so that the value could be null.
        val id = cursor.getString(0)
        val contactId = cursor.getString(1)
        val name = cursor.getString(2)
        val type = cursor.getString(3)

        val starred = cursor.getString(4)
        val timesContacted = cursor.getString(5)
        val lastTimeContacted = cursor.getString(6)
        val customRingtone = cursor.getString(7)
        val sendToVoicemail = cursor.getString(8)

        log(
            """
                RawContact id: $id, contactId: $contactId, accountName: $name, accountType: $type,
                 starred: $starred, timesContacted: $timesContacted, 
                 lastTimeContacted: $lastTimeContacted, customRingtone: $customRingtone, 
                 sendToVoicemail: $sendToVoicemail
            """.trimIndent().replace("\n", "")
        )
    }

    cursor.close()
}