package org.estatio.module.asset.fixtures.person.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.isis.applib.fixturescripts.PersonaWithBuilderScript;
import org.apache.isis.applib.fixturescripts.PersonaWithFinder;
import org.apache.isis.applib.services.registry.ServiceRegistry2;

import org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum;

import org.estatio.module.asset.dom.role.FixedAssetRoleTypeEnum;
import org.estatio.module.asset.fixtures.person.builders.PersonAndRolesBuilder;
import org.estatio.module.asset.fixtures.person.builders.PersonFixedAssetRolesBuilder;
import org.estatio.module.asset.fixtures.property.enums.Property_enum;
import org.estatio.module.party.dom.Party;
import org.estatio.module.party.dom.PartyRepository;
import org.estatio.module.party.dom.Person;
import org.estatio.module.party.dom.PersonGenderType;
import org.estatio.module.party.dom.relationship.PartyRelationshipTypeEnum;
import org.estatio.module.party.dom.role.IPartyRoleType;
import org.estatio.module.party.dom.role.PartyRoleTypeEnum;
import org.estatio.module.party.fixtures.organisation.enums.Organisation_enum;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import static org.estatio.module.party.dom.PersonGenderType.FEMALE;
import static org.estatio.module.party.dom.PersonGenderType.MALE;
import static org.estatio.module.party.dom.relationship.PartyRelationshipTypeEnum.CONTACT;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.OmsHyraSe;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.PastaPapaItNl;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.PerdantFr;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.TopModelGb;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.TopModelSe;
import static org.estatio.module.party.fixtures.organisation.enums.Organisation_enum.YoukeaSe;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.Fr;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.Gb;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.GbFr;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.It;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.Nl;
import static org.incode.module.apptenancy.fixtures.enums.ApplicationTenancy_enum.Se;

