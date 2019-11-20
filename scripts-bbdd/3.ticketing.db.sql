--------------------------------------------------------
-- Archivo creado  - miércoles-octubre-17-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type CATEGORY_TYPE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "CATEGORY_TYPE" AS OBJECT (
   cal_hbid          number,
   cal_name            VARCHAR2(60) );

/
--------------------------------------------------------
--  DDL for Table CATEGORIA_SIP
--------------------------------------------------------

  CREATE TABLE "CATEGORIA_SIP" 
   (	"ID" NUMBER, 
	"TITLE" VARCHAR2(400 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table FO_HBREQACTIONSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "FO_HBREQACTIONSID_UNIQUE_KEY" 
   (	"REQACTIONID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table FO_HBREQLOADFILESID_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "FO_HBREQLOADFILESID_UNIQUE_KEY" 
   (	"FILEID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table FO_HBREQUESTSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "FO_HBREQUESTSID_UNIQUE_KEY" 
   (	"REQUESTID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table FO_HBUSERSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "FO_HBUSERSID_UNIQUE_KEY" 
   (	"USERID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBACTIONITEMTYPES
--------------------------------------------------------

  CREATE TABLE "HBACTIONITEMTYPES" 
   (	"IAI_HBID_ITEM" NUMBER, 
	"RAT_HBID_TYPE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBASSETENUMERATEDSTRPROPERTIES
--------------------------------------------------------

  CREATE TABLE "HBASSETENUMERATEDSTRPROPERTIES" 
   (	"AEP_PRO_PROPERTY" NUMBER, 
	"AEP_VALUE" VARCHAR2(4000 BYTE), 
	"AEP_INDEX" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBASSETS
--------------------------------------------------------

  CREATE TABLE "HBASSETS" 
   (	"ASS_HBID" NUMBER, 
	"ASS_HBVERSION" NUMBER(*,0), 
	"ASS_PST_TYPE" NUMBER, 
	"ASS_IDENTIFIER" VARCHAR2(30 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBASSETSDATA
--------------------------------------------------------

  CREATE TABLE "HBASSETSDATA" 
   (	"ASD_INDEX" NUMBER(*,0) DEFAULT -1, 
	"ASD_VALUE" VARCHAR2(4000 BYTE) DEFAULT ' ', 
	"ASD_PRO_HBID" NUMBER, 
	"ASD_HBID" NUMBER(*,0), 
	"ASD_HBVERSION" NUMBER(*,0), 
	"ASD_ASS_HBID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBASSETTYPES
--------------------------------------------------------

  CREATE TABLE "HBASSETTYPES" 
   (	"AST_HBID" NUMBER, 
	"AST_HBVERSION" NUMBER(*,0), 
	"AST_NAME" VARCHAR2(30 BYTE) DEFAULT ' '
   ) ;
   
--------------------------------------------------------
--  DDL for Table HBCATEGORIES
--------------------------------------------------------

  CREATE TABLE "HBCATEGORIES" 
   (	"CAT_HBID" NUMBER(*,0), 
	"RQT_HBID_TEMPLATECATEGORY" NUMBER(*,0), 
	"CAT_TEMPLATEREQUESTDESCRIPTION" VARCHAR2(4000 BYTE) DEFAULT ' ', 
	"CAT_EXPECTEDRESOLUTIONMINUTES" NUMBER(*,0) DEFAULT 0, 
	"CAT_EXPECTRESOLUTIONMINREAL" NUMBER(*,0) DEFAULT 0, 
	"CAT_OBJECTIVEPERCENTAGE" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBCATEGORYLEVELS
--------------------------------------------------------

  CREATE TABLE "HBCATEGORYLEVELS" 
   (	"CAL_HBID" NUMBER(*,0), 
	"CAL_HBVERSION" NUMBER(*,0), 
	"CAL_PARENT" NUMBER(*,0), 
	"CAL_LEVEL" NUMBER(*,0), 
	"CAL_NAME" VARCHAR2(60 BYTE) DEFAULT ' ', 
	"CAL_ISCATEGORY" NUMBER(*,0) DEFAULT 0, 
	"AUTOASSIGN" VARCHAR2(1 BYTE) DEFAULT 'N'
   ) ;
--------------------------------------------------------
--  DDL for Table HBCATEGORYSCALINGGROUPS
--------------------------------------------------------

  CREATE TABLE "HBCATEGORYSCALINGGROUPS" 
   (	"SGP_HBID" NUMBER(*,0), 
	"CAT_HBID" NUMBER(*,0), 
	"CSG_PRIORITY" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBCOMPANIES
--------------------------------------------------------

  CREATE TABLE "HBCOMPANIES" 
   (	"CPN_HBID" NUMBER, 
	"CPN_HBVERSION" NUMBER(*,0), 
	"CPN_NAME" VARCHAR2(30 BYTE), 
	"CPN_OTHERS" VARCHAR2(4000 BYTE) DEFAULT ' ', 
	"CPN_PHONE" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"CPN_FAX" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"CPN_ADRESS" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"CPN_CITY" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"CPN_STATE" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"CPN_CP" VARCHAR2(30 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBCONFIGURATION
--------------------------------------------------------

  CREATE TABLE "HBCONFIGURATION" 
   (	"CNF_HBID" NUMBER, 
	"CNF_HBVERSION" NUMBER(*,0), 
	"CNF_FUNCTIONNAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"CNF_VALUE" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"CNF_INTERNALNAME" VARCHAR2(10 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBDEPARTMENTS
--------------------------------------------------------

  CREATE TABLE "HBDEPARTMENTS" 
   (	"DEP_HBID" NUMBER(*,0), 
	"DEP_NAME" VARCHAR2(30 BYTE), 
	"DEP_HBVERSION" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HB_ENTIDADESEXTERNAS
--------------------------------------------------------

  CREATE TABLE "HB_ENTIDADESEXTERNAS" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(400 BYTE), 
	"EMAIL" VARCHAR2(400 BYTE), 
	"TELEFONO" VARCHAR2(300 BYTE), 
	"USER_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBHEADERDESCRIPTIONS
--------------------------------------------------------

  CREATE TABLE "HBHEADERDESCRIPTIONS" 
   (	"HED_HBID" NUMBER, 
	"HED_HBVERSION" NUMBER(*,0), 
	"HED_NAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"HED_TITLEPAGE" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"HED_CLASENAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"HED_BUTTONSEARCH" NUMBER(*,0) DEFAULT 0, 
	"HED_ENTERSEARCH" NUMBER(*,0) DEFAULT 0, 
	"HED_BUTTONEXCEL" NUMBER(*,0) DEFAULT 0, 
	"HED_COLUMNSHEAD" NUMBER(*,0) DEFAULT 0, 
	"HED_BUTTONRESET" NUMBER(*,0) DEFAULT 0, 
	"HED_ORIGINAL" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBHEADERFIELDS
--------------------------------------------------------

  CREATE TABLE "HBHEADERFIELDS" 
   (	"HEF_HBID" NUMBER, 
	"HEF_HBVERSION" NUMBER(*,0), 
	"HEF_NAME" VARCHAR2(30 BYTE), 
	"HEF_PROPERTYNAME" VARCHAR2(255 BYTE), 
	"HEF_ADITIONALNAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"HEF_VISIBLELENGTHFIELD" NUMBER(*,0), 
	"HEF_INTERNALLENGTHFIELD" NUMBER(*,0), 
	"HEF_VISIBLE" NUMBER(*,0) DEFAULT 0, 
	"HED_HEF_TYPE" NUMBER, 
	"HEF_INDEX" NUMBER(*,0), 
	"HEF_BUTTONSELECT" NUMBER(*,0) DEFAULT 0, 
	"HEF_CLASENAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"HEF_BUTTONSELECTPROPERTYNAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"HEF_SPECIFICFIELD" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBINVOICEABLEACTIONITEMS
--------------------------------------------------------

  CREATE TABLE "HBINVOICEABLEACTIONITEMS" 
   (	"IAI_HBID" NUMBER, 
	"IAI_HBTYPEDISCRIMINATOR" VARCHAR2(50 BYTE), 
	"IAI_HBVERSION" NUMBER(*,0), 
	"IAI_EXTERNALID" VARCHAR2(30 BYTE), 
	"IAI_NAME" VARCHAR2(30 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table HBLISTINGCOLUMNDESCRIPTIONS
--------------------------------------------------------

  CREATE TABLE "HBLISTINGCOLUMNDESCRIPTIONS" 
   (	"LCD_HBID" NUMBER, 
	"LCD_HBVERSION" NUMBER(*,0), 
	"LCD_NAME" VARCHAR2(30 BYTE), 
	"LCD_PROPERTYNAME" VARCHAR2(255 BYTE), 
	"LCD_ADITIONALWIDTH" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"LCD_ADITIONALNAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"LCD_VISIBLE" NUMBER(*,0) DEFAULT 0, 
	"LCD_LTD_TYPE" NUMBER, 
	"LCD_INDEX" NUMBER(*,0), 
	"LCD_ICON" NUMBER(*,0) DEFAULT 0, 
	"LCD_NOWRAP" NUMBER(*,0) DEFAULT 0, 
	"LCD_PROPERTYNAMEBYORDER" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"LCD_SPECIFIC" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBLISTINGDESCRIPTIONS
--------------------------------------------------------

  CREATE TABLE "HBLISTINGDESCRIPTIONS" 
   (	"LTD_HBID" NUMBER, 
	"LTD_HBVERSION" NUMBER(*,0), 
	"LTD_NAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"LTD_TITLEPAGE" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"LTD_CLASENAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"LTD_ORIGINAL" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBLISTINGFORMENUS
--------------------------------------------------------

  CREATE TABLE "HBLISTINGFORMENUS" 
   (	"LFM_HBID" NUMBER, 
	"LFM_HBVERSION" NUMBER(*,0), 
	"LFM_PROPERTYREQUEST" VARCHAR2(255 BYTE), 
	"LFM_FITERUSERID" NUMBER(*,0) DEFAULT 0, 
	"LFM_MYSCALINGGROUPS" NUMBER(*,0) DEFAULT 0, 
	"LFM_MEN_TYPE" NUMBER, 
	"LFM_INDEX" NUMBER(*,0), 
	"LFM_LISTINGDESC" NUMBER, 
	"LFM_REQUESTSUBSTATE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBMACHINES
--------------------------------------------------------

  CREATE TABLE "HBMACHINES" 
   (	"MAC_HBID" NUMBER, 
	"MAC_HBVERSION" NUMBER(*,0), 
	"MAC_EXTERNALID" VARCHAR2(30 BYTE), 
	"MAC_WARRANTYID" VARCHAR2(30 BYTE), 
	"MAC_WARRANTYSTART" DATE, 
	"MAC_WARRANTYEND" DATE, 
	"MAC_MAINTENANCEID" VARCHAR2(30 BYTE), 
	"MAC_MAINTENANCESTART" DATE, 
	"MAC_MAINTENANCEEND" DATE, 
	"USR_HBID_CLIENT" NUMBER(*,0), 
	"MAC_HBID_ZONE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBMAGAZINES
--------------------------------------------------------

  CREATE TABLE "HBMAGAZINES" 
   (	"MAG_HBID" NUMBER, 
	"MAG_HBVERSION" NUMBER(*,0), 
	"MAG_NAME" VARCHAR2(30 BYTE), 
	"MAG_DEFAULT" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBMENUS
--------------------------------------------------------

  CREATE TABLE "HBMENUS" 
   (	"MEN_HBID" NUMBER, 
	"MEN_HBVERSION" NUMBER(*,0), 
	"MEN_NAME" VARCHAR2(255 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBPARAMETRIZATIONS
--------------------------------------------------------

  CREATE TABLE "HBPARAMETRIZATIONS" 
   (	"PMT_HBID" NUMBER, 
	"PMT_HBVERSION" NUMBER(*,0), 
	"PMT_NAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"PMT_INTERNALNAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"PMT_ACTIVATION" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBPROPERTIES
--------------------------------------------------------

  CREATE TABLE "HBPROPERTIES" 
   (	"PRO_HBID" NUMBER, 
	"PRO_HBVERSION" NUMBER(*,0), 
	"PRO_HBTYPEDISCRIMINATOR" VARCHAR2(10 BYTE), 
	"PRO_NAME" VARCHAR2(30 BYTE), 
	"PRO_REQUIRED" NUMBER(*,0), 
	"PRO_PST_TYPE" NUMBER, 
	"PRO_INDEX" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBPROPERTYSETTYPES
--------------------------------------------------------

  CREATE TABLE "HBPROPERTYSETTYPES" 
   (	"PST_HBID" NUMBER, 
	"PST_HBVERSION" NUMBER(*,0), 
	"PST_NAME" VARCHAR2(30 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTACTIONOPERATIONS
--------------------------------------------------------

  CREATE TABLE "HBREQUESTACTIONOPERATIONS" 
   (	"RAO_HBID" NUMBER(*,0), 
	"RAO_HBVERSION" NUMBER(*,0), 
	"RAO_DESCRIPTION" VARCHAR2(4000 BYTE), 
	"RAO_HASDESCRIPTION" NUMBER(*,0) DEFAULT 0, 
	"RAO_MINUTES" NUMBER(*,0), 
	"RAO_HASMINUTES" NUMBER(*,0) DEFAULT 0, 
	"RAO_AMOUNT" FLOAT(126), 
	"RAO_HASAMOUNT" NUMBER(*,0) DEFAULT 0, 
	"RAO_UNITS" NUMBER(*,0), 
	"RAO_HASUNITS" NUMBER(*,0) DEFAULT 0, 
	"RQA_HBID_ACTION" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTACTIONOPERATIONTYPES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTACTIONOPERATIONTYPES" 
   (	"RAT_HBID" NUMBER, 
	"RAT_HBVERSION" NUMBER(*,0), 
	"RAT_NAME" VARCHAR2(30 BYTE), 
	"RAT_DESCRIPTION" VARCHAR2(4000 BYTE), 
	"RAT_EXTERNALID" NUMBER, 
	"RAT_MUSTHAVEMINUTES" NUMBER(*,0) DEFAULT 0, 
	"RAT_MUSTHAVEUNITS" NUMBER(*,0) DEFAULT 0, 
	"RAT_MUSTHAVEAMOUNT" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTACTIONS
--------------------------------------------------------

  CREATE TABLE "HBREQUESTACTIONS" 
   (	"RQA_HBID" NUMBER(*,0), 
	"RQA_HBVERSION" NUMBER(*,0), 
	"RQA_DESCRIPTION" VARCHAR2(4000 BYTE), 
	"RQA_CREATIONDATETIME" DATE, 
	"RQA_REQUESTACTIONDATETIME" DATE, 
	"RQA_ELAPSEDSECONDS" NUMBER(*,0), 
	"USR_HBID_AGENT" NUMBER(*,0), 
	"RQT_HBID_REQUEST" NUMBER(*,0), 
	"RAT_HBID_TYPE" NUMBER, 
	"RSS_HBID_SUBSTATE" NUMBER DEFAULT 0, 
	"RSS_SLA_STOP" NUMBER(*,0) DEFAULT 0, 
	"OPERADOR" VARCHAR2(200 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTACTIONTYPES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTACTIONTYPES" 
   (	"RAT_HBID" NUMBER, 
	"RAT_HBVERSION" NUMBER(*,0), 
	"RAT_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RAT_DEFAULT" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTLOADFILES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTLOADFILES" 
   (	"RLF_HBID" NUMBER, 
	"RLF_HBVERSION" NUMBER(*,0), 
	"RLF_DESCRIPTION" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RLF_FILENAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"RQT_HBID_REQUEST" NUMBER(*,0), 
	"USR_HBID_USERLOADFILE" NUMBER(*,0), 
	"RLF_INTRODUTIONDATETIME" DATE DEFAULT CURRENT_DATE
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTNUMBER_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "HBREQUESTNUMBER_UNIQUE_KEY" 
   (	"REQUESTNUMBERID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTORIGINS
--------------------------------------------------------

  CREATE TABLE "HBREQUESTORIGINS" 
   (	"ROG_HBID" NUMBER(*,0), 
	"ROG_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"ROG_HBVERSION" NUMBER(*,0), 
	"ROG_DEFAULT" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTPARAMETRIZATIONS
--------------------------------------------------------

  CREATE TABLE "HBREQUESTPARAMETRIZATIONS" 
   (	"RQP_HBID" NUMBER, 
	"RQP_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RQP_HBVERSION" NUMBER(*,0), 
	"RQP_ID_SEARCHESPECICATION" NUMBER, 
	"RQP_ID_REQLISTINGDESCRIPTION" NUMBER, 
	"RQP_OPERATIONROL" VARCHAR2(30 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTPRIORITIES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTPRIORITIES" 
   (	"RPT_HBID" NUMBER(*,0), 
	"RPT_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RPT_PRIORITY" NUMBER(*,0), 
	"RPT_HBVERSION" NUMBER(*,0), 
	"RPT_DEFAULT" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTS
--------------------------------------------------------

  CREATE TABLE "HBREQUESTS" 
   (	"RQT_HBID" NUMBER(*,0), 
	"RQT_HBVERSION" NUMBER(*,0), 
	"DESCRIPTION" VARCHAR2(4000 BYTE),
	"TITLE"	VARCHAR2(600 BYTE),
	"FIRST_NAME"	VARCHAR2(600 BYTE),
	"EMAIL"	VARCHAR2(300 BYTE),
	"PHONE"	VARCHAR2(100 BYTE),
	"USER_ADDRESS"	VARCHAR2(500 BYTE),
	"ADDRESS_STRING"	VARCHAR2(500 BYTE),
	"ADDRESS_ID"	NUMBER,
	"SERVICE_NOTICE"	VARCHAR2(4000 BYTE),
	"USR_HBID_REQUESTER" NUMBER(*,0), 
	"USR_HBID_INTRODUCER" NUMBER(*,0), 
	"USR_HBID_MANAGER" NUMBER(*,0), 
	"RST_ID" NUMBER(*,0), 
	"ROG_HBID" NUMBER(*,0), 
	"RTY_HBID" NUMBER(*,0), 
	"RPT_HBID" NUMBER(*,0), 
	"SGP_HBID" NUMBER(*,0), 
	"CAT_HBID" NUMBER(*,0), 
	"RQT_INTRODUCTIONDATETIME" DATE DEFAULT CURRENT_DATE, 
	"RQT_CREATIONDESCRIPTION" VARCHAR2(4000 BYTE) DEFAULT ' ', 
	"RQT_REQUESTDATETIME" DATE, 
	"RQT_REQUESTDATE" DATE, 
	"RQT_REQUESTNUMBER" NUMBER(*,0), 
	"RQT_EXPECTEDRESOLUTIONDATETIME" DATE, 
	"RQT_CLOSEDATETIME" DATE, 
	"RQT_CLOSEDATE" DATE, 
	"MAC_HBID_MACHINE" NUMBER, 
	"NUMREFCASD" NUMBER DEFAULT 0, 
	"IDCASD" NUMBER DEFAULT 0, 
	"RQT_REQUESTHOUR" NUMBER(*,0) DEFAULT 0, 
	"RQT_RESOLVEDDATATIME" DATE, 
	"RQT_PARENT_ID" NUMBER(*,0), 
	"RQT_PUBLIC" VARCHAR2(1 BYTE) DEFAULT 'N', 
	"RQT_VALIDATED" VARCHAR2(1 BYTE) DEFAULT 'N', 
	"RQT_GEOLOCATION" "SYSTEM"."SDO_GEOMETRY", 
	"RQT_GESTORDATE" DATE, 
	"RQT_EXTERNODATE" DATE, 
	"RQT_EXTERNOID" NUMBER, 
	"OPERADOR" VARCHAR2(40 BYTE), 
	"SOLICITA_RESPUESTA" VARCHAR2(1 BYTE) DEFAULT 'N', 
	"RQT_INSPECTORDATE" DATE, 
	"RQT_INSPECTORID" VARCHAR2(60 BYTE), 
	"RQT_ZONAINSPECCIONID" NUMBER, 
	"INTERNAL_STATUS" NUMBER, 
	"NOTES" VARCHAR2(2000 BYTE), 
	"ID_CITA" NUMBER(38,0), 
	"TOKEN" VARCHAR2(40 BYTE), 
	"USER_ID" NUMBER, 
	"ID_CAT_SIP" NUMBER(6,0)
   ) ;
 

   COMMENT ON COLUMN "HBREQUESTS"."OPERADOR" IS 'Formato para poder agrupar por usuarios de 010 = idusuarioticketing || '':'' || usuariogcz ';
--------------------------------------------------------
--  DDL for Table HBREQUESTSTATES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTSTATES" 
   (	"RST_HBID" NUMBER, 
	"RST_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RST_HBVERSION" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTSUBSTATES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTSUBSTATES" 
   (	"RSS_HBID" NUMBER, 
	"RSS_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RSS_ID_REQUESTSTATE" NUMBER, 
	"RSS_ISSTATE" NUMBER(*,0) DEFAULT 0, 
	"RSS_SLA_STOP" NUMBER(*,0) DEFAULT 0, 
	"RSS_HBVERSION" NUMBER(*,0), 
	"RSS_SENDMAIL" NUMBER(*,0) DEFAULT 0, 
	"RSS_MAILTO" VARCHAR2(255 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTTOKEN
--------------------------------------------------------

  CREATE TABLE "HBREQUESTTOKEN" 
   (	"RQT_HBID" NUMBER, 
	"RQA_HBID" NUMBER, 
	"TOKEN" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table HBREQUESTTYPES
--------------------------------------------------------

  CREATE TABLE "HBREQUESTTYPES" 
   (	"RTY_HBID" NUMBER(*,0), 
	"RTY_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"RTY_HBVERSION" NUMBER(*,0), 
	"RTY_DEFAULT" NUMBER(*,0) DEFAULT 0, 
	"RTY_TICKER" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBSCALINGGROUPS
--------------------------------------------------------

  CREATE TABLE "HBSCALINGGROUPS" 
   (	"SGP_HBID" NUMBER(*,0), 
	"SGP_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"SGP_HBVERSION" NUMBER(*,0), 
	"SGP_EMAILGROUP" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"SGP_AUTONOTIFICATION" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBSCALINGGROUPUSERS
--------------------------------------------------------

  CREATE TABLE "HBSCALINGGROUPUSERS" 
   (	"USR_HBID" NUMBER(*,0), 
	"SGP_HBID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBSEARCHESPECIFICATIONS
--------------------------------------------------------

  CREATE TABLE "HBSEARCHESPECIFICATIONS" 
   (	"SEP_HBID" NUMBER, 
	"SEP_HBVERSION" NUMBER(*,0), 
	"SEP_NAME" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"SEP_TITLEPAGE" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"SEP_OPERATIONLINK" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"SEP_OPERATIONROL" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"SEP_LTD_LISTDESCSEARCH" NUMBER(*,0), 
	"SEP_LTD_LISTDESCEXCEL" NUMBER(*,0), 
	"SEP_HED_HEADER" NUMBER(*,0), 
	"SEP_POPUP" NUMBER(*,0) DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBSTOCKMANAGERS
--------------------------------------------------------

  CREATE TABLE "HBSTOCKMANAGERS" 
   (	"SMG_HBID" NUMBER, 
	"SMG_HBVERSION" NUMBER(*,0), 
	"SMG_HBTYPEDISCRIMINATOR" VARCHAR2(10 BYTE), 
	"SGM_REL" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBSTOCKMOVEMENTS
--------------------------------------------------------

  CREATE TABLE "HBSTOCKMOVEMENTS" 
   (	"SMV_HBID" NUMBER, 
	"SMV_HBVERSION" NUMBER(*,0), 
	"SMV_DATETIME" DATE, 
	"SMV_COUNT" NUMBER(*,0), 
	"SMG_HBID_DESTINATION" NUMBER, 
	"SMT_HBID_TYPE" NUMBER, 
	"SMG_HBID_SOURCE" NUMBER, 
	"SMV_ASS_ASSET" NUMBER DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBSTOCKMOVEMENTTYPE
--------------------------------------------------------

  CREATE TABLE "HBSTOCKMOVEMENTTYPE" 
   (	"SMT_HBID" NUMBER, 
	"SMT_NAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"SMT_HBVERSION" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HBSTOCKS
--------------------------------------------------------

  CREATE TABLE "HBSTOCKS" 
   (	"STK_HBID" NUMBER, 
	"STK_HBVERSION" NUMBER(*,0), 
	"STK_COUNT" NUMBER(*,0), 
	"SMG_HBID_OWNER" NUMBER, 
	"STK_ASS_ASSET" NUMBER DEFAULT 0
   ) ;
--------------------------------------------------------
--  DDL for Table HBSTOCKSZONES
--------------------------------------------------------

  CREATE TABLE "HBSTOCKSZONES" 
   (	"STK_HBID_STOCS" NUMBER, 
	"ZON_HBID_ZONE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBUSERS
--------------------------------------------------------

  CREATE TABLE "HBUSERS" 
   (	"USR_HBID" NUMBER(*,0), 
	"USR_USERNAME" VARCHAR2(125 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"USR_HBID_DEPARTMENT" NUMBER(*,0), 
	"PER_FIRSTNAME" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"PER_LASTNAME" VARCHAR2(40 BYTE) DEFAULT ' ', 
	"PER_PHONE1" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"PER_PHONE2" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"PER_EXT" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"PER_MOVILE" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"CLI_PLACEMENT" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"CLI_EMAIL" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"CLI_COMPANY" VARCHAR2(50 BYTE) DEFAULT ' ', 
	"CLI_TREATMENT" VARCHAR2(20 BYTE) DEFAULT ' ', 
	"CLI_POSITION" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"USR_HBVERSION" NUMBER(*,0), 
	"USR_HBTYPEDISCRIMINATOR" VARCHAR2(10 BYTE), 
	"IDCASD" NUMBER DEFAULT 0, 
	"USR_HBID_COMPANY" NUMBER DEFAULT 1, 
	"CLI_ID_ZONE" NUMBER, 
	"CLI_ID_CATEGORY" NUMBER(*,0), 
	"CLI_CATEGORY" NUMBER(*,0) DEFAULT 0, 
	"CLI_OTHERS" VARCHAR2(4000 BYTE) DEFAULT ' ', 
	"PER_ADDRESS" VARCHAR2(255 BYTE) DEFAULT ' ', 
	"PER_CITY" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"PER_COUNTRY" VARCHAR2(30 BYTE) DEFAULT ' ', 
	"PER_ZIPCODE" VARCHAR2(10 BYTE) DEFAULT ' ', 
	"PER_NIF" VARCHAR2(10 BYTE) DEFAULT ' '
   ) ;
--------------------------------------------------------
--  DDL for Table HBUSERS_ORIGIN
--------------------------------------------------------

  CREATE TABLE "HBUSERS_ORIGIN" 
   (	"ID_USER" NUMBER, 
	"ID_ORIGIN" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBUSERTZONES
--------------------------------------------------------

  CREATE TABLE "HBUSERTZONES" 
   (	"USR_HBID_USERS" NUMBER, 
	"ZON_HBID_ZONE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table HBZONES
--------------------------------------------------------

  CREATE TABLE "HBZONES" 
   (	"ZON_HBID" NUMBER, 
	"ZON_HBVERSION" NUMBER(*,0), 
	"ZON_NAME" VARCHAR2(30 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table TICKETING_ACCESTOKEN
--------------------------------------------------------
  CREATE TABLE TICKETING_ACCESTOKEN
   (
   	 "ID_USUARIO" NUMBER(14,0), 
	 "EXPIRATION" DATE, 
	 "TOKEN" VARCHAR2(100 BYTE)
   );
--------------------------------------------------------
--  DDL for Table HIBERNATE_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "HIBERNATE_UNIQUE_KEY" 
   (	"NEXT_HI" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table REPORT_UNIQUE_KEY
--------------------------------------------------------

  CREATE TABLE "REPORT_UNIQUE_KEY" 
   (	"INTERNALREPORTID" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table RESPUESTASTIPO
--------------------------------------------------------

  CREATE TABLE "RESPUESTASTIPO" 
   (	"USER_ID" NUMBER, 
	"TIPO" NUMBER, 
	"TEXTO" VARCHAR2(1000 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQCOUNTPERCATEGORYLEVEL
--------------------------------------------------------

  CREATE TABLE "RPTREQCOUNTPERCATEGORYLEVEL" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"NOMBRECATEGORIA" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERDAY
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERDAY" 
   (	"INTERNALREPORTID" NUMBER, 
	"FECHA" DATE, 
	"DIAMES" NUMBER(*,0), 
	"DIASEMANA" NUMBER(*,0), 
	"SOLICITUDES" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERDEPARTMENT
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERDEPARTMENT" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"NOMBREDEPARTAMENTO" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERGROUP
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERGROUP" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"NOMBREGRUPO" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERHOUR
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERHOUR" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"HORA" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERORIGIN
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERORIGIN" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"NOMBREORIGEN" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTCOUNTPERTYPE
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTCOUNTPERTYPE" 
   (	"INTERNALREPORTID" NUMBER, 
	"SOLICITUDES" NUMBER(*,0), 
	"NOMBRETIPO" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table RPTREQUESTTIMEPERCATEGORYLEVEL
--------------------------------------------------------

  CREATE TABLE "RPTREQUESTTIMEPERCATEGORYLEVEL" 
   (	"INTERNALREPORTID" NUMBER, 
	"HORAS" FLOAT(126), 
	"NOMBRECATEGORIA" VARCHAR2(100 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Sequence SEQ_ENTIDADEXTERNA
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_ENTIDADEXTERNA"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 34 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_HBREQUESTLOADFILES
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_HBREQUESTLOADFILES"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 2147483744 CACHE 20 NOORDER  NOCYCLE ;


--------------------------------------------------------
--  DDL for Index DR$RQT_CREATIONDESCRIPTION$X
--------------------------------------------------------

  CREATE INDEX "DR$RQT_CREATIONDESCRIPTION$X" ON "DR$RQT_CREATIONDESCRIPTION$I" ("TOKEN_TEXT", "TOKEN_TYPE", "TOKEN_FIRST", "TOKEN_LAST", "TOKEN_COUNT") 
  ;
--------------------------------------------------------
--  DDL for Index HB_ENTIDADESEXTERNAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HB_ENTIDADESEXTERNAS_PK" ON "HB_ENTIDADESEXTERNAS" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTACTIONS_INDEX1
--------------------------------------------------------

  CREATE INDEX "HBREQUESTACTIONS_INDEX1" ON "HBREQUESTACTIONS" ("RQT_HBID_REQUEST") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTLOADFILES_INDEX1
--------------------------------------------------------

  CREATE INDEX "HBREQUESTLOADFILES_INDEX1" ON "HBREQUESTLOADFILES" ("RQT_HBID_REQUEST") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTLOADFILES_INDEX2
--------------------------------------------------------

  CREATE INDEX "HBREQUESTLOADFILES_INDEX2" ON "HBREQUESTLOADFILES" ("RLF_FILENAME") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTLOADFILES_INDEX3
--------------------------------------------------------

  CREATE INDEX "HBREQUESTLOADFILES_INDEX3" ON "HBREQUESTLOADFILES" ("USR_HBID_USERLOADFILE", "RLF_FILENAME", "RQT_HBID_REQUEST") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTLOADFILES_LOADPROFILE
--------------------------------------------------------

  CREATE INDEX "HBREQUESTLOADFILES_LOADPROFILE" ON "HBREQUESTLOADFILES" ("USR_HBID_USERLOADFILE") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTS_INDEX1
--------------------------------------------------------

  CREATE INDEX "HBREQUESTS_INDEX1" ON "HBREQUESTS" ("USR_HBID_REQUESTER") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTS_INDEX2
--------------------------------------------------------

  CREATE INDEX "HBREQUESTS_INDEX2" ON "HBREQUESTS" ("USR_HBID_INTRODUCER") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTS_INDEX3
--------------------------------------------------------

  CREATE INDEX "HBREQUESTS_INDEX3" ON "HBREQUESTS" ("USR_HBID_MANAGER") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HBREQUESTS_PK" ON "HBREQUESTS" ("RQT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index HBREQUESTTOKEN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HBREQUESTTOKEN_PK" ON "HBREQUESTTOKEN" ("RQT_HBID", "RQA_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index HBUSERS_CLI_ID_CATEGORY
--------------------------------------------------------

  CREATE INDEX "HBUSERS_CLI_ID_CATEGORY" ON "HBUSERS" ("CLI_ID_CATEGORY") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBASSETS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBASSETS" ON "HBASSETS" ("ASS_IDENTIFIER") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBASSETTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBASSETTYPES" ON "HBASSETTYPES" ("AST_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBCOMPANIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBCOMPANIES" ON "HBCOMPANIES" ("CPN_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBHEADERDESCRIPTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBHEADERDESCRIPTIONS" ON "HBHEADERDESCRIPTIONS" ("HED_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBHEADERFIELDS2
--------------------------------------------------------

  CREATE INDEX "IX_HBHEADERFIELDS2" ON "HBHEADERFIELDS" ("HED_HEF_TYPE", "HEF_INDEX") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBINVACTIONITEMS_EXTERNALID
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBINVACTIONITEMS_EXTERNALID" ON "HBINVOICEABLEACTIONITEMS" ("IAI_EXTERNALID") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBINVACTIONITEMS_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBINVACTIONITEMS_NAME" ON "HBINVOICEABLEACTIONITEMS" ("IAI_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBLISTINGDESCRIPTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBLISTINGDESCRIPTIONS" ON "HBLISTINGDESCRIPTIONS" ("LTD_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBLISTINGFORMENUS
--------------------------------------------------------

  CREATE INDEX "IX_HBLISTINGFORMENUS" ON "HBLISTINGFORMENUS" ("LFM_MEN_TYPE", "LFM_INDEX") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBMACHINES_EXTERNALID
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBMACHINES_EXTERNALID" ON "HBMACHINES" ("MAC_EXTERNALID") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBMAGAZINES
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBMAGAZINES" ON "HBMAGAZINES" ("MAG_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBMENUS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBMENUS" ON "HBMENUS" ("MEN_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBPARAMETRIZATIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBPARAMETRIZATIONS" ON "HBPARAMETRIZATIONS" ("PMT_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBPROPERTIES
--------------------------------------------------------

  CREATE INDEX "IX_HBPROPERTIES" ON "HBPROPERTIES" ("PRO_PST_TYPE", "PRO_INDEX") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBPROPERTIES2
--------------------------------------------------------

  CREATE INDEX "IX_HBPROPERTIES2" ON "HBLISTINGCOLUMNDESCRIPTIONS" ("LCD_LTD_TYPE", "LCD_INDEX") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBREQACTIONOPERATIONTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBREQACTIONOPERATIONTYPES" ON "HBREQUESTACTIONOPERATIONTYPES" ("RAT_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBREQACTIONOPERATIONTYPES_1
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBREQACTIONOPERATIONTYPES_1" ON "HBREQUESTACTIONOPERATIONTYPES" ("RAT_EXTERNALID") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBREQUESTACTIONTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBREQUESTACTIONTYPES" ON "HBREQUESTACTIONTYPES" ("RAT_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBREQUESTPARAMETRIZATIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBREQUESTPARAMETRIZATIONS" ON "HBREQUESTPARAMETRIZATIONS" ("RQP_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBREQUESTSTATESNAMEUNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBREQUESTSTATESNAMEUNIQUE" ON "HBREQUESTSTATES" ("RST_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBSCALINGGROUP
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBSCALINGGROUP" ON "HBSCALINGGROUPS" ("SGP_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBSEARCHESPECIFICATIONS1
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBSEARCHESPECIFICATIONS1" ON "HBSEARCHESPECIFICATIONS" ("SEP_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBSEARCHESPECIFICATIONS2
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBSEARCHESPECIFICATIONS2" ON "HBSEARCHESPECIFICATIONS" ("SEP_OPERATIONLINK", "SEP_OPERATIONROL") 
  ;
--------------------------------------------------------
--  DDL for Index IX_HBZONE_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_HBZONE_NAME" ON "HBZONES" ("ZON_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_NAME" ON "HBDEPARTMENTS" ("DEP_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_NAMEUNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_NAMEUNIQUE" ON "HBREQUESTSUBSTATES" ("RSS_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_PARENTNAMEUNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_PARENTNAMEUNIQUE" ON "HBCATEGORYLEVELS" ("CAL_PARENT", "CAL_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_PST_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_PST_NAME" ON "HBPROPERTYSETTYPES" ("PST_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index IX_USERNAMEUNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "IX_USERNAMEUNIQUE" ON "HBUSERS" ("USR_USERNAME") 
  ;
--------------------------------------------------------
--  DDL for Index PK_FO_HBREQLFILESID_UNIQUE_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FO_HBREQLFILESID_UNIQUE_KEY" ON "FO_HBREQLOADFILESID_UNIQUE_KEY" ("FILEID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_FO_HBREQUESTSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FO_HBREQUESTSID_UNIQUE_KEY" ON "FO_HBREQUESTSID_UNIQUE_KEY" ("REQUESTID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_FO_HBRQACTIONSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_FO_HBRQACTIONSID_UNIQUE_KEY" ON "FO_HBREQACTIONSID_UNIQUE_KEY" ("REQACTIONID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBACTIONITEMTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBACTIONITEMTYPES" ON "HBACTIONITEMTYPES" ("IAI_HBID_ITEM", "RAT_HBID_TYPE") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBASSETENUMSTRPROPERTIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBASSETENUMSTRPROPERTIES" ON "HBASSETENUMERATEDSTRPROPERTIES" ("AEP_PRO_PROPERTY", "AEP_INDEX") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBASSETS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBASSETS" ON "HBASSETS" ("ASS_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBASSETTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBASSETTYPES" ON "HBASSETTYPES" ("AST_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBCATEGORIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBCATEGORIES" ON "HBCATEGORIES" ("CAT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBCATEGORYLEVELS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBCATEGORYLEVELS" ON "HBCATEGORYLEVELS" ("CAL_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBCATEGORYSCALINGGROUPS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBCATEGORYSCALINGGROUPS" ON "HBCATEGORYSCALINGGROUPS" ("SGP_HBID", "CAT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBCOMPANIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBCOMPANIES" ON "HBCOMPANIES" ("CPN_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBCONFIGURATION
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBCONFIGURATION" ON "HBCONFIGURATION" ("CNF_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBDEPARTMENTS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBDEPARTMENTS" ON "HBDEPARTMENTS" ("DEP_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBHEADERDESCRIPTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBHEADERDESCRIPTIONS" ON "HBHEADERDESCRIPTIONS" ("HED_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBHEADERFIELDS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBHEADERFIELDS" ON "HBHEADERFIELDS" ("HEF_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBINVOICEABLEACTIONITEMS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBINVOICEABLEACTIONITEMS" ON "HBINVOICEABLEACTIONITEMS" ("IAI_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBLISTINGCOLUMNDESCRIPTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBLISTINGCOLUMNDESCRIPTIONS" ON "HBLISTINGCOLUMNDESCRIPTIONS" ("LCD_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBLISTINGDESCRIPTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBLISTINGDESCRIPTIONS" ON "HBLISTINGDESCRIPTIONS" ("LTD_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBLISTINGFORMENUS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBLISTINGFORMENUS" ON "HBLISTINGFORMENUS" ("LFM_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBMACHINES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBMACHINES" ON "HBMACHINES" ("MAC_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBMAGAZINES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBMAGAZINES" ON "HBMAGAZINES" ("MAG_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBMENUS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBMENUS" ON "HBMENUS" ("MEN_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBPARAMETRIZATIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBPARAMETRIZATIONS" ON "HBPARAMETRIZATIONS" ("PMT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBPROPERTIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBPROPERTIES" ON "HBPROPERTIES" ("PRO_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBPROPERTYSETTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBPROPERTYSETTYPES" ON "HBPROPERTYSETTYPES" ("PST_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQACTIONOPERATIONTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQACTIONOPERATIONTYPES" ON "HBREQUESTACTIONOPERATIONTYPES" ("RAT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTACTIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTACTIONS" ON "HBREQUESTACTIONS" ("RQA_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTACTIONTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTACTIONTYPES" ON "HBREQUESTACTIONTYPES" ("RAT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTLOADFILES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTLOADFILES" ON "HBREQUESTLOADFILES" ("RLF_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTNUMBER_UNIQUE_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTNUMBER_UNIQUE_KEY" ON "HBREQUESTNUMBER_UNIQUE_KEY" ("REQUESTNUMBERID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTORIGIN
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTORIGIN" ON "HBREQUESTORIGINS" ("ROG_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTPARAMETRIZATIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTPARAMETRIZATIONS" ON "HBREQUESTPARAMETRIZATIONS" ("RQP_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTPRIORITIES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTPRIORITIES" ON "HBREQUESTPRIORITIES" ("RPT_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTSTATES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTSTATES" ON "HBREQUESTSTATES" ("RST_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTSUBSTATES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTSUBSTATES" ON "HBREQUESTSUBSTATES" ("RSS_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBREQUESTTYPES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBREQUESTTYPES" ON "HBREQUESTTYPES" ("RTY_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSCALINGGROUP
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSCALINGGROUP" ON "HBSCALINGGROUPS" ("SGP_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSCALINGGROUPUSERS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSCALINGGROUPUSERS" ON "HBSCALINGGROUPUSERS" ("USR_HBID", "SGP_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSEARCHESPECIFICATIONS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSEARCHESPECIFICATIONS" ON "HBSEARCHESPECIFICATIONS" ("SEP_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSTOCKMANAGERS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSTOCKMANAGERS" ON "HBSTOCKMANAGERS" ("SMG_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSTOCKS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSTOCKS" ON "HBSTOCKS" ("STK_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBSTOCKSZONES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBSTOCKSZONES" ON "HBSTOCKSZONES" ("STK_HBID_STOCS", "ZON_HBID_ZONE") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBUSERS
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBUSERS" ON "HBUSERS" ("USR_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBUSERSID_UNIQUE_KEY
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBUSERSID_UNIQUE_KEY" ON "FO_HBUSERSID_UNIQUE_KEY" ("USERID") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBUSERTZONES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBUSERTZONES" ON "HBUSERTZONES" ("USR_HBID_USERS", "ZON_HBID_ZONE") 
  ;
--------------------------------------------------------
--  DDL for Index PK_HBZONES
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_HBZONES" ON "HBZONES" ("ZON_HBID") 
  ;
--------------------------------------------------------
--  DDL for Index PROPUESTAS_PREPART_TEMA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PROPUESTAS_PREPART_TEMA_PK" ON "CATEGORIA_SIP" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index RQT_CREATIONDESCRIPTION
--------------------------------------------------------

  CREATE INDEX "RQT_CREATIONDESCRIPTION" ON "HBREQUESTS" ("RQT_CREATIONDESCRIPTION") 
   INDEXTYPE IS "CTXSYS"."CONTEXT" ;
--------------------------------------------------------
--  DDL for Index SYS_C0070526
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0070526" ON "HBASSETSDATA" ("ASD_HBID") 
  ;

--------------------------------------------------------
--  Constraints for Table CATEGORIA_SIP
--------------------------------------------------------

  ALTER TABLE "CATEGORIA_SIP" ADD CONSTRAINT "PROPUESTAS_PREPART_TEMA_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CATEGORIA_SIP" MODIFY ("ID" NOT NULL ENABLE);

--------------------------------------------------------
--  Constraints for Table FO_HBREQACTIONSID_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "FO_HBREQACTIONSID_UNIQUE_KEY" ADD CONSTRAINT "PK_FO_HBRQACTIONSID_UNIQUE_KEY" PRIMARY KEY ("REQACTIONID") ENABLE;
 
  ALTER TABLE "FO_HBREQACTIONSID_UNIQUE_KEY" MODIFY ("REQACTIONID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FO_HBREQLOADFILESID_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "FO_HBREQLOADFILESID_UNIQUE_KEY" ADD CONSTRAINT "PK_FO_HBREQLFILESID_UNIQUE_KEY" PRIMARY KEY ("FILEID") ENABLE;
 
  ALTER TABLE "FO_HBREQLOADFILESID_UNIQUE_KEY" MODIFY ("FILEID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FO_HBREQUESTSID_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "FO_HBREQUESTSID_UNIQUE_KEY" ADD CONSTRAINT "PK_FO_HBREQUESTSID_UNIQUE_KEY" PRIMARY KEY ("REQUESTID") ENABLE;
 
  ALTER TABLE "FO_HBREQUESTSID_UNIQUE_KEY" MODIFY ("REQUESTID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FO_HBUSERSID_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "FO_HBUSERSID_UNIQUE_KEY" ADD CONSTRAINT "PK_HBUSERSID_UNIQUE_KEY" PRIMARY KEY ("USERID") ENABLE;
 
  ALTER TABLE "FO_HBUSERSID_UNIQUE_KEY" MODIFY ("USERID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBACTIONITEMTYPES
--------------------------------------------------------

  ALTER TABLE "HBACTIONITEMTYPES" ADD CONSTRAINT "PK_HBACTIONITEMTYPES" PRIMARY KEY ("IAI_HBID_ITEM", "RAT_HBID_TYPE") ENABLE;
 
  ALTER TABLE "HBACTIONITEMTYPES" MODIFY ("IAI_HBID_ITEM" NOT NULL ENABLE);
 
  ALTER TABLE "HBACTIONITEMTYPES" MODIFY ("RAT_HBID_TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBASSETENUMERATEDSTRPROPERTIES
--------------------------------------------------------

  ALTER TABLE "HBASSETENUMERATEDSTRPROPERTIES" ADD CONSTRAINT "PK_HBASSETENUMSTRPROPERTIES" PRIMARY KEY ("AEP_PRO_PROPERTY", "AEP_INDEX") ENABLE;
 
  ALTER TABLE "HBASSETENUMERATEDSTRPROPERTIES" MODIFY ("AEP_PRO_PROPERTY" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETENUMERATEDSTRPROPERTIES" MODIFY ("AEP_VALUE" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETENUMERATEDSTRPROPERTIES" MODIFY ("AEP_INDEX" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBASSETS
--------------------------------------------------------

  ALTER TABLE "HBASSETS" ADD CONSTRAINT "IX_HBASSETS" UNIQUE ("ASS_IDENTIFIER") ENABLE;
 
  ALTER TABLE "HBASSETS" ADD CONSTRAINT "PK_HBASSETS" PRIMARY KEY ("ASS_HBID") ENABLE;
 
  ALTER TABLE "HBASSETS" MODIFY ("ASS_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETS" MODIFY ("ASS_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETS" MODIFY ("ASS_PST_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETS" MODIFY ("ASS_IDENTIFIER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBASSETSDATA
--------------------------------------------------------

  ALTER TABLE "HBASSETSDATA" MODIFY ("ASD_INDEX" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETSDATA" MODIFY ("ASD_VALUE" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETSDATA" MODIFY ("ASD_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETSDATA" MODIFY ("ASD_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETSDATA" ADD PRIMARY KEY ("ASD_HBID") ENABLE;
--------------------------------------------------------
--  Constraints for Table HBASSETTYPES
--------------------------------------------------------

  ALTER TABLE "HBASSETTYPES" ADD CONSTRAINT "IX_HBASSETTYPES" UNIQUE ("AST_NAME") ENABLE;
 
  ALTER TABLE "HBASSETTYPES" ADD CONSTRAINT "PK_HBASSETTYPES" PRIMARY KEY ("AST_HBID") ENABLE;
 
  ALTER TABLE "HBASSETTYPES" MODIFY ("AST_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETTYPES" MODIFY ("AST_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBASSETTYPES" MODIFY ("AST_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBCATEGORIES
--------------------------------------------------------

  ALTER TABLE "HBCATEGORIES" ADD CONSTRAINT "PK_HBCATEGORIES" PRIMARY KEY ("CAT_HBID") ENABLE;
 
  ALTER TABLE "HBCATEGORIES" MODIFY ("CAT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORIES" MODIFY ("CAT_TEMPLATEREQUESTDESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORIES" MODIFY ("CAT_EXPECTEDRESOLUTIONMINUTES" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORIES" MODIFY ("CAT_EXPECTRESOLUTIONMINREAL" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORIES" MODIFY ("CAT_OBJECTIVEPERCENTAGE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBCATEGORYLEVELS
--------------------------------------------------------

  ALTER TABLE "HBCATEGORYLEVELS" ADD CONSTRAINT "IX_PARENTNAMEUNIQUE" UNIQUE ("CAL_PARENT", "CAL_NAME") ENABLE;
 
  ALTER TABLE "HBCATEGORYLEVELS" ADD CONSTRAINT "PK_HBCATEGORYLEVELS" PRIMARY KEY ("CAL_HBID") ENABLE;
 
  ALTER TABLE "HBCATEGORYLEVELS" MODIFY ("CAL_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYLEVELS" MODIFY ("CAL_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYLEVELS" MODIFY ("CAL_LEVEL" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYLEVELS" MODIFY ("CAL_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYLEVELS" MODIFY ("CAL_ISCATEGORY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBCATEGORYSCALINGGROUPS
--------------------------------------------------------

  ALTER TABLE "HBCATEGORYSCALINGGROUPS" ADD CONSTRAINT "PK_HBCATEGORYSCALINGGROUPS" PRIMARY KEY ("SGP_HBID", "CAT_HBID") ENABLE;
 
  ALTER TABLE "HBCATEGORYSCALINGGROUPS" MODIFY ("SGP_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYSCALINGGROUPS" MODIFY ("CAT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCATEGORYSCALINGGROUPS" MODIFY ("CSG_PRIORITY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBCOMPANIES
--------------------------------------------------------

  ALTER TABLE "HBCOMPANIES" ADD CONSTRAINT "PK_HBCOMPANIES" PRIMARY KEY ("CPN_HBID") ENABLE;
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_OTHERS" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_PHONE" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_FAX" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_ADRESS" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_CITY" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_STATE" NOT NULL ENABLE);
 
  ALTER TABLE "HBCOMPANIES" MODIFY ("CPN_CP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBCONFIGURATION
--------------------------------------------------------

  ALTER TABLE "HBCONFIGURATION" ADD CONSTRAINT "PK_HBCONFIGURATION" PRIMARY KEY ("CNF_HBID") ENABLE;
 
  ALTER TABLE "HBCONFIGURATION" MODIFY ("CNF_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBCONFIGURATION" MODIFY ("CNF_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBCONFIGURATION" MODIFY ("CNF_FUNCTIONNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBCONFIGURATION" MODIFY ("CNF_VALUE" NOT NULL ENABLE);
 
  ALTER TABLE "HBCONFIGURATION" MODIFY ("CNF_INTERNALNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBDEPARTMENTS
--------------------------------------------------------

  ALTER TABLE "HBDEPARTMENTS" ADD CONSTRAINT "PK_HBDEPARTMENTS" PRIMARY KEY ("DEP_HBID") ENABLE;
 
  ALTER TABLE "HBDEPARTMENTS" MODIFY ("DEP_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBDEPARTMENTS" MODIFY ("DEP_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBDEPARTMENTS" MODIFY ("DEP_HBVERSION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HB_ENTIDADESEXTERNAS
--------------------------------------------------------

  ALTER TABLE "HB_ENTIDADESEXTERNAS" ADD CONSTRAINT "HB_ENTIDADESEXTERNAS_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "HB_ENTIDADESEXTERNAS" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "HB_ENTIDADESEXTERNAS" MODIFY ("NOMBRE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBHEADERDESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "HBHEADERDESCRIPTIONS" ADD CONSTRAINT "IX_HBHEADERDESCRIPTIONS" UNIQUE ("HED_NAME") ENABLE;
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" ADD CONSTRAINT "PK_HBHEADERDESCRIPTIONS" PRIMARY KEY ("HED_HBID") ENABLE;
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_TITLEPAGE" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_CLASENAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_BUTTONSEARCH" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_ENTERSEARCH" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_BUTTONEXCEL" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_COLUMNSHEAD" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_BUTTONRESET" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERDESCRIPTIONS" MODIFY ("HED_ORIGINAL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBHEADERFIELDS
--------------------------------------------------------

  ALTER TABLE "HBHEADERFIELDS" ADD CONSTRAINT "PK_HBHEADERFIELDS" PRIMARY KEY ("HEF_HBID") ENABLE;
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_PROPERTYNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_ADITIONALNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_VISIBLELENGTHFIELD" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_INTERNALLENGTHFIELD" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_VISIBLE" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_BUTTONSELECT" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_CLASENAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_BUTTONSELECTPROPERTYNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBHEADERFIELDS" MODIFY ("HEF_SPECIFICFIELD" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBINVOICEABLEACTIONITEMS
--------------------------------------------------------

  ALTER TABLE "HBINVOICEABLEACTIONITEMS" ADD CONSTRAINT "IX_HBINVACTIONITEMS_EXTERNALID" UNIQUE ("IAI_EXTERNALID") ENABLE;
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" ADD CONSTRAINT "IX_HBINVACTIONITEMS_NAME" UNIQUE ("IAI_NAME") ENABLE;
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" ADD CONSTRAINT "PK_HBINVOICEABLEACTIONITEMS" PRIMARY KEY ("IAI_HBID") ENABLE;
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" MODIFY ("IAI_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" MODIFY ("IAI_HBTYPEDISCRIMINATOR" NOT NULL ENABLE);
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" MODIFY ("IAI_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" MODIFY ("IAI_EXTERNALID" NOT NULL ENABLE);
 
  ALTER TABLE "HBINVOICEABLEACTIONITEMS" MODIFY ("IAI_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBLISTINGCOLUMNDESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" ADD CONSTRAINT "PK_HBLISTINGCOLUMNDESCRIPTIONS" PRIMARY KEY ("LCD_HBID") ENABLE;
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_PROPERTYNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_ADITIONALWIDTH" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_ADITIONALNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_VISIBLE" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_ICON" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_NOWRAP" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_PROPERTYNAMEBYORDER" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGCOLUMNDESCRIPTIONS" MODIFY ("LCD_SPECIFIC" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBLISTINGDESCRIPTIONS
--------------------------------------------------------

  ALTER TABLE "HBLISTINGDESCRIPTIONS" ADD CONSTRAINT "IX_HBLISTINGDESCRIPTIONS" UNIQUE ("LTD_NAME") ENABLE;
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" ADD CONSTRAINT "PK_HBLISTINGDESCRIPTIONS" PRIMARY KEY ("LTD_HBID") ENABLE;
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_TITLEPAGE" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_CLASENAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGDESCRIPTIONS" MODIFY ("LTD_ORIGINAL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBLISTINGFORMENUS
--------------------------------------------------------

  ALTER TABLE "HBLISTINGFORMENUS" ADD CONSTRAINT "PK_HBLISTINGFORMENUS" PRIMARY KEY ("LFM_HBID") ENABLE;
 
  ALTER TABLE "HBLISTINGFORMENUS" MODIFY ("LFM_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGFORMENUS" MODIFY ("LFM_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGFORMENUS" MODIFY ("LFM_PROPERTYREQUEST" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGFORMENUS" MODIFY ("LFM_FITERUSERID" NOT NULL ENABLE);
 
  ALTER TABLE "HBLISTINGFORMENUS" MODIFY ("LFM_MYSCALINGGROUPS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBMACHINES
--------------------------------------------------------

  ALTER TABLE "HBMACHINES" ADD CONSTRAINT "IX_HBMACHINES_EXTERNALID" UNIQUE ("MAC_EXTERNALID") ENABLE;
 
  ALTER TABLE "HBMACHINES" ADD CONSTRAINT "PK_HBMACHINES" PRIMARY KEY ("MAC_HBID") ENABLE;
 
  ALTER TABLE "HBMACHINES" MODIFY ("MAC_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBMACHINES" MODIFY ("MAC_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBMACHINES" MODIFY ("MAC_EXTERNALID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBMAGAZINES
--------------------------------------------------------

  ALTER TABLE "HBMAGAZINES" ADD CONSTRAINT "IX_HBMAGAZINES" UNIQUE ("MAG_NAME") ENABLE;
 
  ALTER TABLE "HBMAGAZINES" ADD CONSTRAINT "PK_HBMAGAZINES" PRIMARY KEY ("MAG_HBID") ENABLE;
 
  ALTER TABLE "HBMAGAZINES" MODIFY ("MAG_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBMAGAZINES" MODIFY ("MAG_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBMAGAZINES" MODIFY ("MAG_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBMAGAZINES" MODIFY ("MAG_DEFAULT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBMENUS
--------------------------------------------------------

  ALTER TABLE "HBMENUS" ADD CONSTRAINT "IX_HBMENUS" UNIQUE ("MEN_NAME") ENABLE;
 
  ALTER TABLE "HBMENUS" ADD CONSTRAINT "PK_HBMENUS" PRIMARY KEY ("MEN_HBID") ENABLE;
 
  ALTER TABLE "HBMENUS" MODIFY ("MEN_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBMENUS" MODIFY ("MEN_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBMENUS" MODIFY ("MEN_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBPARAMETRIZATIONS
--------------------------------------------------------

  ALTER TABLE "HBPARAMETRIZATIONS" ADD CONSTRAINT "IX_HBPARAMETRIZATIONS" UNIQUE ("PMT_NAME") ENABLE;
 
  ALTER TABLE "HBPARAMETRIZATIONS" ADD CONSTRAINT "PK_HBPARAMETRIZATIONS" PRIMARY KEY ("PMT_HBID") ENABLE;
 
  ALTER TABLE "HBPARAMETRIZATIONS" MODIFY ("PMT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBPARAMETRIZATIONS" MODIFY ("PMT_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBPARAMETRIZATIONS" MODIFY ("PMT_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBPARAMETRIZATIONS" MODIFY ("PMT_INTERNALNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBPARAMETRIZATIONS" MODIFY ("PMT_ACTIVATION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBPROPERTIES
--------------------------------------------------------

  ALTER TABLE "HBPROPERTIES" ADD CONSTRAINT "PK_HBPROPERTIES" PRIMARY KEY ("PRO_HBID") ENABLE;
 
  ALTER TABLE "HBPROPERTIES" MODIFY ("PRO_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTIES" MODIFY ("PRO_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTIES" MODIFY ("PRO_HBTYPEDISCRIMINATOR" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTIES" MODIFY ("PRO_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTIES" MODIFY ("PRO_REQUIRED" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBPROPERTYSETTYPES
--------------------------------------------------------

  ALTER TABLE "HBPROPERTYSETTYPES" ADD CONSTRAINT "IX_PST_NAME" UNIQUE ("PST_NAME") ENABLE;
 
  ALTER TABLE "HBPROPERTYSETTYPES" ADD CONSTRAINT "PK_HBPROPERTYSETTYPES" PRIMARY KEY ("PST_HBID") ENABLE;
 
  ALTER TABLE "HBPROPERTYSETTYPES" MODIFY ("PST_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTYSETTYPES" MODIFY ("PST_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBPROPERTYSETTYPES" MODIFY ("PST_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTACTIONOPERATIONS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_DESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HASDESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_MINUTES" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HASMINUTES" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_AMOUNT" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HASAMOUNT" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_UNITS" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONS" MODIFY ("RAO_HASUNITS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTACTIONOPERATIONTYPES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" ADD CONSTRAINT "IX_HBREQACTIONOPERATIONTYPES" UNIQUE ("RAT_NAME") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" ADD CONSTRAINT "IX_HBREQACTIONOPERATIONTYPES_1" UNIQUE ("RAT_EXTERNALID") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" ADD CONSTRAINT "PK_HBREQACTIONOPERATIONTYPES" PRIMARY KEY ("RAT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_DESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_EXTERNALID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_MUSTHAVEMINUTES" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_MUSTHAVEUNITS" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONOPERATIONTYPES" MODIFY ("RAT_MUSTHAVEAMOUNT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTACTIONS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTACTIONS" ADD CONSTRAINT "PK_HBREQUESTACTIONS" PRIMARY KEY ("RQA_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_DESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_CREATIONDATETIME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_REQUESTACTIONDATETIME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQA_ELAPSEDSECONDS" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("USR_HBID_AGENT" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RQT_HBID_REQUEST" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RSS_HBID_SUBSTATE" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONS" MODIFY ("RSS_SLA_STOP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTACTIONTYPES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTACTIONTYPES" ADD CONSTRAINT "IX_HBREQUESTACTIONTYPES" UNIQUE ("RAT_NAME") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONTYPES" ADD CONSTRAINT "PK_HBREQUESTACTIONTYPES" PRIMARY KEY ("RAT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTACTIONTYPES" MODIFY ("RAT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONTYPES" MODIFY ("RAT_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONTYPES" MODIFY ("RAT_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTACTIONTYPES" MODIFY ("RAT_DEFAULT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTLOADFILES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTLOADFILES" ADD CONSTRAINT "PK_HBREQUESTLOADFILES" PRIMARY KEY ("RLF_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RLF_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RLF_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RLF_DESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RLF_FILENAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RQT_HBID_REQUEST" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTLOADFILES" MODIFY ("RLF_INTRODUTIONDATETIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTNUMBER_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "HBREQUESTNUMBER_UNIQUE_KEY" ADD CONSTRAINT "PK_HBREQUESTNUMBER_UNIQUE_KEY" PRIMARY KEY ("REQUESTNUMBERID") ENABLE;
 
  ALTER TABLE "HBREQUESTNUMBER_UNIQUE_KEY" MODIFY ("REQUESTNUMBERID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTORIGINS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTORIGINS" ADD CONSTRAINT "PK_HBREQUESTORIGIN" PRIMARY KEY ("ROG_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTORIGINS" MODIFY ("ROG_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTORIGINS" MODIFY ("ROG_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTORIGINS" MODIFY ("ROG_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTORIGINS" MODIFY ("ROG_DEFAULT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTPARAMETRIZATIONS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" ADD CONSTRAINT "IX_HBREQUESTPARAMETRIZATIONS" UNIQUE ("RQP_NAME") ENABLE;
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" ADD CONSTRAINT "PK_HBREQUESTPARAMETRIZATIONS" PRIMARY KEY ("RQP_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" MODIFY ("RQP_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" MODIFY ("RQP_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" MODIFY ("RQP_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" MODIFY ("RQP_ID_SEARCHESPECICATION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPARAMETRIZATIONS" MODIFY ("RQP_OPERATIONROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTPRIORITIES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTPRIORITIES" ADD CONSTRAINT "PK_HBREQUESTPRIORITIES" PRIMARY KEY ("RPT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTPRIORITIES" MODIFY ("RPT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPRIORITIES" MODIFY ("RPT_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPRIORITIES" MODIFY ("RPT_PRIORITY" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPRIORITIES" MODIFY ("RPT_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTPRIORITIES" MODIFY ("RPT_DEFAULT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "HBREQUESTS_PK" PRIMARY KEY ("RQT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_PUBLIC" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_VALIDATED" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_INTRODUCTIONDATETIME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_CREATIONDESCRIPTION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("NUMREFCASD" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("IDCASD" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTS" MODIFY ("RQT_REQUESTHOUR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTSTATES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTSTATES" ADD CONSTRAINT "IX_HBREQUESTSTATESNAMEUNIQUE" UNIQUE ("RST_NAME") ENABLE;
 
  ALTER TABLE "HBREQUESTSTATES" ADD CONSTRAINT "PK_HBREQUESTSTATES" PRIMARY KEY ("RST_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTSTATES" MODIFY ("RST_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSTATES" MODIFY ("RST_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSTATES" MODIFY ("RST_HBVERSION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTSUBSTATES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTSUBSTATES" ADD CONSTRAINT "IX_NAMEUNIQUE" UNIQUE ("RSS_NAME") ENABLE;
 
  ALTER TABLE "HBREQUESTSUBSTATES" ADD CONSTRAINT "PK_HBREQUESTSUBSTATES" PRIMARY KEY ("RSS_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_ID_REQUESTSTATE" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_ISSTATE" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_SLA_STOP" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_SENDMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTSUBSTATES" MODIFY ("RSS_MAILTO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTTOKEN
--------------------------------------------------------

  ALTER TABLE "HBREQUESTTOKEN" ADD CONSTRAINT "HBREQUESTTOKEN_PK" PRIMARY KEY ("RQT_HBID", "RQA_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTTOKEN" MODIFY ("RQT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTTOKEN" MODIFY ("RQA_HBID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBREQUESTTYPES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTTYPES" ADD CONSTRAINT "PK_HBREQUESTTYPES" PRIMARY KEY ("RTY_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTTYPES" MODIFY ("RTY_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTTYPES" MODIFY ("RTY_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTTYPES" MODIFY ("RTY_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTTYPES" MODIFY ("RTY_DEFAULT" NOT NULL ENABLE);
 
  ALTER TABLE "HBREQUESTTYPES" MODIFY ("RTY_TICKER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSCALINGGROUPS
--------------------------------------------------------

  ALTER TABLE "HBSCALINGGROUPS" ADD CONSTRAINT "IX_HBSCALINGGROUP" UNIQUE ("SGP_NAME") ENABLE;
 
  ALTER TABLE "HBSCALINGGROUPS" ADD CONSTRAINT "PK_HBSCALINGGROUP" PRIMARY KEY ("SGP_HBID") ENABLE;
 
  ALTER TABLE "HBSCALINGGROUPS" MODIFY ("SGP_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSCALINGGROUPS" MODIFY ("SGP_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBSCALINGGROUPS" MODIFY ("SGP_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSCALINGGROUPS" MODIFY ("SGP_EMAILGROUP" NOT NULL ENABLE);
 
  ALTER TABLE "HBSCALINGGROUPS" MODIFY ("SGP_AUTONOTIFICATION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSCALINGGROUPUSERS
--------------------------------------------------------

  ALTER TABLE "HBSCALINGGROUPUSERS" ADD CONSTRAINT "PK_HBSCALINGGROUPUSERS" PRIMARY KEY ("USR_HBID", "SGP_HBID") ENABLE;
 
  ALTER TABLE "HBSCALINGGROUPUSERS" MODIFY ("USR_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSCALINGGROUPUSERS" MODIFY ("SGP_HBID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSEARCHESPECIFICATIONS
--------------------------------------------------------

  ALTER TABLE "HBSEARCHESPECIFICATIONS" ADD CONSTRAINT "IX_HBSEARCHESPECIFICATIONS1" UNIQUE ("SEP_NAME") ENABLE;
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" ADD CONSTRAINT "IX_HBSEARCHESPECIFICATIONS2" UNIQUE ("SEP_OPERATIONLINK", "SEP_OPERATIONROL") ENABLE;
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" ADD CONSTRAINT "PK_HBSEARCHESPECIFICATIONS" PRIMARY KEY ("SEP_HBID") ENABLE;
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_TITLEPAGE" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_OPERATIONLINK" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_OPERATIONROL" NOT NULL ENABLE);
 
  ALTER TABLE "HBSEARCHESPECIFICATIONS" MODIFY ("SEP_POPUP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSTOCKMANAGERS
--------------------------------------------------------

  ALTER TABLE "HBSTOCKMANAGERS" ADD CONSTRAINT "PK_HBSTOCKMANAGERS" PRIMARY KEY ("SMG_HBID") ENABLE;
 
  ALTER TABLE "HBSTOCKMANAGERS" MODIFY ("SMG_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMANAGERS" MODIFY ("SMG_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMANAGERS" MODIFY ("SMG_HBTYPEDISCRIMINATOR" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMANAGERS" MODIFY ("SGM_REL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSTOCKMOVEMENTS
--------------------------------------------------------

  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMV_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMV_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMV_DATETIME" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMV_COUNT" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMG_HBID_DESTINATION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMT_HBID_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTS" MODIFY ("SMV_ASS_ASSET" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSTOCKMOVEMENTTYPE
--------------------------------------------------------

  ALTER TABLE "HBSTOCKMOVEMENTTYPE" MODIFY ("SMT_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTTYPE" MODIFY ("SMT_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKMOVEMENTTYPE" MODIFY ("SMT_HBVERSION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSTOCKS
--------------------------------------------------------

  ALTER TABLE "HBSTOCKS" ADD CONSTRAINT "PK_HBSTOCKS" PRIMARY KEY ("STK_HBID") ENABLE;
 
  ALTER TABLE "HBSTOCKS" MODIFY ("STK_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKS" MODIFY ("STK_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKS" MODIFY ("STK_COUNT" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKS" MODIFY ("SMG_HBID_OWNER" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKS" MODIFY ("STK_ASS_ASSET" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBSTOCKSZONES
--------------------------------------------------------

  ALTER TABLE "HBSTOCKSZONES" ADD CONSTRAINT "PK_HBSTOCKSZONES" PRIMARY KEY ("STK_HBID_STOCS", "ZON_HBID_ZONE") ENABLE;
 
  ALTER TABLE "HBSTOCKSZONES" MODIFY ("STK_HBID_STOCS" NOT NULL ENABLE);
 
  ALTER TABLE "HBSTOCKSZONES" MODIFY ("ZON_HBID_ZONE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBUSERS
--------------------------------------------------------

  ALTER TABLE "HBUSERS" ADD CONSTRAINT "IX_USERNAMEUNIQUE" UNIQUE ("USR_USERNAME") ENABLE;
 
  ALTER TABLE "HBUSERS" ADD CONSTRAINT "PK_HBUSERS" PRIMARY KEY ("USR_HBID") ENABLE;
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_HBID_DEPARTMENT" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_FIRSTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_LASTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_PHONE1" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_PHONE2" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_EXT" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_MOVILE" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_PLACEMENT" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_COMPANY" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_TREATMENT" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_POSITION" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_HBTYPEDISCRIMINATOR" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("IDCASD" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("USR_HBID_COMPANY" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_CATEGORY" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("CLI_OTHERS" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_ADDRESS" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_CITY" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_COUNTRY" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_ZIPCODE" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERS" MODIFY ("PER_NIF" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBUSERTZONES
--------------------------------------------------------

  ALTER TABLE "HBUSERTZONES" ADD CONSTRAINT "PK_HBUSERTZONES" PRIMARY KEY ("USR_HBID_USERS", "ZON_HBID_ZONE") ENABLE;
 
  ALTER TABLE "HBUSERTZONES" MODIFY ("USR_HBID_USERS" NOT NULL ENABLE);
 
  ALTER TABLE "HBUSERTZONES" MODIFY ("ZON_HBID_ZONE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table HBZONES
--------------------------------------------------------

  ALTER TABLE "HBZONES" ADD CONSTRAINT "IX_HBZONE_NAME" UNIQUE ("ZON_NAME") ENABLE;
 
  ALTER TABLE "HBZONES" ADD CONSTRAINT "PK_HBZONES" PRIMARY KEY ("ZON_HBID") ENABLE;
 
  ALTER TABLE "HBZONES" MODIFY ("ZON_HBID" NOT NULL ENABLE);
 
  ALTER TABLE "HBZONES" MODIFY ("ZON_HBVERSION" NOT NULL ENABLE);
 
  ALTER TABLE "HBZONES" MODIFY ("ZON_NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table REPORT_UNIQUE_KEY
--------------------------------------------------------

  ALTER TABLE "REPORT_UNIQUE_KEY" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQCOUNTPERCATEGORYLEVEL
--------------------------------------------------------

  ALTER TABLE "RPTREQCOUNTPERCATEGORYLEVEL" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQCOUNTPERCATEGORYLEVEL" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQCOUNTPERCATEGORYLEVEL" MODIFY ("NOMBRECATEGORIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERDAY
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERDAY" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDAY" MODIFY ("FECHA" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDAY" MODIFY ("DIAMES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDAY" MODIFY ("DIASEMANA" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDAY" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERDEPARTMENT
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERDEPARTMENT" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDEPARTMENT" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERDEPARTMENT" MODIFY ("NOMBREDEPARTAMENTO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERGROUP
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERGROUP" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERGROUP" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERGROUP" MODIFY ("NOMBREGRUPO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERHOUR
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERHOUR" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERHOUR" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERHOUR" MODIFY ("HORA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERORIGIN
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERORIGIN" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERORIGIN" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERORIGIN" MODIFY ("NOMBREORIGEN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTCOUNTPERTYPE
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTCOUNTPERTYPE" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERTYPE" MODIFY ("SOLICITUDES" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTCOUNTPERTYPE" MODIFY ("NOMBRETIPO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table RPTREQUESTTIMEPERCATEGORYLEVEL
--------------------------------------------------------

  ALTER TABLE "RPTREQUESTTIMEPERCATEGORYLEVEL" MODIFY ("INTERNALREPORTID" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTTIMEPERCATEGORYLEVEL" MODIFY ("HORAS" NOT NULL ENABLE);
 
  ALTER TABLE "RPTREQUESTTIMEPERCATEGORYLEVEL" MODIFY ("NOMBRECATEGORIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table HBASSETENUMERATEDSTRPROPERTIES
--------------------------------------------------------

  ALTER TABLE "HBASSETENUMERATEDSTRPROPERTIES" ADD CONSTRAINT "FK_HBASSETENUMSTRPROP_HBPROP" FOREIGN KEY ("AEP_PRO_PROPERTY")
	  REFERENCES "HBPROPERTIES" ("PRO_HBID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBASSETSDATA
--------------------------------------------------------

  ALTER TABLE "HBASSETSDATA" ADD CONSTRAINT "FK_HBASSETSDATA_HBASSETS" FOREIGN KEY ("ASD_ASS_HBID")
	  REFERENCES "HBASSETS" ("ASS_HBID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBCATEGORYSCALINGGROUPS
--------------------------------------------------------

  ALTER TABLE "HBCATEGORYSCALINGGROUPS" ADD CONSTRAINT "FK_HBCATSCALGROUPS_HBSCALGROUP" FOREIGN KEY ("SGP_HBID")
	  REFERENCES "HBSCALINGGROUPS" ("SGP_HBID") ENABLE;
 
  ALTER TABLE "HBCATEGORYSCALINGGROUPS" ADD CONSTRAINT "FK_HBCATSCALINGGROUPS_HBCAT" FOREIGN KEY ("CAT_HBID")
	  REFERENCES "HBCATEGORIES" ("CAT_HBID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HB_ENTIDADESEXTERNAS
--------------------------------------------------------

  ALTER TABLE "HB_ENTIDADESEXTERNAS" ADD CONSTRAINT "HB_ENTIDADESEXTERNAS_HBUS_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBREQUESTACTIONS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTACTIONS" ADD CONSTRAINT "FK_HBREQUESTACTIONS_HBUSERS" FOREIGN KEY ("USR_HBID_AGENT")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBREQUESTLOADFILES
--------------------------------------------------------

  ALTER TABLE "HBREQUESTLOADFILES" ADD CONSTRAINT "HBREQUESTLOADFILES_HBUSER_FK1" FOREIGN KEY ("USR_HBID_USERLOADFILE")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBREQUESTS
--------------------------------------------------------

  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQUESTS_HBCATEGORIES" FOREIGN KEY ("CAT_HBID")
	  REFERENCES "HBCATEGORIES" ("CAT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQUESTS_HBSCALINGGROUPS" FOREIGN KEY ("SGP_HBID")
	  REFERENCES "HBSCALINGGROUPS" ("SGP_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQUESTS_HBUSERS" FOREIGN KEY ("USR_HBID_REQUESTER")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQUESTS_HBUSERS1" FOREIGN KEY ("USR_HBID_INTRODUCER")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQUESTS_HBUSERS2" FOREIGN KEY ("USR_HBID_MANAGER")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "FK_HBREQ_HBREQPRIORITIES" FOREIGN KEY ("RPT_HBID")
	  REFERENCES "HBREQUESTPRIORITIES" ("RPT_HBID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD CONSTRAINT "HBREQUESTS_HB_ENTIDADESEX_FK1" FOREIGN KEY ("RQT_EXTERNOID")
	  REFERENCES "HB_ENTIDADESEXTERNAS" ("ID") ENABLE;
 
  ALTER TABLE "HBREQUESTS" ADD FOREIGN KEY ("ID_CAT_SIP")
	  REFERENCES "CATEGORIA_SIP" ("ID") ON DELETE SET NULL ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBREQUESTTOKEN
--------------------------------------------------------

  ALTER TABLE "HBREQUESTTOKEN" ADD CONSTRAINT "HBREQUESTTOKEN_HBREQUESTS_FK1" FOREIGN KEY ("RQT_HBID")
	  REFERENCES "HBREQUESTS" ("RQT_HBID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBSCALINGGROUPUSERS
--------------------------------------------------------

  ALTER TABLE "HBSCALINGGROUPUSERS" ADD CONSTRAINT "FK_HBSCALGRUPUSERS_HBSCALGROUP" FOREIGN KEY ("SGP_HBID")
	  REFERENCES "HBSCALINGGROUPS" ("SGP_HBID") ENABLE;
 
  ALTER TABLE "HBSCALINGGROUPUSERS" ADD CONSTRAINT "FK_HBSCALINGGRUPUSERS_HBUSERS" FOREIGN KEY ("USR_HBID")
	  REFERENCES "HBUSERS" ("USR_HBID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HBUSERS_ORIGIN
--------------------------------------------------------

  ALTER TABLE "HBUSERS_ORIGIN" ADD CONSTRAINT "HBUSERS_ORIGIN_HBREQUESTO_FK1" FOREIGN KEY ("ID_ORIGIN")
	  REFERENCES "HBREQUESTORIGINS" ("ROG_HBID") ON DELETE CASCADE ENABLE;
 
  ALTER TABLE "HBUSERS_ORIGIN" ADD CONSTRAINT "HBUSERS_ORIGIN_HBUSERS_FK1" FOREIGN KEY ("ID_USER")
	  REFERENCES "HBUSERS" ("USR_HBID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RESPUESTASTIPO
--------------------------------------------------------

  ALTER TABLE "RESPUESTASTIPO" ADD CONSTRAINT "RESPUESTASTIPO_HBUSERS_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "HBUSERS" ("USR_HBID") ON DELETE CASCADE ENABLE;

 