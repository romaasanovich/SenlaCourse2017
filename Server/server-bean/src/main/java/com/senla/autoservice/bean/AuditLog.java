package com.senla.autoservice.bean;

import com.senla.autoservice.bean.aentity.AEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "audit_log")
public class AuditLog extends AEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "url")
    private String url;
    @Column(name = "time")
    private Long time;

    public AuditLog() {
    }

    public AuditLog(final User user, final String url) {
        this.user = user;
        this.url = url;
        time = new Date().getTime();
    }

    public User getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    public Long getTime() {
        return time;
    }
}
