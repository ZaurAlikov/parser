/*==============================================================*/
/* Created by:   Alikov Z.O.                                    */
/* Created on:   17.10.2018                                     */
/*==============================================================*/
CREATE SCHEMA IF NOT EXISTS "ESAUTO";

/*==============================================================*/
/* Table: "GOODS"                                               */
/*==============================================================*/
CREATE TABLE "ESAUTO"."GOODS" (
  INTERNAL_ARTICLE          VARCHAR2(255) NOT NULL,
  SUPPLIER_ARTICLE          VARCHAR2(255),
  CATEGORY                  VARCHAR2(255),
  TITLE                     VARCHAR2(1024),
  SHORT_TITLE               VARCHAR2(255),
  MANUFACTURER              VARCHAR2(255),
  COUNTRY                   VARCHAR2(255),
  PRICE                     VARCHAR2(255),
  VIDEO_URL                 VARCHAR2(255),
  SEO_URL                   VARCHAR2(255),
  IMG_URL                   VARCHAR2(255),
  ADDITIONAL_IMG_URL        VARCHAR2(1024),
  DESCRIPTION               VARCHAR2(MAX),
  META_TITLE                VARCHAR2(255),
  META_DESCRIPTION          VARCHAR2(1024),
  PROPERTY_ID               VARCHAR2(255),
  SUITABLE_CARS             VARCHAR2(MAX),
  SUPPLIER_URL              VARCHAR2(255),
  CONSTRAINT PK_INTERNAL_ARTICLE PRIMARY KEY (INTERNAL_ARTICLE),
  CONSTRAINT UQ_SEO_URL UNIQUE (SEO_URL),
  CONSTRAINT UQ_TITLE UNIQUE (TITLE),
);


