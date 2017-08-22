/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.tax.fixture;

import org.incode.module.fixturesupport.dom.scripts.TeardownFixtureAbstract;

import org.estatio.tax.dom.Tax;
import org.estatio.tax.dom.TaxRate;

public class TaxModule_tearDown extends TeardownFixtureAbstract {

    @Override
    protected void execute(final ExecutionContext executionContext) {
        deleteFrom(TaxRate.class);
        deleteFrom(Tax.class);
    }

}
