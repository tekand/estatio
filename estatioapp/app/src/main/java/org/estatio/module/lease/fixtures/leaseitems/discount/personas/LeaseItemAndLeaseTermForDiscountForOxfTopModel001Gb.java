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
package org.estatio.module.lease.fixtures.leaseitems.discount.personas;

import org.estatio.module.base.fixtures.security.apptenancy.enums.ApplicationTenancy_enum;
import org.estatio.module.lease.fixtures.LeaseItemAndTermsAbstract;
import org.estatio.module.lease.fixtures.lease.enums.Lease_enum;
import org.estatio.module.lease.fixtures.leaseitems.discount.enums.LeaseItemForDiscount_enum;

public class LeaseItemAndLeaseTermForDiscountForOxfTopModel001Gb extends LeaseItemAndTermsAbstract {

    public static final String LEASE_REF = Lease_enum.OxfTopModel001Gb.getRef();
    public static final String AT_PATH = ApplicationTenancy_enum.GbOxfDefault.getPath();

    @Override
    protected void execute(final ExecutionContext fixtureResults) {
        createLeaseTermsForOxfTopModel001(fixtureResults);
    }

    private void createLeaseTermsForOxfTopModel001(final ExecutionContext executionContext) {

        // prereqs
        executionContext.executeChild(this, Lease_enum.OxfTopModel001Gb.builder());

        // exec

        if(System.getProperty("lease-item-legacy") == null) {
        } else {
        }

        executionContext.executeChild(this, LeaseItemForDiscount_enum.OxfTopModel001Gb.builder());

//        final Lease lease = leaseRepository.findLeaseByReference(LEASE_REF);
//        createLeaseTermForDiscount(
//                LEASE_REF,
//                AT_PATH,
//                lease.getStartDate(), lease.getStartDate().plusYears(5),
//                bd(-2000),
//                executionContext);
    }
}
