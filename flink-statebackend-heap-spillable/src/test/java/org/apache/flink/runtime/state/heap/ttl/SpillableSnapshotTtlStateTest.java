/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one
 *  * or more contributor license agreements.  See the NOTICE file
 *  * distributed with this work for additional information
 *  * regarding copyright ownership.  The ASF licenses this file
 *  * to you under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.apache.flink.runtime.state.heap.ttl;

import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.runtime.state.heap.SpillableStateBackend;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.runtime.state.ttl.SpillableTtlUtils;
import org.apache.flink.runtime.state.ttl.StateBackendTestContext;
import org.apache.flink.runtime.state.ttl.TtlStateTestBase;
import org.apache.flink.runtime.state.ttl.TtlStateTestContextBase;
import org.apache.flink.runtime.state.ttl.TtlTimeProvider;

import org.junit.runners.Parameterized;

import java.util.List;

/**
 * Test suite for spillable state TTL.
 */
public class SpillableSnapshotTtlStateTest extends TtlStateTestBase {

	@Override
	protected StateBackendTestContext createStateBackendTestContext(TtlTimeProvider timeProvider) {
		return new StateBackendTestContext(timeProvider) {
			@Override
			protected StateBackend createStateBackend() {
				return new SpillableStateBackend(new MemoryStateBackend(true));
			}
		};
	}

	@Override
	protected boolean incrementalCleanupSupported() {
		return true;
	}

	@Parameterized.Parameters(name = "{0}")
	public static List<TtlStateTestContextBase<?, ?, ?>> testContexts() {
		return SpillableTtlUtils.getContexts();
	}
}
