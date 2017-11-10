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
package org.estatio.module.party.fixtures.organisation.personas;

import org.estatio.module.base.fixtures.security.apptenancy.personas.ApplicationTenancyForGb;

public class OrganisationForPoisonGb extends OrganisationAbstract {

    public static final String REF = "POISON_GB";
    public static final String AT_PATH = ApplicationTenancyForGb.PATH;

    @Override
    protected void execute(ExecutionContext executionContext) {
        createOrganisation(
                AT_PATH,
                REF,
                "Poison Perfumeries",
                "46 Brewster Street",
                null,
                "W2D1PQ",
                "London",
                null,
                "GBR",
                "+44202218888",
                "+44202218899",
                "info@poison-perfumeries.com",
                executionContext);

    }

}