-- /*==============================================================*/
-- /* Table: "CLIENT"                                              */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_CLIENT" (
--   "ID"            VARCHAR2(255) NOT NULL,
--   "CRM_CLIENT_ID" VARCHAR2(255) NOT NULL,
--   "CRM_PARENT_ID" VARCHAR2(255),
--   "SHORT_NAME"    VARCHAR2(255),
--   "FULL_NAME"     VARCHAR2(255) NOT NULL,
--   "TYPE"          VARCHAR2(255) NOT NULL,
--   "CATEGORY"      VARCHAR2(255),
--   "INN"           VARCHAR2(30),
--   "KPP"           VARCHAR2(15),
--   "TER_DEVISION"  VARCHAR2(255),
--   "SEGMENT_CODE"  VARCHAR2(255),
--   "IS_RESIDENT"   NUMBER(1),
--   "KM_ID"         VARCHAR2(255),
--   CONSTRAINT PK_CLIENT PRIMARY KEY ("ID"),
--   CONSTRAINT FK_CLIENT_KM FOREIGN KEY ("KM_ID") REFERENCES CBB_PARTICIPANT ("ID"),
--   CONSTRAINT UQ_CBB_CLIENT_CRM_CLIENT_ID UNIQUE ("CRM_CLIENT_ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "ISSUE"                                               */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_ISSUE" (
--   "ID"            VARCHAR2(255) NOT NULL,
--   "REVISION_ID"   VARCHAR2(255) NOT NULL,
--   "PARENT_ID"     VARCHAR2(255),
--   "ROOT_ID"       VARCHAR2(255),
--   "AUTHOR_ID"     VARCHAR2(255) NOT NULL,
--   "TYPE_CODE"     VARCHAR2(128) NOT NULL,
--   "STATUS_CODE"   VARCHAR2(128) NOT NULL,
--   "RESULT_CODE"   VARCHAR2(128),
--   "TITLE"         VARCHAR2(255),
--   "DESCR"         VARCHAR2(255),
--   "MSG_CHAIN_UID" VARCHAR2(255),
--   "CLIENT_ID"     VARCHAR2(255),
--   "CREATE_DATE"   DATE          NOT NULL,
--   "PLAN_DATE"     DATE,
--   "CLOSE_DATE"    DATE,
--   CONSTRAINT PK_ISSUE PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_CLIENT FOREIGN KEY ("CLIENT_ID") REFERENCES CBB_CLIENT ("ID"),
--   CONSTRAINT FK_ISSUE_AUTHOR FOREIGN KEY ("AUTHOR_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "EXTENSION"                                           */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_EXTENSION" (
--   "ID"        VARCHAR2(255) NOT NULL,
--   "TYPE_CODE" VARCHAR2(128) NOT NULL,
--   "ISSUE_ID"  VARCHAR2(255) NOT NULL,
--   "OBJECT_ID" VARCHAR2(255) NOT NULL,
--   CONSTRAINT PK_EXTENSION PRIMARY KEY ("ID"),
--   CONSTRAINT FK_EXTENSION_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "KSK_TEAM"                                            */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_KSK_TEAM" (
--   "ID"        VARCHAR2(255) NOT NULL,
--   "CLIENT_ID" VARCHAR2(255) NOT NULL,
--   CONSTRAINT PK_KSK_TEAM PRIMARY KEY ("ID"),
--   CONSTRAINT FK_KSK_TEAM_CLIENT FOREIGN KEY ("CLIENT_ID") REFERENCES CBB_CLIENT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "KSK_TEAM_MEMBER"                                     */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_KSK_TEAM_MEMBER" (
--   "ID"             VARCHAR2(255) NOT NULL,
--   "KSK_TEAM_ID"    VARCHAR2(255) NOT NULL,
--   "ROLE_CODE"      VARCHAR2(128),
--   "PARTICIPANT_ID" VARCHAR2(255),
--   "POSITION_ID"    VARCHAR2(255) NOT NULL,
--   CONSTRAINT PK_KSK_TEAM_MEMBER PRIMARY KEY ("ID"),
--   CONSTRAINT FK_KSK_TEAM_IN_MEMBER FOREIGN KEY ("KSK_TEAM_ID") REFERENCES CBB_KSK_TEAM ("ID"),
--   CONSTRAINT FK_PARTICIPANT FOREIGN KEY ("PARTICIPANT_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "KSK_TASK"                                            */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_KSK_TASK" (
--   "ID"            VARCHAR2(255) NOT NULL,
--   "NUM"           VARCHAR2(255),
--   "KSK_TEAM_ID"   VARCHAR2(255) NOT NULL,
--   "CATEGORY_CODE" VARCHAR2(128) NOT NULL,
--   "TYPE_CODE"     VARCHAR2(128),
--   "ISSUE_ID"      VARCHAR2(255) NOT NULL,
--   CONSTRAINT PK_KSK_TASK PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_KSK_TASK FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_KSK_TEAM_IN_TASK FOREIGN KEY ("KSK_TEAM_ID") REFERENCES CBB_KSK_TEAM ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "DEVIATION"                                           */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_DEVIATION" (
--   "ID"           VARCHAR2(255),
--   "REVISION_ID"  VARCHAR2(255) NOT NULL,
--   "TYPE_CODE"    VARCHAR2(128) NOT NULL,
--   "INDEX_CODE"   VARCHAR2(128),
--   "OBJECT"       VARCHAR2(128) NOT NULL,
--   "ISSUE_ID"     VARCHAR2(255) NOT NULL,
--   "DEVIATION_ID" VARCHAR2(255) NOT NULL,
--   "KPI1"         NUMBER(30, 5) NOT NULL,
--   "KPI2"         NUMBER(30, 5) NOT NULL,
--   "KPI3"         NUMBER(30, 5) NOT NULL,
--   "KPI4"         NUMBER(30, 5) NOT NULL,
--   "KPI5"         NUMBER(30, 5) NOT NULL,
--   "KPI6"         NUMBER(30, 5) NOT NULL,
--   "KPI7"         NUMBER(30, 5) NOT NULL,
--   "KPI8"         NUMBER(30, 5) NOT NULL,
--   "KPI9"         NUMBER(30, 5) NOT NULL,
--   "SPIKDATE"     DATE,
--   "CALC_DATE"    DATE,
--   "PLAN_DATE"    DATE,
--   CONSTRAINT PK_DEVIATION PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_DEV FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT UQ_DEVIATION_DEVIATION_ID UNIQUE (DEVIATION_ID)
-- );
--
-- /*==============================================================*/
-- /* Table: "DEVIATION_HISTORY"                                   */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_DEVIATION_HISTORY" (
--   "ID"              VARCHAR2(255),
--   "ISSUE_REVISION"  VARCHAR2(255) NOT NULL,
--
--   "DEVIATION_ID_PK" VARCHAR2(255) NOT NULL,
--   "REVISION_ID"     VARCHAR2(255) NOT NULL,
--   "TYPE_CODE"       VARCHAR2(128) NOT NULL,
--   "INDEX_CODE"      VARCHAR2(128),
--   "OBJECT"          VARCHAR2(128) NOT NULL,
--   "ISSUE_ID"        VARCHAR2(255) NOT NULL,
--   "DEVIATION_ID"    VARCHAR2(255) NOT NULL,
--   "KPI1"            NUMBER(30, 5) NOT NULL,
--   "KPI2"            NUMBER(30, 5) NOT NULL,
--   "KPI3"            NUMBER(30, 5) NOT NULL,
--   "KPI4"            NUMBER(30, 5) NOT NULL,
--   "KPI5"            NUMBER(30, 5) NOT NULL,
--   "KPI6"            NUMBER(30, 5) NOT NULL,
--   "KPI7"            NUMBER(30, 5) NOT NULL,
--   "KPI8"            NUMBER(30, 5) NOT NULL,
--   "KPI9"            NUMBER(30, 5) NOT NULL,
--   "SPIKDATE"        DATE,
--   "CALC_DATE"       DATE,
--   "PLAN_DATE"       DATE,
--   CONSTRAINT PK_DEVIATION_HISTORY PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_H_DEV_H FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_DEV_H_ID FOREIGN KEY ("DEVIATION_ID_PK") REFERENCES CBB_DEVIATION ("ID"),
--   CONSTRAINT UQ_DEVIATION_H_DEVIATION_ID UNIQUE (DEVIATION_ID)
-- );
--
-- /*==============================================================*/
-- /* Table: "REVISION"                                            */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_REVISION" (
--   "ID"          VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"    VARCHAR2(255) NOT NULL,
--   "CHANGE_DATE" TIMESTAMP     NOT NULL,
--   "AUTHOR_ID"   VARCHAR2(255) NOT NULL,
--   "OBJECT_TYPE" VARCHAR2(32)  NOT NULL,
--   CONSTRAINT PK_REVISION PRIMARY KEY ("ID"),
--   CONSTRAINT FK_REVISION_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_REVISION_AUTHOR FOREIGN KEY ("AUTHOR_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "REVISION_DIFF"                                       */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_REVISION_DIFF" (
--   "ID"          VARCHAR2(255) NOT NULL,
--   "REVISION_ID" VARCHAR2(255) NOT NULL,
--   "OLD_VALUE"   VARCHAR2(255),
--   "NEW_VALUE"   VARCHAR2(255),
--   "DATA_PATH"   VARCHAR2(1024) NOT NULL,
--   CONSTRAINT PK_REVISION_DIFF PRIMARY KEY ("ID"),
--   CONSTRAINT FK_REV_DIFF_REVISION FOREIGN KEY ("REVISION_ID") REFERENCES CBB_REVISION ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "MESSAGE_LOG"                                         */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_MESSAGE_LOG" (
--   "ID"          VARCHAR2(255)  NOT NULL,
--   "CREATE_DATE" TIMESTAMP      NOT NULL,
--   "AUTHOR_ID"   VARCHAR2(255)  NOT NULL,
--   "MSG_TEXT"    VARCHAR2(1024) NOT NULL,
--   "CHAIN_UID"   VARCHAR2(255)  NOT NULL,
--   CONSTRAINT PK_MESSAGE_LOG PRIMARY KEY ("ID"),
--   CONSTRAINT FK_MESSAGE_LOG_PA_ID FOREIGN KEY ("AUTHOR_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "ISSUE_HISTORY"                                       */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_ISSUE_HISTORY" (
--   "ID"            VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"      VARCHAR2(255) NOT NULL,
--   "REVISION_ID"   VARCHAR2(255) NOT NULL,
--   "TYPE_CODE"     VARCHAR2(128) NOT NULL,
--   "TITLE"         VARCHAR2(255),
--   "STATUS_CODE"   VARCHAR2(128) NOT NULL,
--   "RESULT_CODE"   VARCHAR2(128),
--   "DESCR"         VARCHAR2(255),
--   "PARENT_ID"     VARCHAR2(255),
--   "ROOT_ID"       VARCHAR2(255),
--   "CREATE_DATE"   DATE          NOT NULL,
--   "CLOSE_DATE"    DATE,
--   "PLAN_DATE"     DATE,
--   "AUTHOR_ID"     VARCHAR2(255) NOT NULL,
--   "MSG_CHAIN_UID" VARCHAR2(255),
--   "CLIENT_ID"     VARCHAR2(255),
--
--   CONSTRAINT PK_ISSUE_HISTORY PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISS_HIST_ID FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_ISS_HIST_REVISION FOREIGN KEY ("REVISION_ID") REFERENCES CBB_REVISION ("ID"),
--   CONSTRAINT FK_ISS_HIST_AUTHOR FOREIGN KEY ("AUTHOR_ID") REFERENCES CBB_PARTICIPANT ("ID"),
--   CONSTRAINT FK_ISS_HIST_CLIENT FOREIGN KEY ("CLIENT_ID") REFERENCES CBB_CLIENT ("ID")
-- );
-- /*==============================================================*/
-- /* Table: "DEAL_REQUEST"                                        */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_DEAL_REQUEST" (
--   "ID"                  VARCHAR2(255) NOT NULL,
--   "REVISION_ID"         VARCHAR2(255) NOT NULL,
--   "ACCOUNT_ID"          VARCHAR2(255),
--   "ACCOUNT_NUM"         VARCHAR2(255),
--   "PRODUCT_CODE"        VARCHAR2(128)  NOT NULL,
--   "CUR_ISO_CODE"        VARCHAR2(128)  NOT NULL,
--   "AMOUNT"              NUMBER(14, 2),
--   "FINAL_RATE"          NUMBER(7, 5),
--   "KM_RATE"             NUMBER(7, 5),
--   "PM_RATE"             NUMBER(7, 5),
--   "DEAL_START_DATE"     DATE          NOT NULL,
--   "DEAL_END_DATE"       DATE,
--   "DEAL_VALUT_DATE"     DATE          NOT NULL,
--   "DEAL_DURATION"       NUMBER(10),
--   "IS_WITHDRAWAL"       NUMBER(1)     NOT NULL,
--   "IS_TOPUP"            NUMBER(1)     NOT NULL,
--   "MIN_TOPUP_SUM"       NUMBER(14, 2),
--   "MAX_TOPUP_SUM"       NUMBER(14, 2),
--   "MAX_ACC_SUM"         NUMBER(14, 2),
--   "MAX_TOPUP_DATE"      DATE,
--   "TOPUP_PERIOD_CODE"   VARCHAR2(128),
--   "PERCENT_PERIOD_CODE" VARCHAR2(128),
--   "PERCENT_PERIOD_DAY"  NUMBER(2),
--   "PERCENT_PERIOD_DATE" DATE,
--   "PERCENT_PERIOD_VAL"  NUMBER(10),
--   "IS_CAPITALIZ"        NUMBER(1)     NOT NULL,
--   "CALC_STATUS_CODE"    VARCHAR2(128),
--   "ISSUE_ID"            VARCHAR2(255) NOT NULL,
--   "TER_DIVISION"        VARCHAR2(255),
--   "AGREEMENT_NUM"       VARCHAR2(255),
--   "IS_FOR_TREASURER"    NUMBER(1),
--   CONSTRAINT PK_DEAL_REQUEST PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_DEAL FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "CUSTOM_WITHDRAWAL"                                   */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_CUSTOM_WITHDRAWAL" (
--   "ID"          VARCHAR2(255) NOT NULL,
--   "REVISION_ID" VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"    VARCHAR2(255) NOT NULL,
--   "TYPE_CODE"   VARCHAR2(128)  NOT NULL,
--   "ROW_NUM"     NUMBER(5)     NOT NULL,
--   "DAY_FROM"    NUMBER(10)    NOT NULL,
--   "DAY_UNTIL"   NUMBER(10),
--   "RATE"        NUMBER(7, 5)  NOT NULL,
--   CONSTRAINT PK_CUSTOM_WITHDRAWAL PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID")
-- );
--
--
-- /*==============================================================*/
-- /* Table: "CBB_CALC_RESULT"                                     */
-- /*==============================================================*/
--
-- CREATE TABLE "UFS"."CBB_CALC_RESULT" (
--   "ID"                         VARCHAR2(255) NOT NULL,
--   "RQ_ID"                      VARCHAR2(255) NOT NULL,
--   "RQ_DATE"                    DATE          NOT NULL,
--   "RS_DATE"                    DATE          NOT NULL,
--   "AUTHOR_ID"                  VARCHAR2(255) NOT NULL,
--   "TER_DIVISION"               VARCHAR2(255),
--   "CALC_ID"                    VARCHAR2(255),
--   "ISSUE_ID"                   VARCHAR2(255) NOT NULL,
--   "CLIENT_ID"                  VARCHAR2(255) NOT NULL,
--   "PRODUCT_CODE"               VARCHAR2(128)  NOT NULL,
--   "CUR_ISO_CODE"               VARCHAR2(128)  NOT NULL,
--   "AMOUNT"                     NUMBER(12, 2),
--   "DEAL_START_DATE"            DATE          NOT NULL,
--   "DEAL_END_DATE"              DATE,
--   "DEAL_VALUT_DATE"            DATE          NOT NULL,
--   "DEAL_DURATION"              NUMBER(10),
--   "IS_WITHDRAWAL"              NUMBER(1)     NOT NULL,
--   "IS_TOPUP"                   NUMBER(1)     NOT NULL,
--   "STATUS"                     VARCHAR2(128) NOT NULL,
--   "RESPONCE_CODE_RS"           VARCHAR2(64),
--   "RESPONCE_DESC_RS"           VARCHAR2(256),
--   "ETS_MONTHLY"                NUMBER(10, 5),
--   "COMPOUNDING_SPREAD"         NUMBER(10, 5),
--   "ETS"                        NUMBER(10, 5),
--   "FOR_RATE"                   NUMBER(10, 5),
--   "FUND_RATE"                  NUMBER(10, 5),
--   "EVA"                        NUMBER(10, 5),
--   "OPEX"                       NUMBER(10, 5),
--   "EVA_TARGET"                 NUMBER(10, 5),
--   "EVA_INDICATIVE"             NUMBER(10, 5),
--   "DELTA_LIMIT"                NUMBER(17, 5),
--   "MARGINAL_INCOME"            NUMBER(17, 5),
--   "TARGET_MARGINAL_INCOME"     NUMBER(17, 5),
--   "INDICATIVE_MARGINAL_INCOME" NUMBER(17, 5),
--   "OPTION_PRICE"               NUMBER(10, 5),
--   "WITHDRAWAL_OPTION_PRICE"    NUMBER(10, 5),
--   "K_LIQ_FUND"                 NUMBER(10, 5),
--   "CALC_STATUS_CODE"           VARCHAR2(255),
--   "AGREEMENT_EMPLOYEE_ID"      VARCHAR2(255),
--   "AGREEMENT_LEVEL"            VARCHAR2(255),
--   "MAX_RATE"                   NUMBER(10, 5),
--   "IS_TREASURER_UTIL"          NUMBER(1),
--   "OPEN_LIMIT"                 NUMBER(12, 2),
--   "BOOKED_LIMIT"               NUMBER(12, 2),
--   CONSTRAINT PK_CALC_RESULT PRIMARY KEY ("ID"),
--   CONSTRAINT FK_ISSUE_CALC_RESULT FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "TEAM_MEMBER"                                         */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_TEAM_MEMBER" (
--   "ID"             VARCHAR2(255) NOT NULL,
--   "PARTICIPANT_ID" VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"       VARCHAR2(255) NOT NULL,
--   "TYPE"           VARCHAR2(128) NOT NULL,
--   CONSTRAINT PK_TEAM_MEMBER PRIMARY KEY ("ID"),
--   CONSTRAINT FK_TEAM_MEMBER_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_TEAM_MEMBER_PARTICIPANT FOREIGN KEY ("PARTICIPANT_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "PROTOCOL"                                            */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_PROTOCOL" (
--   "ID"          VARCHAR2(255) NOT NULL,
--   "CREATE_DATE" DATE          NOT NULL,
--   "AUTHOR_ID"   VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"    VARCHAR2(255) NOT NULL,
--   "STATUS"      VARCHAR2(128) NOT NULL,
--   CONSTRAINT PK_PROTOCOL PRIMARY KEY ("ID"),
--   CONSTRAINT FK_PROTOCOL_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_PROTOCOL_AUTHOR FOREIGN KEY ("AUTHOR_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "RESOLUTION"                                          */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_RESOLUTION" (
--   "ID"          VARCHAR2(255) NOT NULL,
--   "PROTOCOL_ID" VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"    VARCHAR2(255) NOT NULL,
--   "IS_INCLUDE"  NUMBER(1)     NOT NULL,
--   CONSTRAINT PK_RESOLUTION PRIMARY KEY ("ID"),
--   CONSTRAINT FK_RESOLUTION_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_RESOLUTION_PROTOCOL FOREIGN KEY ("PROTOCOL_ID") REFERENCES CBB_PROTOCOL ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "CBB_FACT_VIEW_ISSUE"                                 */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_FACT_VIEW_ISSUE" (
--   "ID"             VARCHAR2(255) NOT NULL,
--   "ISSUE_ID"       VARCHAR2(255) NOT NULL,
--   "PARTICIPANT_ID" VARCHAR2(255) NOT NULL,
--   "VIEW_DATE"      TIMESTAMP     NOT NULL,
--   CONSTRAINT PK_FACT_VIEW_ISSUE PRIMARY KEY ("ID"),
--   CONSTRAINT FK_FACT_VIEW_ISSUE_ISSUE FOREIGN KEY ("ISSUE_ID") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_FACT_VIEW_ISSUE_PARTICIP FOREIGN KEY ("PARTICIPANT_ID") REFERENCES CBB_PARTICIPANT ("ID")
-- );
--
-- /*==============================================================*/
-- /* Table: "CBB_RELATION"                                 */
-- /*==============================================================*/
-- CREATE TABLE "UFS"."CBB_RELATION" (
--   "ID"        VARCHAR2(255) NOT NULL,
--   "ISSUE_ID1" VARCHAR2(255) NOT NULL,
--   "ISSUE_ID2" VARCHAR2(255) NOT NULL,
--   "TYPE"      VARCHAR2(128) NOT NULL,
--   CONSTRAINT PK_RELATION PRIMARY KEY ("ID"),
--   CONSTRAINT FK_RELATION_ISSUE1 FOREIGN KEY ("ISSUE_ID1") REFERENCES CBB_ISSUE ("ID"),
--   CONSTRAINT FK_RELATION_ISSUE2 FOREIGN KEY ("ISSUE_ID2") REFERENCES CBB_ISSUE ("ID")
-- );