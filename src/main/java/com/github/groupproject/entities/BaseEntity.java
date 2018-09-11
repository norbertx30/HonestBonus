package com.github.groupproject.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
abstract class BaseEntity {

    private String uuid = UUID.randomUUID().toString();
    private LocalDateTime createdAt;

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity that = (BaseEntity)obj;
            return that.uuid.equals(this.uuid);
        }
    }
}
