/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
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
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.remote.grpc.data.register;

import org.skywalking.apm.collector.core.data.Data;
import org.skywalking.apm.collector.remote.RemoteDataMapping;
import org.skywalking.apm.collector.remote.grpc.data.GRPCRemoteData;
import org.skywalking.apm.collector.remote.grpc.proto.RemoteData;

/**
 * @author peng-yongsheng
 */
public class InstanceRemoteData extends GRPCRemoteData {

    @Override public RemoteDataMapping mapping() {
        return RemoteDataMapping.Instance;
    }

    @Override public RemoteData.Builder serialize(Data data) {
        RemoteData.Builder builder = RemoteData.newBuilder();
        builder.addDataStrings(data.getDataString(0));
        builder.addDataIntegers(data.getDataInteger(0));
        builder.addDataStrings(data.getDataString(1));
        builder.addDataIntegers(data.getDataInteger(1));
        builder.addDataLongs(data.getDataLong(0));
        builder.addDataLongs(data.getDataLong(1));
        builder.addDataStrings(data.getDataString(2));
        return builder;
    }

    @Override public Data deserialize(RemoteData remoteData) {
        Data data = build(remoteData);
        data.setDataString(0, remoteData.getDataStrings(0));
        data.setDataInteger(0, remoteData.getDataIntegers(0));
        data.setDataString(1, remoteData.getDataStrings(1));
        data.setDataInteger(1, remoteData.getDataIntegers(1));
        data.setDataLong(0, remoteData.getDataLongs(0));
        data.setDataLong(1, remoteData.getDataLongs(1));
        data.setDataString(2, remoteData.getDataStrings(2));
        return data;
    }
}