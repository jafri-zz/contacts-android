package com.vestrel00.contacts.entities.operation

import android.content.ContentProviderOperation
import android.content.ContentProviderOperation.newUpdate
import com.vestrel00.contacts.Fields
import com.vestrel00.contacts.entities.MutableOptions
import com.vestrel00.contacts.entities.table.Table
import com.vestrel00.contacts.equalTo

/*
 * Note that changes to the options of a RawContact may affect the options of the parent Contact.
 * On the other hand, changes to the options of the parent Contact will be propagated to all child
 * RawContact options.
 */
internal class OptionsOperation {

    fun updateContactOptions(contactId: Long, options: MutableOptions): ContentProviderOperation =
        newUpdate(Table.CONTACTS.uri)
            .withSelection("${Fields.Id equalTo contactId}", null)
            .withOptions(options)
            .build()

    fun updateRawContactOptions(
        rawContactId: Long, options: MutableOptions
    ): ContentProviderOperation = newUpdate(Table.RAW_CONTACTS.uri)
        .withSelection("${Fields.Id equalTo rawContactId}", null)
        .withOptions(options)
        .build()
}

private fun ContentProviderOperation.Builder.withOptions(options: MutableOptions)
        : ContentProviderOperation.Builder =
    withValue(Fields.Options.Starred, options.starred.toSqlValue())
        .withValue(Fields.Options.TimesContacted, options.timesContacted ?: 0)
        .withValue(Fields.Options.LastTimeContacted, options.lastTimeContacted?.time ?: 0)
        .withValue(Fields.Options.CustomRingtone, options.customRingtone.toString())
        .withValue(Fields.Options.SendToVoicemail, options.sendToVoicemail.toSqlValue())

private fun Boolean?.toSqlValue(): Int {
    if (this != null) {
        return if (this) 1 else 0
    }
    return 0
}