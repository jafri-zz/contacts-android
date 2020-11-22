package com.vestrel00.contacts.permissions.data

import com.vestrel00.contacts.ContactsPermissions
import com.vestrel00.contacts.data.Data
import com.vestrel00.contacts.data.DataDelete
import com.vestrel00.contacts.data.DataQuery
import com.vestrel00.contacts.data.DataUpdate
import com.vestrel00.contacts.permissions.requestReadPermission
import com.vestrel00.contacts.permissions.requestWritePermission

/**
 * If [ContactsPermissions.READ_PERMISSION] is not yet granted, suspends the current coroutine,
 * requests for the permission, and then returns a new [DataQuery] instance.
 *
 * If permission is already granted, then immediately returns a new [DataQuery] instance.
 */
suspend fun Data.queryWithPermission(): DataQuery {
    if (!permissions.canQuery()) {
        applicationContext.requestReadPermission()
    }

    return query()
}

/**
 * If [ContactsPermissions.READ_PERMISSION] is not yet granted, suspends the current coroutine,
 * requests for the permission, and then returns a new [DataQuery] instance.
 *
 * If permission is already granted, then immediately returns a new [DataQuery] instance.
 */
suspend fun Data.queryProfileWithPermission(): DataQuery {
    if (!permissions.canQuery()) {
        applicationContext.requestReadPermission()
    }

    return queryProfile()
}

/**
 * If [ContactsPermissions.WRITE_PERMISSION] is not yet  granted, suspends the current coroutine,
 * requests for the permissions, and then returns a new [DataUpdate] instance.
 *
 * If permission is already granted, then immediately returns a new [DataUpdate] instance.
 */
suspend fun Data.updateWithPermission(): DataUpdate {
    if (!permissions.canUpdateDelete()) {
        applicationContext.requestWritePermission()
    }

    return update()
}

/**
 * If [ContactsPermissions.WRITE_PERMISSION] is not yet  granted, suspends the current coroutine,
 * requests for the permissions, and then returns a new [DataUpdate] instance.
 *
 * If permission is already granted, then immediately returns a new [DataUpdate] instance.
 */
suspend fun Data.updateProfileWithPermission(): DataUpdate {
    if (!permissions.canUpdateDelete()) {
        applicationContext.requestWritePermission()
    }

    return updateProfile()
}

/**
 * If [ContactsPermissions.WRITE_PERMISSION] is not yet granted, suspends the current coroutine,
 * requests for the permissions, and then returns a new [DataDelete] instance.
 *
 * If permissions are already granted, then immediately returns a new [DataDelete] instance.
 */
suspend fun Data.deleteWithPermission(): DataDelete {
    if (!permissions.canUpdateDelete()) {
        applicationContext.requestWritePermission()
    }

    return delete()
}

/**
 * If [ContactsPermissions.WRITE_PERMISSION] is not yet granted, suspends the current coroutine,
 * requests for the permissions, and then returns a new [DataDelete] instance.
 *
 * If permissions are already granted, then immediately returns a new [DataDelete] instance.
 */
suspend fun Data.deleteProfileWithPermission(): DataDelete {
    if (!permissions.canUpdateDelete()) {
        applicationContext.requestWritePermission()
    }

    return deleteProfile()
}