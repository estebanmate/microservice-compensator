package com.masmovil.it.compensator.model;

import lombok.Data;

@Data
public class TicketType {
  private String developerName;
  private String recordTypeId;
  private String name;

  public TicketType() {}

  /**
   * Full parameters constructor.
   * @param developerName developerName.
   * @param recordTypeId recordTypeId.
   * @param name name.
   */
  public TicketType(String developerName, String recordTypeId, String name) {
    this.developerName = developerName;
    this.recordTypeId = recordTypeId;
    this.name = name;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other == this) {
      return true;
    }
    if (!(other instanceof TicketType)) {
      return false;
    }
    TicketType otherMyClass = (TicketType) other;
    return developerName.equals(otherMyClass.getDeveloperName());
  }

  @Override
  public int hashCode() {
    return recordTypeId == null ? 43 : recordTypeId.hashCode();
  }
}
