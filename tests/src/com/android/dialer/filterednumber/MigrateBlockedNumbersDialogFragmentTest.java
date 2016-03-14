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

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.dialer.filterednumber.MigrateBlockedNumbersDialogFragment.Listener;


/**
 * Unit tests for {@link MigrateBlockedNumbersDialogFragment}
 */
@SmallTest
public class MigrateBlockedNumbersDialogFragmentTest extends AndroidTestCase {

    public void testNewInstance_NullMigrationListener() {
        try {
            MigrateBlockedNumbersDialogFragment.newInstance(null);
            fail();
        } catch (NullPointerException e) {}
    }

    public void testNewInstance_WithListener() {
        assertNotNull(MigrateBlockedNumbersDialogFragment.newInstance(
                new Listener() {
                    @Override
                    public void onComplete() {}
                }));
    }
}
