--------------------------------------------------------
--  DDL for Table CALLE
--------------------------------------------------------

  CREATE TABLE "CALLE" 
   (	"ID" NUMBER(7,0), 
	"NOMBRE" VARCHAR2(260 BYTE), 
	"NOMBRE_COMPLETO" VARCHAR2(500 BYTE), 
	"TIPO_VIA" VARCHAR2(20 CHAR), 
	"GCZ_PUBLICADO" VARCHAR2(1 BYTE) DEFAULT 'S', 
	"GCZ_FECHAALTA" DATE, 
	"GCZ_FECHAMOD" DATE DEFAULT NULL, 
	"GCZ_FECHAPUB" DATE DEFAULT NULL, 
	"GCZ_USUARIOALTA" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"GCZ_USUARIOMOD" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"GCZ_USUARIOPUB" VARCHAR2(100 BYTE) DEFAULT NULL
   ) ;
--------------------------------------------------------
--  DDL for Table CALL_JUNTA
--------------------------------------------------------

  CREATE TABLE "CALL_JUNTA" 
   (	"ID_CALLE" NUMBER(6,0), 
	"PRIMER_NUMERO" VARCHAR2(10 BYTE), 
	"ULTIMO_NUMERO" VARCHAR2(10 BYTE), 
	"COD_POS" VARCHAR2(5 BYTE), 
	"ID_JUNTA" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table BARRIO
--------------------------------------------------------

  CREATE TABLE "BARRIO" 
   (	"ID_BARRIO" NUMBER, 
	"NOMBRE" VARCHAR2(255 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table JUNTA
--------------------------------------------------------

  CREATE TABLE "JUNTA" 
   (	"ID_JUNTA" NUMBER(*,0), 
	"NOMBRE" VARCHAR2(50 BYTE), 
	"GEOMETRIA" CLOB, 
	"NOMBRE_PADRON" VARCHAR2(200 BYTE), 
	"ID_MAPA" NUMBER, 
	"ID_CEN" NUMBER, 
	"DESCRIPCION" VARCHAR2(400 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table JUNTA_TIENE_PROPIEDAD
--------------------------------------------------------

  CREATE TABLE "JUNTA_TIENE_PROPIEDAD" 
   (	"ID_PROPIEDAD" NUMBER, 
	"ID_JUNTA" NUMBER, 
	"VALOR" VARCHAR2(4000 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table MAPEO_SEMANTICO_VIA
--------------------------------------------------------

  CREATE TABLE "MAPEO_SEMANTICO_VIA" 
   (	"ID" NUMBER(*,0), 
	"ID_VIA" NUMBER(*,0), 
	"ID_PROPIEDAD" NUMBER(*,0), 
	"URI_RECURSO" VARCHAR2(1000 BYTE), 
	"NOMBRE_RECURSO" VARCHAR2(1000 BYTE), 
	"ESTADO" CHAR(1 BYTE), 
	"PUNTUACION" FLOAT(126), 
	"FECHA_ALTA" DATE, 
	"FECHA_MOD_ESTADO" DATE, 
	"FECHA_MOD_PUNTUACION" DATE
   ) ;
 

   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."URI_RECURSO" IS 'La URI del recurso con el que se relaciona la v¿';
 
   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."NOMBRE_RECURSO" IS 'El nombre del recurso con el que se relaciona la v¿';
 
   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."ESTADO" IS 'Se montar¿como una lista controlada donde P=pendiente S=aceptado N=rechazado 1=preferido';
 
   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."FECHA_ALTA" IS 'Fecha en la que se insert¿r primera vez el mapeo.';
 
   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."FECHA_MOD_ESTADO" IS '¿tima fecha en la que se modific¿ estado.';
 
   COMMENT ON COLUMN "MAPEO_SEMANTICO_VIA"."FECHA_MOD_PUNTUACION" IS '¿tima fecha en la que se modific¿ puntuaci¿el mapeo.';
 
   COMMENT ON TABLE "MAPEO_SEMANTICO_VIA"  IS 'Tabla que almacenar¿as relaciones sem¿icas entre v¿ y recursos. Se puede definir la propiedad por la que se relacionan, de forma que en realidad tenemos tripletas.';
--------------------------------------------------------
--  DDL for Table PORTALERO
--------------------------------------------------------

  CREATE TABLE "PORTALERO" 
   (	"ID_POR" NUMBER(10,0), 
	"NUMERO" NUMBER(4,0), 
	"X" NUMBER(10,2), 
	"Y" NUMBER(10,2), 
	"ETIQUETA" VARCHAR2(100 BYTE), 
	"COD_POS" VARCHAR2(5 BYTE), 
	"ID" NUMBER(6,0), 
	"ID_BAR" NUMBER(10,0), 
	"ID_JUN" NUMBER(10,0), 
	"GCZ_FECHAALTA" DATE, 
	"GCZ_FECHAMOD" DATE DEFAULT NULL, 
	"GCZ_PUBLICADO" VARCHAR2(1 BYTE) DEFAULT 'S', 
	"GCZ_FECHAPUB" DATE DEFAULT NULL, 
	"GCZ_USUARIOALTA" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"GCZ_USUARIOMOD" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"GCZ_USUARIOPUB" VARCHAR2(100 BYTE) DEFAULT NULL, 
	"XWGS84" FLOAT(126), 
	"YWGS84" FLOAT(126)
   ) ;
--------------------------------------------------------
--  DDL for Table TIPO_VIA
--------------------------------------------------------

  CREATE TABLE "TIPO_VIA" 
   (	"CODIGO" VARCHAR2(20 CHAR), 
	"NOMBRE_COMPLETO" VARCHAR2(255 CHAR), 
	"NOMBRE_CORTO" VARCHAR2(255 CHAR), 
	"ABREVIATURA" VARCHAR2(20 CHAR)
   ) ;
 

   COMMENT ON COLUMN "TIPO_VIA"."CODIGO" IS 'Es el c¿o que le corresponde a cada tipo de via: Plaza es PL, Pasaje PJ, Avenida AV, Andador AN. Lo usaremos como clave primaria';
 
   COMMENT ON COLUMN "TIPO_VIA"."NOMBRE_COMPLETO" IS 'Es la denominaci¿ue tiene como tipo de via: Plaza, Pasaje, Avenida, Avenida peatonal, Andador ';
 
   COMMENT ON COLUMN "TIPO_VIA"."NOMBRE_CORTO" IS 'Es la denominaci¿orta. S¿cambia en tipos como Avenida peatonal, que pasa a ser Avenida';
 
   COMMENT ON COLUMN "TIPO_VIA"."ABREVIATURA" IS 'Es la abreviatura que todo el mundo suele asignar a cada tipo de via. Aqu¿a no son valores ¿nicos, y "calle", "calle peatonal" y "tramo peatonal" comparten "Cl.", por ejemplo';

--------------------------------------------------------
--  DDL for Index PK_CALLE
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_CALLE" ON "CALLE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index BARRIO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BARRIO_PK" ON "BARRIO" ("ID_BARRIO") 
  ;
--------------------------------------------------------
--  DDL for Index PK_JUNTA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_JUNTA" ON "JUNTA" ("ID_JUNTA") 
  ;
--------------------------------------------------------
--  DDL for Index JUNTA_TIENE_PROPIEDAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "JUNTA_TIENE_PROPIEDAD_PK" ON "JUNTA_TIENE_PROPIEDAD" ("ID_PROPIEDAD", "ID_JUNTA") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C008029
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C008029" ON "MAPEO_SEMANTICO_VIA" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ID_PORTALERO_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "ID_PORTALERO_ID" ON "PORTALERO" ("ID_POR") 
  ;
--------------------------------------------------------
--  DDL for Index PK_TIPO_VIA
--------------------------------------------------------

  CREATE UNIQUE INDEX "PK_TIPO_VIA" ON "TIPO_VIA" ("CODIGO") 
  ;
--------------------------------------------------------
--  DDL for Trigger DISP_CALLE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DISP_CALLE" 
before INSERT OR UPDATE
ON CALLE for each row

BEGIN
    if inserting then
          :new.GCZ_FECHAALTA := sysdate;
    elsif updating then
          :new.GCZ_FECHAMOD := sysdate;
    end if;
END;
/
ALTER TRIGGER "DISP_CALLE" ENABLE;
--------------------------------------------------------
--  Constraints for Table CALLE
--------------------------------------------------------

  ALTER TABLE "CALLE" ADD CONSTRAINT "PK_CALLE" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CALLE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CALLE" MODIFY ("NOMBRE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BARRIO
--------------------------------------------------------

  ALTER TABLE "BARRIO" ADD CONSTRAINT "BARRIO_PK" PRIMARY KEY ("ID_BARRIO") ENABLE;
 
  ALTER TABLE "BARRIO" MODIFY ("ID_BARRIO" NOT NULL ENABLE);
 
  ALTER TABLE "BARRIO" MODIFY ("NOMBRE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table JUNTA
--------------------------------------------------------

  ALTER TABLE "JUNTA" ADD CONSTRAINT "PK_JUNTA" PRIMARY KEY ("ID_JUNTA") ENABLE;
 
  ALTER TABLE "JUNTA" MODIFY ("ID_JUNTA" NOT NULL ENABLE);
 
  ALTER TABLE "JUNTA" MODIFY ("NOMBRE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table JUNTA_TIENE_PROPIEDAD
--------------------------------------------------------

  ALTER TABLE "JUNTA_TIENE_PROPIEDAD" ADD CONSTRAINT "JUNTA_TIENE_PROPIEDAD_PK" PRIMARY KEY ("ID_PROPIEDAD", "ID_JUNTA") ENABLE;
 
  ALTER TABLE "JUNTA_TIENE_PROPIEDAD" MODIFY ("ID_PROPIEDAD" NOT NULL ENABLE);
 
  ALTER TABLE "JUNTA_TIENE_PROPIEDAD" MODIFY ("ID_JUNTA" NOT NULL ENABLE);
 
  ALTER TABLE "JUNTA_TIENE_PROPIEDAD" MODIFY ("VALOR" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MAPEO_SEMANTICO_VIA
--------------------------------------------------------

  ALTER TABLE "MAPEO_SEMANTICO_VIA" MODIFY ("ID_VIA" NOT NULL ENABLE);
 
  ALTER TABLE "MAPEO_SEMANTICO_VIA" MODIFY ("ID_PROPIEDAD" NOT NULL ENABLE);
 
  ALTER TABLE "MAPEO_SEMANTICO_VIA" MODIFY ("URI_RECURSO" NOT NULL ENABLE);
 
  ALTER TABLE "MAPEO_SEMANTICO_VIA" MODIFY ("ESTADO" NOT NULL ENABLE);
 
  ALTER TABLE "MAPEO_SEMANTICO_VIA" MODIFY ("FECHA_ALTA" NOT NULL ENABLE);
 
  ALTER TABLE "MAPEO_SEMANTICO_VIA" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table PORTALERO
--------------------------------------------------------

  ALTER TABLE "PORTALERO" ADD CONSTRAINT "ID_PORTALERO_ID" PRIMARY KEY ("ID_POR") ENABLE;
 
  ALTER TABLE "PORTALERO" MODIFY ("ID_POR" NOT NULL ENABLE);
 
  ALTER TABLE "PORTALERO" MODIFY ("NUMERO" NOT NULL ENABLE);
 
  ALTER TABLE "PORTALERO" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TIPO_VIA
--------------------------------------------------------

  ALTER TABLE "TIPO_VIA" MODIFY ("NOMBRE_COMPLETO" CONSTRAINT "NN_TIPO_VIA_NCOMPL" NOT NULL ENABLE);
 
  ALTER TABLE "TIPO_VIA" MODIFY ("NOMBRE_CORTO" CONSTRAINT "NN_TIPO_VIA_NCORTO" NOT NULL ENABLE);
 
  ALTER TABLE "TIPO_VIA" ADD CONSTRAINT "PK_TIPO_VIA" PRIMARY KEY ("CODIGO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CALLE
--------------------------------------------------------

  ALTER TABLE "CALLE" ADD CONSTRAINT "FK_CALLE_TIPO_VIA" FOREIGN KEY ("TIPO_VIA")
	  REFERENCES "TIPO_VIA" ("CODIGO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CALL_JUNTA
--------------------------------------------------------

  ALTER TABLE "CALL_JUNTA" ADD CONSTRAINT "FK_CALL_JUNTA" FOREIGN KEY ("ID_JUNTA")
	  REFERENCES "JUNTA" ("ID_JUNTA") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table JUNTA_TIENE_PROPIEDAD
--------------------------------------------------------

  ALTER TABLE "JUNTA_TIENE_PROPIEDAD" ADD CONSTRAINT "JUNTA_TIENE_PROPIEDAD_CE_FK1" FOREIGN KEY ("ID_JUNTA")
	  REFERENCES "JUNTA" ("ID_JUNTA") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PORTALERO
--------------------------------------------------------

  ALTER TABLE "PORTALERO" ADD CONSTRAINT "REF_PORTA_BARRI_FK" FOREIGN KEY ("ID_BAR")
	  REFERENCES "BARRIO" ("ID_BARRIO") ENABLE;
 
  ALTER TABLE "PORTALERO" ADD CONSTRAINT "REF_PORTA_JUNTA_FK" FOREIGN KEY ("ID_JUN")
	  REFERENCES "JUNTA" ("ID_JUNTA") ENABLE;
