package com.masmovil.it.compensator.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final String LANDLINE = "landline";
  private static final String MOBILE = "mobile";

  @JsonAlias({ "id", "Id" })
  private String id;
  @JsonAlias({ "name", "Name" })
  private String name;

  @Nullable
  @JsonAlias({ "subcategoryId", "TKT_T_CodigoVista__c" })
  private String subcategoryId;

  @JsonAlias({ "serviceProviderId", "SamanageESD__ServiceProvider__c" })
  private String serviceProviderId;
  private String serviceProviderName;

  @JsonProperty("SamanageESD__ServiceProvider__r")
  private void unpackNestedProvider(Map<String, Object> serviceProvider) {
    if (serviceProvider != null) {
      this.serviceProviderName = (String) serviceProvider.get("Name");
      this.caseType = (String) serviceProvider.get("TKT_MP_TiposCaso__c");
    }
  }

  private String caseType;

  @Nullable
  @JsonAlias({ "parentCategoryId", "SamanageESD__ParentId__c" })
  private String parentCategoryId;
  @Nullable
  private String parentCategoryName;

  @JsonProperty("SamanageESD__ParentId__r")
  private void unpackNestedParentCategory(Map<String, Object> parentCategory) {
    if (parentCategory != null) {
      this.parentCategoryName = (String) parentCategory.get("Name");
    }
  }

  @Nullable
  @JsonAlias({ "posibleQueues", "TKT_MP_ColasPosibles__c" })
  private String posibleQueues;

  @JsonAlias({ "mobileSubscription", "TKT_C_ValidoMovil__c" })
  private boolean mobileSubscription;
  @JsonAlias({ "landlineSubscription", "TKT_C_ValidoFijo__c" })
  private boolean landlineSubscription;

  /**
   * Check if a Category has an specific subscription type.
   * @param subscription subscription type.
   * @return true or false.
   */
  public boolean checkSubscriptionType(Optional<String> subscription) {
    switch (subscription.orElse("").toLowerCase()) {
      case (LANDLINE):
        return landlineSubscription;
      case (MOBILE):
        return mobileSubscription;
      default:
        return true;
    }
  }

}