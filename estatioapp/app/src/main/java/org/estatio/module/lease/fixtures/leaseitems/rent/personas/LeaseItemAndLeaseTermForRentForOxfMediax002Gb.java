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
package org.estatio.module.lease.fixtures.leaseitems.rent.personas;

import org.estatio.module.base.fixtures.security.apptenancy.enums.ApplicationTenancy_enum;
import org.estatio.module.index.fixtures.IndexRefData;
import org.estatio.module.lease.dom.Lease;
import org.estatio.module.lease.fixtures.LeaseItemAndTermsAbstract;
import org.estatio.module.lease.fixtures.lease.personas.LeaseForOxfMediaX002Gb;

import static org.incode.module.base.integtests.VT.bd;
import static org.incode.module.base.integtests.VT.ld;

public class LeaseItemAndLeaseTermForRentForOxfMediax002Gb extends LeaseItemAndTermsAbstract {

    public static final String LEASE_REF = LeaseForOxfMediaX002Gb.REF;
    public static final String AT_PATH = ApplicationTenancy_enum.GbOxfDefault.getPath();

    public static final String INDEX_REF_IT = IndexRefData.IT_REF;

    @Override
    protected void execute(final ExecutionContext executionContext) {

        // prereqs
        executionContext.executeChild(this, new LeaseForOxfMediaX002Gb());

        // exec
        final Lease lease = leaseRepository.findLeaseByReference(LEASE_REF);

        createLeaseTermForIndexableRent(
                LEASE_REF,
                AT_PATH,
                lease.getStartDate(), null,
                bd(20000),
                ld(2008, 1, 1), ld(2009, 1, 1), ld(2009, 4, 1),
                INDEX_REF_IT,
                executionContext);
    }

}