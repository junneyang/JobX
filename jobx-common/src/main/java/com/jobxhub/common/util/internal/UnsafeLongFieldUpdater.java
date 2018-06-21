/*
 * Copyright (c) 2015 The JobX Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jobxhub.common.util.internal;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeLongFieldUpdater<U> {
    private final long offset;
    private final Unsafe unsafe;

    UnsafeLongFieldUpdater(Unsafe unsafe, Class<? super U> tClass, String fieldName) throws NoSuchFieldException {
        Field field = tClass.getDeclaredField(fieldName);
        if (unsafe == null) {
            throw new NullPointerException("unsafe");
        }
        this.unsafe = unsafe;
        offset = unsafe.objectFieldOffset(field);
    }

    public void set(U obj, long newValue) {
        unsafe.putLong(obj, offset, newValue);
    }

    public long get(U obj) {
        return unsafe.getLong(obj, offset);
    }
}