@Getter
@Accessors(chain = true)
public enum Person_enum
        implements PersonaWithBuilderScript<Person, PersonAndRolesBuilder>, PersonaWithFinder<Person> {

    AgnethaFaltskogSe("AFALTSKOG", "Agnetha", "Faltskog", "A", false, FEMALE, Se, Se,
            CONTACT, YoukeaSe,
            new IPartyRoleType[] { },
            new FixedAssetRoleSpec[] {}),

    BrunoTreasurerFr("BJEREMIE", "Bruno", "Jeremei", null, true, MALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.TREASURER },
            new FixedAssetRoleSpec[] {}),

    DylanOfficeAdministratorGb("DCLAYTON", "Dylan", "Clayton", null, true, MALE, Gb, GbFr, //Since ECP-677 multiple security atPath
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.OFFICE_ADMINISTRATOR },
            new FixedAssetRoleSpec[] {}),

    EmmaTreasurerGb("EFARMER", "Emma", "Farmer", "E", true, FEMALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.TREASURER },
            new FixedAssetRoleSpec[] {}),

    FaithConwayGb("FCONWAY", "Faith", "Conway", null, true, FEMALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] {},
            new FixedAssetRoleSpec[] {}),

    FifineLacroixFr("FLACROIX", "Fifine", "Lacroix", null, true, FEMALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.INCOMING_INVOICE_MANAGER },
            new FixedAssetRoleSpec[] {
                new FixedAssetRoleSpec(FixedAssetRoleTypeEnum.PROPERTY_MANAGER, Property_enum.VivFr),
                new FixedAssetRoleSpec(FixedAssetRoleTypeEnum.PROPERTY_MANAGER, Property_enum.MnsFr),
            }),

    FleuretteRenaudFr("FRENAUD", "Fleurette", "Renaud", null, false, FEMALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    FloellaAssetManagerGb("FBEAUTIFUL", "Floella", "Beautiful", null, true, FEMALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {
                new FixedAssetRoleSpec(FixedAssetRoleTypeEnum.ASSET_MANAGER, Property_enum.OxfGb)
            }),

    GabrielHerveFr("GHERVE", "Gabriel", "Herve", null, true, FEMALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.COUNTRY_DIRECTOR },
            new FixedAssetRoleSpec[] {}),

    GinoVannelliGb("GVANNELLI", "Gino", "Vannelli", "G", false, MALE, Gb, Gb,
            CONTACT, TopModelGb,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    JeanneDarcFr("JDARC", "Jeanne", "D'Arc", "J", false, MALE, Fr, Fr,
            CONTACT, PerdantFr,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    JohnDoeNl("JDOE", "John", "Doe", "J", false, MALE, Nl, Nl,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    JohnSmithGb("JSMTH", "John", "Smith", "J", false, MALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    JonathanIncomingInvoiceManagerGb("JRICE", "Jonathan", "Rice", null, true, MALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.INCOMING_INVOICE_MANAGER },
            new FixedAssetRoleSpec[] {}),

    LinusTorvaldsNl("LTORVALDS", "Linus", "Torvalds", "L", false, MALE, Nl, Nl,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    LucianoPavarottiIt("LPAVAROTTI", "Luciano", "Pavarotti", "L", false, MALE, It, It,
            CONTACT, PastaPapaItNl,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    OlivePropertyManagerFr("OBEAUSOLIEL", "Olive", "Beusoleil", null, true, FEMALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.INCOMING_INVOICE_MANAGER },
            new FixedAssetRoleSpec[] {
                new FixedAssetRoleSpec(FixedAssetRoleTypeEnum.PROPERTY_MANAGER, Property_enum.MacFr)
            }),

    OscarCountryDirectorGb("OPRITCHARD", "Oscar", "Pritchard", null, true, MALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.COUNTRY_DIRECTOR },
            new FixedAssetRoleSpec[] {}),

    PeterPanProjectManagerGb("PP", "Peter", "Pan", "P", true, MALE, Gb, Gb,
            null, null,
            new IPartyRoleType[] { },
            new FixedAssetRoleSpec[] {}),

    RosaireEvrardFr("REVRARD", "Rosaire", "Evrard", null, true, FEMALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),

    ThibaultOfficerAdministratorFr("TJOSUE", "Thibault", "Josue", null, true, MALE, Fr, Fr,
            null, null,
            new IPartyRoleType[] { PartyRoleTypeEnum.OFFICE_ADMINISTRATOR },
            new FixedAssetRoleSpec[] {}),
    JohnDoeSe("JDOE", "John", "Doe", "J", false, MALE, Se, Se,
            null, null,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),
    GinoVannelliSe("GVANNELLI", "Gino", "Vannelli", "G", false, MALE, Se, Se,
            CONTACT, TopModelSe,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),
    JonasCar("JCAR", "Jonas", "Car", "J", false, MALE, Se, Se,
            CONTACT, OmsHyraSe,
            new IPartyRoleType[] {  },
            new FixedAssetRoleSpec[] {}),
    ;

    @Data
    public static class FixedAssetRoleSpec {
        private final FixedAssetRoleTypeEnum fixedAssetRole;
        private final Property_enum property_d;
    }

    private final String ref;
    private final String firstName;
    private final String lastName;
    private final String initials;
    private final String securityUserName;
    private final ApplicationTenancy_enum securityAtPath;
    private final PersonGenderType personGenderType;
    private final ApplicationTenancy_enum applicationTenancy_d;

    private final PartyRelationshipTypeEnum partyRelationshipType;
    private final Organisation_enum partyFrom_d;

    private final IPartyRoleType[] partyRoleTypes;
    private final FixedAssetRoleSpec[] fixedAssetRoles;

    Person_enum(
            final String ref,
            final String firstName,
            final String lastName,
            final String initials,
            final boolean setupSecurityUser,
            final PersonGenderType personGenderType,
            final ApplicationTenancy_enum applicationTenancy_d,
            final ApplicationTenancy_enum securityAtPath,
            final PartyRelationshipTypeEnum partyRelationshipType,
            final Organisation_enum partyFrom_d,
            final IPartyRoleType[] partyRoleTypes,
            final FixedAssetRoleSpec[] fixedAssetRoles) {
        this.ref = ref;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.securityUserName = setupSecurityUser ? ref.toLowerCase() : null;
        this.personGenderType = personGenderType;
        this.applicationTenancy_d = applicationTenancy_d;
        this.securityAtPath = securityAtPath;

        this.partyFrom_d = partyFrom_d;
        this.partyRelationshipType = partyRelationshipType;

        this.partyRoleTypes = partyRoleTypes;
        this.fixedAssetRoles = fixedAssetRoles;
    }

    @Override
    public Person findUsing(final ServiceRegistry2 serviceRegistry) {
        final PartyRepository partyRepository = serviceRegistry
                .lookupService(PartyRepository.class);
        final Party party = partyRepository.findPartyByReference(ref);
        return (Person) party;
    }


    @Override
    public PersonAndRolesBuilder builder() {

        final PersonAndRolesBuilder personAndRolesBuilder = new PersonAndRolesBuilder()
                .setReference(getRef())
                .setFirstName(getFirstName())
                .setLastName(getLastName())
                .setInitials(getInitials())
                .setSecurityUsername(getSecurityUserName())
                .setSecurityAtPath(getSecurityAtPath().getPath())
                .setPersonGenderType(getPersonGenderType())
                .setAtPath(getApplicationTenancy_d().getPath())
                .setRelationshipType(getPartyRelationshipType())
                .setPrereq((f,ec) -> f.setFromParty(f.objectFor(getPartyFrom_d(), ec)))
                .setPrereq((f,ec) -> f.setFixedAssetRoleSpecs(
                            Arrays.stream(Person_enum.this.getFixedAssetRoles())
                                .map(x -> new PersonFixedAssetRolesBuilder.FixedAssetRoleSpec(
                                                x.fixedAssetRole,
                                                f.objectFor(x.getProperty_d(), ec))
                                        )
                                .collect(Collectors.toList())))
                ;

        for (final IPartyRoleType partyRoleType : getPartyRoleTypes()) {
            personAndRolesBuilder.addPartyRoleType(partyRoleType);
        }
        return personAndRolesBuilder;
    }

}
