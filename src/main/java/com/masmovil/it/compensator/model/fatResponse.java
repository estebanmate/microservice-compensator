package com.masmovil.it.compensator.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class fatResponse {
  @JsonAlias({ "Id", "id" })
  private String id;

  @JsonAlias({ "RecordTypeId", "recordTypeId" })
  private String recordTypeId;

  @JsonAlias({ "Priority", "priority" })
  private String priority;

  @JsonAlias({ "Description", "description" })
  private String description;

  @JsonAlias({ "Origin", "origin" })
  private String origin;

  @JsonAlias({ "Subject", "subject" })
  private String subject;

  @JsonAlias({ "TKT_PH_Suscripcion__c", "tkt_PH_Suscripcion__c" })
  private String tkt_PH_Suscripcion__c;

  @JsonAlias({ "TKT_T_UCID__c", "tkt_T_UCID__c" })
  private String tkt_T_UCID__c;

  @JsonAlias({ "Nombre_Contacto__c", "nombre_Contacto__c" })
  private String nombre_Contacto__c;

  @JsonAlias({ "Apellidos_Contacto__c", "apellidos_Contacto__c" })
  private String apellidos_Contacto__c;

  @JsonAlias({ "Email_del_Contacto__c", "email_del_Contacto__c" })
  private String email_del_Contacto__c;

  @JsonAlias({ "Telefono_del_Contacto__c", "telefono_del_Contacto__c" })
  private String telefono_del_Contacto__c;

  @JsonAlias({ "Tipo_de_documento__c", "tipo_de_documento__c" })
  private String tipo_de_documento__c;

  @JsonAlias({ "Numero_de_documento__c", "numero_de_documento__c" })
  private String numero_de_documento__c;

  @JsonAlias({ "Nombre_de_Compania__c", "nombre_de_Compania__c" })
  private String nombre_de_Compania__c;

  @JsonAlias({ "TKT_P_TipoCliente__c", "tkt_P_TipoCliente__c" })
  private String tkt_P_TipoCliente__c;

  @JsonAlias({ "TKT_P_SegmentoCliente__c", "tkt_P_SegmentoCliente__c" })
  private String tkt_P_SegmentoCliente__c;

  @JsonAlias({ "TKT_T_NumeroCliente__c", "tkt_T_NumeroCliente__c" })
  private String tkt_T_NumeroCliente__c;

  @JsonAlias({ "TKT_P_TipoContrato__c", "tkt_P_TipoContrato__c" })
  private String tkt_P_TipoContrato__c;

  @JsonAlias({ "TKT_P_TipoServicio__c", "tkt_P_TipoServicio__c" })
  private String tkt_P_TipoServicio__c;

  @JsonAlias({ "TKT_P_Marca__c", "tkt_P_Marca__c" })
  private String tkt_P_Marca__c;

  @JsonAlias({ "TKT_P_TipoCobertura__c", "tkt_P_TipoCobertura__c" })
  private String tkt_P_TipoCobertura__c;

  @JsonAlias({ "TKT_P_Red__c", "tkt_P_Red__c" })
  private String tkt_P_Red__c;

  @JsonAlias({ "TKT_T_CodigoVista__c", "tkt_T_CodigoVista__c" })
  private String tkt_T_CodigoVista__c;

  @JsonAlias({ "TKT_P_PlataformaOrigen__c", "tkt_P_PlataformaOrigen__c" })
  private String tkt_P_PlataformaOrigen__c;

  @JsonAlias({ "TKT_P_SedeOrigen__c", "tkt_P_SedeOrigen__c" })
  private String tkt_P_SedeOrigen__c;

  @JsonAlias({ "TKT_P_TipoReclamacion__c", "tkt_P_TipoReclamacion__c" })
  private String tkt_P_TipoReclamacion__c;

  @JsonAlias({ "TKT_P_MotivoPendiente__c", "tkt_P_MotivoPendiente__c" })
  private String tkt_P_MotivoPendiente__c;

  @JsonAlias({ "TKT_T_IdentificadoresFactura__c", "tkt_T_IdentificadoresFactura__c" })
  private String tkt_T_IdentificadoresFactura__c;

  @JsonAlias({ "TKT_T_IdVendedor__c", "tkt_T_IdVendedor__c" })
  private String tkt_T_IdVendedor__c;

  @JsonAlias({ "TKT_P_TipoCuota__c", "tkt_P_TipoCuota__c" })
  private String tkt_P_TipoCuota__c;

  @JsonAlias({ "TKT_P_TipoDevolucion__c", "tkt_P_TipoDevolucion__c" })
  private String tkt_P_TipoDevolucion__c;

  @JsonAlias({ "TKT_P_TipoBono__c", "tkt_P_TipoBono__c" })
  private String tkt_P_TipoBono__c;

  @JsonAlias({ "TKT_P_MotivoDesacuerdo__c", "tkt_P_MotivoDesacuerdo__c" })
  private String tkt_P_MotivoDesacuerdo__c;

  @JsonAlias({ "TKT_T_NumeroReclamado__c", "tkt_T_NumeroReclamado__c" })
  private String tkt_T_NumeroReclamado__c;

  @JsonAlias({ "TKT_P_TipoPenalizacion__c", "tkt_P_TipoPenalizacion__c" })
  private String tkt_P_TipoPenalizacion__c;

  @JsonAlias({ "TKT_P_ProvocaReclamacion__c", "tkt_P_ProvocaReclamacion__c" })
  private String tkt_P_ProvocaReclamacion__c;

  @JsonAlias({ "TKT_CU_ImporteReclamado__c", "tkt_CU_ImporteReclamado__c" })
  private String tkt_CU_ImporteReclamado__c;

  @JsonAlias({ "SamanageESD__Skip_Assignment_rules__c", "samanageESD__Skip_Assignment_rules__c" })
  private String samanageESD__Skip_Assignment_rules__c;

  @JsonAlias({ "OwnerId", "ownerId" })
  private String ownerId;

  // Fields used for Client Ticket List
  @JsonAlias({ "caseNumber", "CaseNumber" })
  private String caseNumber;

  @JsonAlias({ "recordType", "SamanageESD__RecordType__c" })
  private String recordType;

  private String serviceProviderName;

  @JsonProperty("SamanageESD__ServiceProvider__r")
  private void unpackNestedProvider(Map<String, Object> serviceProvider) {
    if (serviceProvider != null) {
      this.serviceProviderName = (String) serviceProvider.get("Name");
    }
  }

  private String categoryName;

  @JsonProperty("SamanageESD__Category__r")
  private void unpackNestedCategory(Map<String, Object> category) {
    if (category != null) {
      this.categoryName = (String) category.get("Name");
    }
  }

  private String subcategoryName;

  @JsonProperty("SamanageESD__SubCategory__r")
  private void unpackNestedSubCategory(Map<String, Object> subcategory) {
    if (subcategory != null)
      this.subcategoryName = (String) subcategory.get("Name");
  }

  @JsonAlias({ "asigneeName", "SamanageESD__Asignee_Name__c" })
  private String asigneeName;

  @JsonAlias({ "ownerName", "SamanageESD__OwnerName__c" })
  private String ownerName;

  @JsonAlias({ "createdDate", "CreatedDate" })
  private String createdDate;

  @JsonAlias({ "lastModifiedDate", "LastModifiedDate" })
  private String lastModifiedDate;
  
  @JsonAlias({ "status", "Status" })
  private String status;

  @JsonAlias({ "commentsNumber", "SamanageESD__hasComments__c" })
  private int commentsNumber;

}
