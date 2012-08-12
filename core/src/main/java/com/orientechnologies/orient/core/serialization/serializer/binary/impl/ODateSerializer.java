/*
 * Copyright 2010-2012 Luca Garulli (l.garulli--at--orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orientechnologies.orient.core.serialization.serializer.binary.impl;

import java.util.Calendar;
import java.util.Date;

import com.orientechnologies.common.types.OBinarySerializer;

/**
 * Serializer for {@link com.orientechnologies.orient.core.metadata.schema.OType#DATE} .
 * 
 * @author ibershadskiy <a href="mailto:ibersh20@gmail.com">Ilya Bershadskiy</a>
 * @since 20.01.12
 */
public class ODateSerializer implements OBinarySerializer<Date> {

  public static ODateSerializer INSTANCE = new ODateSerializer();
  public static final byte      ID       = 4;

  public int getObjectSize(Date object) {
    return OLongSerializer.LONG_SIZE;
  }

  public void serialize(Date object, byte[] stream, int startPosition) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(object);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    ODateTimeSerializer dateTimeSerializer = ODateTimeSerializer.INSTANCE;
    dateTimeSerializer.serialize(calendar.getTime(), stream, startPosition);
  }

  public Date deserialize(byte[] stream, int startPosition) {
    ODateTimeSerializer dateTimeSerializer = ODateTimeSerializer.INSTANCE;
    return dateTimeSerializer.deserialize(stream, startPosition);
  }

  public int getObjectSize(byte[] stream, int startPosition) {
    return OLongSerializer.LONG_SIZE;
  }

  public byte getId() {
    return ID;
  }

  public int getObjectSizeNative(byte[] stream, int startPosition) {
    return OLongSerializer.LONG_SIZE;
  }

  public void serializeNative(Date object, byte[] stream, int startPosition) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(object);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    ODateTimeSerializer dateTimeSerializer = ODateTimeSerializer.INSTANCE;
    dateTimeSerializer.serializeNative(calendar.getTime(), stream, startPosition);
  }

  public Date deserializeNative(byte[] stream, int startPosition) {
    ODateTimeSerializer dateTimeSerializer = ODateTimeSerializer.INSTANCE;
    return dateTimeSerializer.deserializeNative(stream, startPosition);
  }

  public boolean isFixedLength() {
    return true;
  }

  public int getFixedLength() {
    return OLongSerializer.LONG_SIZE;
  }
}
