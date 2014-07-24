/*
 * Copyright (C) 2014 The Android Open Source Project
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

package android.telecomm;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Represents a distinct account, line of service or call placement method that
 * the system can use to place phone calls.
 */
public class PhoneAccountHandle implements Parcelable {
    private ComponentName mComponentName;
    private String mId;

    public PhoneAccountHandle(
            ComponentName componentName,
            String id) {
        mComponentName = componentName;
        mId = id;
    }

    /**
     * The {@code ComponentName} of the {@link android.telecomm.ConnectionService} which is
     * responsible for making phone calls using this {@code PhoneAccountHandle}.
     *
     * @return A suitable {@code ComponentName}.
     */
    public ComponentName getComponentName() {
        return mComponentName;
    }

    /**
     * A unique identifier for this {@code PhoneAccountHandle}, generated by and meaningful to the
     * {@link android.telecomm.ConnectionService} that created it.
     *
     * @return A unique identifier for this {@code PhoneAccountHandle}.
     */
    public String getId() {
        return mId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mComponentName) + Objects.hashCode(mId);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(mComponentName)
                    .append(", ")
                    .append(mId)
                    .toString();
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other instanceof PhoneAccountHandle &&
                Objects.equals(((PhoneAccountHandle) other).getComponentName(),
                        getComponentName()) &&
                Objects.equals(((PhoneAccountHandle) other).getId(), getId());
    }

    //
    // Parcelable implementation.
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(mComponentName, flags);
        out.writeString(mId);
    }

    public static final Creator<PhoneAccountHandle> CREATOR = new Creator<PhoneAccountHandle>() {
        @Override
        public PhoneAccountHandle createFromParcel(Parcel in) {
            return new PhoneAccountHandle(in);
        }

        @Override
        public PhoneAccountHandle[] newArray(int size) {
            return new PhoneAccountHandle[size];
        }
    };

    private PhoneAccountHandle(Parcel in) {
        mComponentName = in.readParcelable(getClass().getClassLoader());
        mId = in.readString();
    }
}