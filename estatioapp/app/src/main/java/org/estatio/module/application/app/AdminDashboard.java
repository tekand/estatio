package org.estatio.module.application.app;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.email.EmailService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.message.MessageService;

import org.isisaddons.module.servletapi.dom.HttpSessionProvider;

import org.incode.module.slack.impl.SlackService;

import org.estatio.module.lease.dom.settings.LeaseInvoicingSettingsService;
import org.estatio.module.settings.dom.ApplicationSettingForEstatio;
import org.estatio.module.settings.dom.ApplicationSettingsServiceRW;

@DomainObject(
        // WORKAROUND: using fqcn as objectType because Isis' invalidation of cache in prototyping mode causing NPEs in some situations
        objectType = "org.estatio.module.application.app.AdminDashboard"
)
@XmlRootElement(name = "adminDashboard")
@XmlType(
        propOrder = {
        }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminDashboard {

    public static final String KEY_ESTATIO_MOTD = "estatio.motd";
    public static final String KEY_MINIO_ARCHIVE_FOR_CALLER = "docBlobServer.caller";

    public String title() { return "Admin Dashboard"; }


    public enum DocBlobArchiveCaller {
        CAMEL("camel"),
        BATCH("batch"),
        CAMEL_AND_BATCH("camel,batch"),
        NONE("none");

        private final String title;
        DocBlobArchiveCaller(final String title) {
            this.title = title;
        }

        public String title() {
            return title;
        }

        public static DocBlobArchiveCaller lookup(final String title) {
            final DocBlobArchiveCaller[] values = values();
            for (final DocBlobArchiveCaller value : values) {
                if(value.title().equalsIgnoreCase(title)) {
                    return value;
                }
            }
            return null;
        }

    }

    @Property(
            editing = Editing.ENABLED,
            optionality = Optionality.OPTIONAL
    )
    @XmlTransient
    public DocBlobArchiveCaller getDocBlobServerCaller() {
        ApplicationSettingForEstatio applicationSetting =
                (ApplicationSettingForEstatio) applicationSettingsServiceRW.find(KEY_MINIO_ARCHIVE_FOR_CALLER);
        return applicationSetting != null
                ? DocBlobArchiveCaller.lookup(applicationSetting.valueAsString())
                : null;
    }
    public void setDocBlobServerCaller(DocBlobArchiveCaller caller) {
        ApplicationSettingForEstatio applicationSetting =
                (ApplicationSettingForEstatio) applicationSettingsServiceRW.find(KEY_MINIO_ARCHIVE_FOR_CALLER);
        if(applicationSetting != null) {
            if(caller != null) {
                applicationSetting.setValueRaw(caller.title());
            } else {
                applicationSettingsServiceRW.delete(applicationSetting);
            }
        } else {
            if(caller != null) {
                applicationSettingsServiceRW.newString(KEY_MINIO_ARCHIVE_FOR_CALLER, "Which caller(s) to enable the archive of document blobs to Minio", caller.title());
            }
        }
    }

    @Property(
            editing = Editing.ENABLED,
            optionality = Optionality.OPTIONAL,
            hidden = Where.EVERYWHERE // for now...
    )
    @XmlTransient
    public String getMotd() {
        ApplicationSettingForEstatio applicationSetting =
                (ApplicationSettingForEstatio) applicationSettingsServiceRW.find(KEY_ESTATIO_MOTD);
        return applicationSetting != null ? applicationSetting.valueAsString() : null;
    }
    public void setMotd(String motd) {
        ApplicationSettingForEstatio applicationSetting =
                (ApplicationSettingForEstatio) applicationSettingsServiceRW.find(KEY_ESTATIO_MOTD);
        if(applicationSetting != null) {
            if(motd != null) {
                applicationSetting.setValueRaw(motd);
            } else {
                applicationSettingsServiceRW.delete(applicationSetting);
            }
        } else {
            if(motd != null) {
                applicationSettingsServiceRW.newString(KEY_ESTATIO_MOTD, "Message of the Day", motd);
            }
        }
    }


    @Property(editing = Editing.DISABLED, optionality = Optionality.OPTIONAL)
    @XmlTransient
    public LocalDate getEpochDate() {
        return settingsService.fetchEpochDate();
    }
    public void setEpochDate(final LocalDate epochDate) {
        settingsService.updateEpochDate(epochDate);
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public AdminDashboard updateEpochDate(final LocalDate epochDate) {
        setEpochDate(epochDate);
        return this;
    }
    public LocalDate default0UpdateEpochDate() {
        return getEpochDate();
    }


    @Property()
    @MemberOrder(sequence = "3")
    @XmlTransient
    public Integer getHttpSessionTimeout() {
        return httpSessionProvider.getHttpSession().map(HttpSession::getMaxInactiveInterval).orElse(null);
    }


    @Collection()
    @MemberOrder(sequence = "2")
    public List<ApplicationSettingForEstatio> getApplicationSettings() {
        return applicationSettingsServiceRW.listAll()
                .stream()
                .filter(ApplicationSettingForEstatio.class::isInstance)
                .map(ApplicationSettingForEstatio.class::cast)
                .collect(Collectors.toList());
    }


    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "3.1")
    public AdminDashboard testEmail(
            @ParameterLayout(named = "To")
            final String to,
            @ParameterLayout(named = "Subject")
            final String subject,
            @ParameterLayout(
                    named = "Body",
                    multiLine = 5
            )
            final String body) {
        final List<String> toList = Collections.singletonList(to);
        final List<String> ccList = Collections.emptyList();
        final List<String> bccList = Collections.emptyList();
        emailService.send(toList, ccList, bccList, subject, body);
        return this;
    }
    public String disableTestEmail() {
        if(emailService == null) {
            return "No EmailService defined";
        }
        if (!emailService.isConfigured()) {
            return "EmailService is not configured";
        }
        return null;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "3.2")
    public AdminDashboard testSlack(
            String channel,
            String message) {
        slackService.sendMessage(channel, message);
        return this;
    }
    public String disableTestSlack() {
        if (slackService == null) {
            return "No SlackService defined";
        }
        if (!slackService.isConfigured()) {
            return "SlackService is not configured";
        }
        return null;
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    @MemberOrder(sequence = "3.4")
    public AdminDashboard patchDatabase(
            @ParameterLayout(multiLine = 10)
            String sql) {
        final Integer integer = isisJdoSupport.executeUpdate(sql);
        messageService.informUser("executeUpdate(sql) returned: " + integer);
        return this;
    }



    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    @MemberOrder(sequence = "3.5")
    public void raiseRuntimeException() {
        throw new RuntimeException();
    }




    @Inject
    @XmlTransient
    SlackService slackService;

    @Inject
    @XmlTransient
    EmailService emailService;

    @Inject
    @XmlTransient
    LeaseInvoicingSettingsService settingsService;

    @Inject
    @XmlTransient
    ApplicationSettingsServiceRW applicationSettingsServiceRW;

    @Inject
    @XmlTransient
    HttpSessionProvider httpSessionProvider;

    @Inject
    @XmlTransient
    IsisJdoSupport isisJdoSupport;

    @Inject
    @XmlTransient
    MessageService messageService;

}
