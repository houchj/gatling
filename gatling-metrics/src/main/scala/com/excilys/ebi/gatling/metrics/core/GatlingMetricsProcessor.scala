/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.metrics.core

import com.yammer.metrics.core.MetricName
import com.excilys.ebi.gatling.metrics.types.{FastHistogram, FastCounter}

trait GatlingMetricsProcessor[T] {

	/**
	 * Process the given fast counter.
	 *
	 * @param name       the name of the counter
	 * @param counter    the counter
	 * @param context    the epoch of the meter
	 * @throws Exception if something goes wrong
	 */
	def processFastCounter(name: MetricName, counter: FastCounter, context: T)

	/**
	 * Process the given fast histogram.
	 *
	 * @param name       the name of the histogram
	 * @param histogram  the histogram
	 * @param context    the epoch of the meter
	 * @throws Exception if something goes wrong
	 */
	def processFastHistogram(name: MetricName, histogram: FastHistogram, context: T)

}
