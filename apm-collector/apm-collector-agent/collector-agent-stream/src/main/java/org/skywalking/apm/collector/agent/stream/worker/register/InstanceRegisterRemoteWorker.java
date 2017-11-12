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

package org.skywalking.apm.collector.agent.stream.worker.register;

import org.skywalking.apm.collector.cache.CacheServiceManager;
import org.skywalking.apm.collector.remote.service.RemoteClientService;
import org.skywalking.apm.collector.storage.service.DAOService;
import org.skywalking.apm.collector.storage.table.register.Instance;
import org.skywalking.apm.collector.stream.worker.base.AbstractRemoteWorker;
import org.skywalking.apm.collector.stream.worker.base.AbstractRemoteWorkerProvider;
import org.skywalking.apm.collector.stream.worker.base.WorkerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng
 */
public class InstanceRegisterRemoteWorker extends AbstractRemoteWorker<Instance, Instance> {

    private final Logger logger = LoggerFactory.getLogger(InstanceRegisterRemoteWorker.class);

    @Override public int id() {
        return 0;
    }

    public InstanceRegisterRemoteWorker(DAOService daoService, CacheServiceManager cacheServiceManager) {
        super(daoService, cacheServiceManager);
    }

    @Override protected void onWork(Instance instance) throws WorkerException {
        logger.debug("application id: {}, agentUUID: {}, register time: {}", instance.getApplicationId(), instance.getAgentUUID(), instance.getRegisterTime());
        onNext(instance);
    }

    public static class Factory extends AbstractRemoteWorkerProvider<Instance, Instance, InstanceRegisterRemoteWorker> {

        public Factory(DAOService daoService, CacheServiceManager cacheServiceManager,
            RemoteClientService remoteClientService) {
            super(daoService, cacheServiceManager, remoteClientService);
        }

        @Override
        public InstanceRegisterRemoteWorker workerInstance(DAOService daoService,
            CacheServiceManager cacheServiceManager) {
            return new InstanceRegisterRemoteWorker(getDaoService(), getCacheServiceManager());
        }
    }
}