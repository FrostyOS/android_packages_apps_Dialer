/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.dialer.filterednumber;

import com.google.common.base.Preconditions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.dialer.R;

/**
 * Dialog fragment shown to users when they need to migrate to use
 * {@link android.provider.BlockedNumberContract} for blocking.
 */
public class MigrateBlockedNumbersDialogFragment extends DialogFragment {

    /**
     * Listener for the operation to migrate from
     * {@link com.android.dialer.database.FilteredNumberContract} blocking to
     * {@link android.provider.BlockedNumberContract} blocking.
     */
    public interface Listener {

        /**
         * Callback called when the migration operation is finished.
         */
        void onComplete();
    }

    private Listener mMigrationListener;

    /**
     * Creates a new MigrateBlockedNumbersDialogFragment.
     *
     * @param migrationListener The {@link Listener} to call when the
     * migration is complete.
     * @return the new MigrateBlockedNumbersDialogFragment.
     * @throws NullPointerException if migrationListener is {@code null}.
     */
    public static DialogFragment newInstance(Listener migrationListener) {
        MigrateBlockedNumbersDialogFragment fragment = new MigrateBlockedNumbersDialogFragment();
        fragment.mMigrationListener = Preconditions.checkNotNull(migrationListener);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.migrate_blocked_numbers_dialog_title)
                .setMessage(R.string.migrate_blocked_numbers_dialog_message)
                .setPositiveButton(R.string.migrate_blocked_numbers_dialog_allow_button,
                        newPositiveButtonOnClickListener())
                .setNegativeButton(R.string.migrate_blocked_numbers_dialog_cancel_button, null)
                .create();
    }

    private DialogInterface.OnClickListener newPositiveButtonOnClickListener() {
        return new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO(maxwelb): Perform actual migration, call
                // FilteredNumberCompat#hasMigratedToNewBlocking

                mMigrationListener.onComplete();
            }
        };
    }

    @Override
    public void onPause() {
        // The dialog is dismissed and state is cleaned up onPause, i.e. rotation.
        dismiss();
        mMigrationListener = null;
        super.onPause();
    }
}
