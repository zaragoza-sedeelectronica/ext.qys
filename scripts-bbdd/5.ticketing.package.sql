set define off;

create or replace FUNCTION split
(p_string    varchar,
p_element   INTEGER,
	p_separator varchar)
RETURN	    VARCHAR2
AS
v_string     VARCHAR2(32767);
BEGIN
v_string := p_string || p_separator;
FOR i IN 1 .. p_element - 1 LOOP
v_string := SUBSTR(v_string,INSTR(v_string,p_separator)+LENGTH(p_separator));
END LOOP;
RETURN SUBSTR(v_string,1,INSTR(v_string,p_separator)-1);
END split;
/
create or replace PACKAGE PCK_QYS_REQUESTS AS TYPE cursorRetorno IS REF CURSOR;
    PROCEDURE DETALLE(
    v_id in numeric,
    usuarioTicketing in numeric default null,
		xmlsal OUT CLOB);
    PROCEDURE GET(v_filas IN number, v_inicio IN number,
    v_orden in varchar2, 
    v_ids in varchar2 default null, 
    v_title in varchar2 default null,
    v_notes in varchar2 default null,
		v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
    v_user_id in numeric default null, 
		v_start_date in date default null, 
		v_end_date in date default null, 
    v_type in varchar2 default null,
		v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
    operadorget in varchar2 default null,
    v_id_cat_sip in numeric default null,
    xmlsal OUT clob, total OUT number);
    
    PROCEDURE GETACCIONES(v_filas IN number, v_inicio IN number,
    v_orden in varchar2, 
    v_ids in varchar2 default null, 
    v_title in varchar2 default null,
    v_notes in varchar2 default null,
		v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
		v_start_date in date default null, 
		v_end_date in date default null, 
    v_type in varchar2 default null,
		v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
    xmlsal OUT clob, total OUT number);
    
    
    PROCEDURE CREAR(
        v_service_code numeric default null,
				p_email hbusers.cli_email%type default null, 
				v_address_string hbusers.per_address%type default null, 
        v_address_id varchar2 default null,
				p_account_id varchar2 default null, 
				p_first_name varchar2 default null, 
				p_last_name varchar2 default null, 
				p_user_address hbusers.per_address%type default null, 
				p_phone varchar2 default null, 
				v_title varchar2 default null, 
        v_notes varchar2 default null, 
				v_description in hbrequests.rqt_creationdescription%type default null,
				v_media_name hbrequestloadfiles.rlf_filename%type default null, 
        v_media_description hbrequestloadfiles.rlf_description%type default null, 
				p_publico varchar2 default null, 
				v_x number default null, 
				v_y number default null,
    
        p_agency_code numeric default null,
        p_type numeric default 1,
        p_priority numeric default 2,
        p_origin numeric default 3,
        p_validated varchar2 default null,
        --p_visible varchar2 default null,
        p_zona_inspeccion numeric default null,
        p_fecha_prevista date default null,
    
        p_usuarioadmin varchar2 default null,
        p_operator varchar2 default null,
        p_answer_requested varchar2 default null,
        p_id_cita numeric default null,
        p_user_id numeric default null,

        xmlsal OUT CLOB
    );
    
    PROCEDURE GUARDAR(
        p_requestnumber number,
        p_service_code numeric default null,
				p_email hbusers.cli_email%type default null, 
				p_address_string hbusers.per_address%type default null, 
        p_address_id varchar2 default null,
				p_account_id varchar2 default null, 
				p_first_name varchar2 default null, 
				p_last_name varchar2 default null, 
				p_user_address hbusers.per_address%type default null, 
				p_phone varchar2 default null, 
				p_title varchar2 default null, 
        p_notes varchar2 default null, 
				p_description in hbrequests.rqt_creationdescription%type default null,  
				p_media_name hbrequestloadfiles.rlf_filename%type default null, 
        p_media_description hbrequestloadfiles.rlf_description%type default null, 
				p_publico varchar2 default null, 
				p_x number default null, 
				p_y number default null,
    
        p_agency_code numeric default null,
        p_type numeric default null,
        p_priority numeric default null,
        p_origin numeric default null,
        p_validated varchar2 default null,
       -- p_visible varchar2 default null,
      
        p_zona_inspeccion numeric default null,
        p_fecha_prevista date default null,
    
        p_usuarioadmin varchar2 default null,
        p_answer_requested varchar2 default null,
        
        p_id_cita numeric default null,
        p_id_cat_sip numeric default null,
        
        xmlsal OUT CLOB
    );
    
    PROCEDURE ACCIONES(
        v_requestnumber number,
        p_accion numeric default null,
				v_texto hbrequestactions.rqa_description%type default null, 
				
        v_fecha date default null,
        
        v_idexterno numeric default null,
        
        p_usuarioadmin varchar2 default null,
        p_usuariogcz varchar2 default null,
        p_uuid varchar2 default null,
        p_estadointerno number default null,
    
        xmlsal OUT CLOB
    );
    
     PROCEDURE ESTADOANTERIORQUEJA(
        v_requestnumber number,
        v_accion numeric default null,
				v_texto hbrequestactions.rqa_description%type default null,        
        p_usuarioadmin varchar2 default null,
        p_usuariogcz varchar2 default null
    );
    
     PROCEDURE TOKEN(
        v_token varchar2 default null,
				v_texto hbrequestactions.rqa_description%type default null, 
        xmlsal OUT CLOB
    );
    
    PROCEDURE ASOCIAR(
        v_id numeric default null,
        v_service_code numeric default null,
        v_agency_responsible numeric default null,
        v_user_id numeric
    );
    
    PROCEDURE CATEGORIA(
        rootCategory number default null,
        xmlsal OUT CLOB
    );
    
     PROCEDURE INFORMES(
        v_start_date in date default null, 
        v_end_date in date default null,
        v_ids_categoria in varchar2 default null,
       /* v_distrito in numeric default null,
        v_barrio in numeric default null,*/
				v_texto varchar2 default null, 
        usuarioTicketing in numeric default null,
        xmlsal OUT CLOB
    );
    
    FUNCTION generarWhere(v_ids in varchar2 default null, 
    v_title in varchar2 default null,
    v_notes in varchar2 default null,
		v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
    v_user_id in numeric default null,
		v_start_date in date default null, 
		v_end_date in date default null, 
    v_type in varchar2 default null,
		v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
    operadorget in varchar2 default null,
    v_id_cat_sip in numeric default null
    ) RETURN string;
    
    
    FUNCTION GET_ROWS(pString IN varchar2) return integer;
    
END PCK_QYS_REQUESTS;
/
create or replace PACKAGE BODY "PCK_QYS_REQUESTS" AS
   
   PROCEDURE DETALLE(
    v_id in numeric,
    usuarioTicketing in numeric default null,
        xmlsal OUT CLOB)
   
   IS
   v_idinterno numeric;
   l_xmltype XMLTYPE;
   norows EXCEPTION;
   PRAGMA EXCEPTION_INIT(norows, -30625);
   BEGIN
  --3506176
     select RQT_HBID into v_idinterno from hbrequests where rqt_requestnumber=v_id;

     select xmlelement("request",
                    xmlelement("service_request_id", hb_re.rqt_requestnumber),
                    xmlelement("description", hb_re.description),
                    xmlelement("title", hb_re.title),
                    xmlelement("notes", notes),
                    xmlelement("first_name", case when ciudadano.usr_hbid=3506176 then hb_re.first_name else ciudadano.PER_FIRSTNAME ||' ' || ciudadano.PER_LASTNAME end),
                    xmlelement("account_id", USR_HBID_REQUESTER),
                    xmlelement("user_id", USER_ID),
                    xmlelement("email", case when ciudadano.usr_hbid=3506176 then hb_re.email else ciudadano.CLI_EMAIL end),
                    xmlelement("phone", case when ciudadano.usr_hbid=3506176 then hb_re.phone else ciudadano.PER_PHONE1 end),
                    xmlelement("user_address", case when ciudadano.usr_hbid=3506176 then hb_re.user_address else ciudadano.PER_ADDRESS end),
                    xmlelement("address_string", hb_re.address_string),
                    xmlelement("address_id", hb_re.address_id),
                    xmlelement("origin", ROG_HBID),
                    xmlelement("priority",RPT_HBID),
                    xmlelement("type", RTY_HBID),
                    
                    xmlelement("agency_responsible_code",usr.USR_HBID),
                    xmlelement("agency_responsible",usr.PER_FIRSTNAME || ' '|| usr.PER_LASTNAME),
                    xmlelement("agency_responsible_email",usr.CLI_EMAIL),
                    xmlelement("requested_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_introductiondatetime AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("resolved_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.RQT_RESOLVEDDATATIME AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("closed_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.RQT_CLOSEDATETIME AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("gestor_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_gestordate AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("inspector_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.RQT_INSPECTORDATE AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("expected_resolution_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.RQT_EXPECTEDRESOLUTIONDATETIME AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    
                    
                    xmlelement("group_operator", split (hb_re.operador, 1, ':')),
                    xmlelement("operator", split (hb_re.operador, 2, ':')),
                    xmlelement("answer_requested", hb_re.solicita_respuesta),

                    xmlelement("externo_datetime", TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_externodate AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                    xmlelement("externo_code", RQT_EXTERNOID),
                    xmlelement("externo_name", (select nombre from HB_ENTIDADESEXTERNAS where id = RQT_EXTERNOID) ),
                    
                    xmlelement("updated_datetime", nvl2(hb_re.rqt_closedate,TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_closedate AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS'),TO_CHAR(sys_extract_utc(CAST (acciones.fecha AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')) ),
      
                    xmlelement("x", replace(hb_re.rqt_geolocation.SDO_POINT.X,',','.')),
                    xmlelement("y", replace(hb_re.rqt_geolocation.SDO_POINT.Y,',','.')),
                    
                    xmlelement("zona_inspeccion", hb_re.RQT_ZONAINSPECCIONID),
                    xmlelement("inspector", hb_re.RQT_INSPECTORID),
                    xmlelement("id_cita", hb_re.ID_CITA),
                    xmlelement("token", hb_re.TOKEN),
                    
                    xmlelement("status", nvl2(hb_re.rqt_closedate,'closed','open')),
                    xmlelement("status_admin", hb_st.RST_NAME),
                    xmlelement("visible", hb_re.RQT_PUBLIC),
                    xmlelement("id_cat_sip", hb_re.ID_CAT_SIP),
                    xmlelement("validated", hb_re.RQT_VALIDATED),
                    xmlelement("service_code", hb_le.cal_hbid),
                    xmlelement("service_name", hb_le.cal_name),

                    xmlelement("service_notice", hb_re.service_notice),
                    xmlelement("rst_id", hb_re.rst_id),
                        (SELECT xmlagg(xmlelement("actions",
                                                   xmlelement("id", RQA_HBID),
                                                   xmlelement("version", RQA_HBVERSION),
                                                   xmlelement("elapsed_seconds", RQA_ELAPSEDSECONDS),
                                                   xmlelement("elapsed_time", TO_CHAR(TRUNC(RQA_ELAPSEDSECONDS/3600),'FM9900') || ':' || TO_CHAR(TRUNC(MOD(RQA_ELAPSEDSECONDS,3600)/60),'FM00') || ':' || TO_CHAR(MOD(RQA_ELAPSEDSECONDS,60),'FM00')),
                                                   xmlelement("agent_id", USR_HBID_AGENT),
                                                   xmlelement("agent_name", PER_FIRSTNAME || ' ' || PER_LASTNAME),
                                                   xmlelement("type", RAT_HBID_TYPE),
                                                   xmlelement("type_name", RAT_NAME),
                                                   xmlelement("substate", RSS_HBID_SUBSTATE),
                                                   xmlelement("operator", OPERADOR),
                                                   xmlelement("version", RQA_HBVERSION),
                                                   xmlelement("description", RQA_DESCRIPTION),
                                                   xmlelement("agency_responsible", grs.sgp_name),
                                                   xmlelement("creation_date", TO_CHAR(sys_extract_utc(CAST (RQA_CREATIONDATETIME AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS'))
                                                   
                                                                              )
                                                         order by RQA_CREATIONDATETIME desc)
                                FROM HBREQUESTACTIONS ac, HBUSERS us, HBREQUESTACTIONTYPES tipo,HBSCALINGGROUPS grs,HBSCALINGGROUPUSERS usr_gr 
                                WHERE RQT_HBID_REQUEST = v_idinterno and USR_HBID_AGENT=us.usr_hbid and RAT_HBID_TYPE=tipo.RAT_HBID(+) and
                                      usr_gr.usr_hbid(+)=us.usr_hbid and grs.sgp_hbid(+)=usr_gr.sgp_hbid
                                
                         ),
                         (SELECT xmlagg(xmlelement("files",
                                                   xmlelement("id", RLF_HBID),
                                                   xmlelement("version", RLF_HBVERSION),
                                                   xmlelement("description", RLF_DESCRIPTION),
                                                   xmlelement("id_usario", USR_HBID_USERLOADFILE),
                                                   xmlelement("creation_date", TO_CHAR(sys_extract_utc(CAST (RLF_INTRODUTIONDATETIME AS TIMESTAMP)),'YYYY-MM-DD"T"HH24:MI:SS')),
                                                   xmlelement("media_url", 'http://www.zaragoza.es/aytocasa/ver.jsp?id=' || rlf_hbid ||','|| rqt_hbid_request || ',' || translate(lower(rlf_filename),'áéíóúñ &','aeioun__')),
                                                   xmlelement("file_name", RLF_FILENAME)
                                                                              )
                                                         )
                                FROM HBREQUESTLOADFILES ac
                                WHERE RQT_HBID_REQUEST = v_idinterno
                         )                          
                
      ) into l_xmltype
      from hbrequests hb_re, HBREQUESTSTATES hb_st, hbcategorylevels hb_le, (select max(RQA_REQUESTACTIONDATETIME) as fecha,RQT_HBID_REQUEST from HBREQUESTACTIONS group by RQT_HBID_REQUEST) acciones, hbusers usr, hbusers ciudadano
      where ciudadano.USR_HBID(+)=hb_re.USR_HBID_REQUESTER  and hb_re.rqt_requestnumber = v_id AND hb_re.RST_ID=hb_st.RST_HBID(+) AND acciones.RQT_HBID_REQUEST = hb_re.rqt_hbid and  hb_re.cat_hbid= hb_le.cal_hbid(+) and hb_re.USR_HBID_MANAGER = usr.USR_HBID(+) and
        (hb_re.USR_HBID_REQUESTER=usuarioTicketing 
            OR hb_re.USR_HBID_INTRODUCER=usuarioTicketing 
            OR hb_re.USR_HBID_MANAGER=usuarioTicketing 
            -- OR (usuarioTicketing=2 and hb_re.USR_HBID_INTRODUCER not in (select USR_HBID from hbusers where CLI_ID_CATEGORY=2))
            OR usuarioTicketing=2
            -- 
            OR (hb_re.USR_HBID_MANAGER is null and (usuarioTicketing=2 or usuarioTicketing=235798528))
            OR usuarioTicketing in (select USR_HBID_AGENT from hbrequestactions where RQT_HBID_REQUEST = acciones.RQT_HBID_REQUEST));
   xmlsal := l_xmltype.getClobVal;
  EXCEPTION 
    when norows then
      xmlsal :='<resultado/>';
  
  END DETALLE;
  
   PROCEDURE GET(v_filas IN number, 
    v_inicio IN number, 
    v_orden in varchar2, 
    v_ids in varchar2 default null, 
    v_title in varchar2 default null, 
    v_notes in varchar2 default null, 
        v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
    v_user_id in numeric default null, 
        v_start_date in date default null, 
        v_end_date in date default null, 
    v_type in varchar2 default null, 
        v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
    operadorget in varchar2 default null,
    v_id_cat_sip in numeric default null,

   xmlsal OUT clob, total OUT number)
    IS
   l_xmltype XMLTYPE;
   l_ctx dbms_xmlgen.ctxhandle;
   norows EXCEPTION;
   PRAGMA EXCEPTION_INIT(norows, -30625);
   fromwhere varchar2(3000);
   fromwhereCount varchar2(3000);
   --fromwherecontar varchar2(3000);
    BEGIN

    fromwhere := 'FROM hbrequests hb_re, hbcategorylevels hb_le, HBREQUESTSTATES hb_st,
              (select RQT_HBID_REQUEST,USR_HBID_USERLOADFILE,rlf_hbid,rlf_filename from hbrequestloadfiles where USR_HBID_USERLOADFILE in (select USR_HBID from hbusers where CLI_ID_CATEGORY=2)) adjunto
      WHERE adjunto.RQT_HBID_REQUEST(+) = hb_re.rqt_hbid 
      and adjunto.USR_HBID_USERLOADFILE(+)=hb_re.USR_HBID_INTRODUCER
      AND hb_re.RST_ID=hb_st.RST_HBID(+) AND
      '|| generarWhere(v_ids,v_title,v_notes,v_service_code,v_externo_code,v_agency_responsible,v_account_id,v_user_id,v_start_date,v_end_date,v_type,v_status,v_public,usuarioTicketing,p_groupoperator,p_operator, p_answer_requested, p_barrio, origin, inspector,operadorget,v_id_cat_sip) ||'
      hb_re.cat_hbid= hb_le.cal_hbid(+)';
    
    fromWhereCount := 'FROM hbrequests hb_re, hbcategorylevels hb_le, HBREQUESTSTATES hb_st
      WHERE 
       hb_re.RST_ID=hb_st.RST_HBID(+) AND
      '|| generarWhere(v_ids,v_title,v_notes,v_service_code,v_externo_code,v_agency_responsible,v_account_id,v_user_id,v_start_date,v_end_date,v_type,v_status,v_public,usuarioTicketing,p_groupoperator,p_operator, p_answer_requested, p_barrio, origin, inspector,operadorget,v_id_cat_sip) ||'
      hb_re.cat_hbid= hb_le.cal_hbid(+)';
    
    /*fromwherecontar := 'FROM hbrequests hb_re, hbcategorylevels hb_le, HBREQUESTSTATES hb_st, hbusers usr
      WHERE hb_re.RST_ID=hb_st.RST_HBID(+) AND  
      '|| generarWhere(v_ids,v_title,v_service_code,v_externo_code,v_agency_responsible,v_account_id,v_start_date,v_end_date,v_type,v_status,v_public,usuarioTicketing) ||'
      hb_re.cat_hbid= hb_le.cal_hbid(+) and hb_re.USR_HBID_MANAGER = usr.USR_HBID(+)';*/
    
    if v_user_id is null and usuarioTicketing is null and v_account_id is null then
     fromWhere := fromWhere || ' AND hb_re.cat_hbid!=1 AND hb_re.cat_hbid!=2 ';
     fromWhereCount := fromWhereCount || ' AND hb_re.cat_hbid!=1 AND hb_re.cat_hbid!=2 ';
    end if;
    total := GET_ROWS(fromWhereCount);
    if total > 0 then
      if total > 300 and v_start_date is null and v_account_id is null then
      -- Si se obtienen demasiados resultados, filtramos solo las del último trimestre
        fromWhere := fromWhere || ' AND hb_re.rqt_introductiondatetime>=add_months(sysdate,-3) ';
      end if;    
     if v_orden is not null then
      fromWhere := fromWhere || ' order by ' || v_orden; 
     end if;
     
     l_ctx := dbms_xmlgen.newcontext('SELECT hb_re.rqt_requestnumber as service_request_id, 
       hb_re.description as description,
       hb_re.title as title,
        hb_re.notes as notes,
       hb_re.first_name as first_name,
       hb_re.email as email,
       hb_re.phone as phone,
       hb_re.user_address as user_address,
       hb_re.address_string as address_string,
       hb_re.address_id as address_id,

        split (hb_re.operador, 1, '':'') as group_operator,
        split (hb_re.operador, 2, '':'') as operator,
        
        hb_re.RPT_HBID as priority,
        hb_re.ID_CAT_SIP as id_cat_sip,
        
        hb_re.solicita_respuesta as answer_requested,
        hb_re.RQT_ZONAINSPECCIONID as zona_inspeccion,
        hb_re.RQT_INSPECTORID as inspector,
        replace(hb_re.rqt_geolocation.SDO_POINT.X,'','',''.'') as x,
        replace(hb_re.rqt_geolocation.SDO_POINT.Y,'','',''.'') as y,
        TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_introductiondatetime AS TIMESTAMP)),''YYYY-MM-DD"T"HH24:MI:SS'') as requested_datetime,
        TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_closedatetime AS TIMESTAMP)),''YYYY-MM-DD"T"HH24:MI:SS'') as closed_datetime,
        nvl2(hb_re.rqt_closedate,TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_closedate AS TIMESTAMP)),''YYYY-MM-DD"T"HH24:MI:SS''), TO_CHAR(sys_extract_utc(CAST (hb_re.rqt_gestordate AS TIMESTAMP)),''YYYY-MM-DD"T"HH24:MI:SS'')) as updated_datetime,
        nvl2(hb_re.rqt_closedate,''closed'',''open'') as status, 
        hb_re.service_notice as service_notice,

        hb_re.USR_HBID_MANAGER as agency_responsible_code,
        hb_re.USR_HBID_REQUESTER as account_id,
        hb_re.USER_ID as user_id,
        
        hb_le.cal_hbid as service_code,
        hb_le.cal_name as service_name,
        hb_re.RQT_PUBLIC as visible,
        hb_re.internal_status as internal_status,
        hb_re.RQT_VALIDATED as validated,
        nvl2(adjunto.rlf_hbid,''http://www.zaragoza.es/aytocasa/ver.jsp?id='' || adjunto.rlf_hbid ||'',''|| adjunto.rqt_hbid_request || '','' || translate(lower(adjunto.rlf_filename),''áéíóúñ &'',''aeioun__''),null) as media_url,
        hb_st.RST_NAME as status_admin, 
        hb_re.ROG_HBID as origin,
        hb_re.RTY_HBID as type  
        ' || fromwhere);
    
    
    dbms_xmlgen.setMaxRows(l_ctx,v_filas);
    dbms_xmlgen.setskiprows(l_ctx, v_inicio);
    dbms_xmlgen.setrowsettag(l_ctx, 'resultado');
    dbms_xmlgen.setrowtag (l_ctx, 'result');
    l_xmltype := dbms_xmlgen.getxmltype(l_ctx);
    l_xmltype := xmltype(replace(l_xmltype.getClobVal(), '<result>', '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="request">'));
    dbms_xmlgen.closecontext (l_ctx);
    xmlsal := l_xmltype.getClobVal;
   else
     xmlsal :='<resultado/>';
   end if;
  EXCEPTION 
    when norows then
      xmlsal :='<resultado/>';
  
  END GET;


  PROCEDURE GETACCIONES(v_filas IN number, 
    v_inicio IN number, 
    v_orden in varchar2, 
    v_ids in varchar2 default null, 
    v_title in varchar2 default null, 
    v_notes in varchar2 default null, 
        v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
        v_start_date in date default null, 
        v_end_date in date default null, 
    v_type in varchar2 default null, 
        v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
   
   xmlsal OUT clob, total OUT number)
    IS
   l_xmltype XMLTYPE;
   l_ctx dbms_xmlgen.ctxhandle;
   norows EXCEPTION;
   PRAGMA EXCEPTION_INIT(norows, -30625);
   fromwhere varchar2(3000);
   fromacccion varchar2(1000);
   --fromwherecontar varchar2(3000);
    BEGIN
    fromwhere := 'select hb_re.rqt_requestnumber FROM hbrequests hb_re, hbcategorylevels hb_le, HBREQUESTSTATES hb_st,
              (select RQT_HBID_REQUEST,USR_HBID_USERLOADFILE,rlf_hbid,rlf_filename from hbrequestloadfiles where USR_HBID_USERLOADFILE in (select USR_HBID from hbusers where CLI_ID_CATEGORY=2)) adjunto
      WHERE adjunto.RQT_HBID_REQUEST(+) = hb_re.rqt_hbid 
      and adjunto.USR_HBID_USERLOADFILE(+)=hb_re.USR_HBID_INTRODUCER
      AND hb_re.RST_ID=hb_st.RST_HBID(+) AND
      '|| generarWhere(v_ids,v_title,v_notes,v_service_code,v_externo_code,v_agency_responsible,v_account_id,null,null,null,v_type,v_status,v_public,usuarioTicketing,p_groupoperator,p_operator, p_answer_requested, p_barrio, origin, inspector, null,null) ||'
      hb_re.cat_hbid= hb_le.cal_hbid(+)';
    
    if v_start_date is not null then
      fromacccion := 'acciones.RQA_CREATIONDATETIME between to_date(''' || to_char(v_start_date,'dd/mm/yyyy') || ''',''dd/mm/yyyy'') and to_date(''' || to_char(v_end_date,'dd/mm/yyyy') || ''',''dd/mm/yyyy'') AND ';
    end if;
    
    
    if usuarioTicketing is null and v_account_id is null then
     fromWhere := fromWhere || ' AND hb_re.cat_hbid>2 AND hb_re.rqt_introductiondatetime>=to_date(''11-12-2013'',''dd-mm-yyyy'') ';
    end if;
    
     l_ctx := dbms_xmlgen.newcontext('SELECT request.rqt_requestnumber as id_aviso,
      acciones.RQA_HBID as id, 
       acciones.RQA_HBVERSION as version,
       acciones.RQA_DESCRIPTION as description,
       acciones.OPERADOR as operator,
       TO_CHAR(sys_extract_utc(CAST (acciones.RQA_CREATIONDATETIME AS TIMESTAMP)),''YYYY-MM-DD"T"HH24:MI:SS'') as creation_date 
       from hbrequestactions acciones, hbrequests request
       where '|| fromacccion ||'acciones.RQT_HBID_REQUEST = request.rqt_hbid 
       and request.rqt_requestnumber in (' || fromwhere ||')
       order by acciones.RQA_CREATIONDATETIME asc
       ');
        
     
    
    dbms_xmlgen.setMaxRows(l_ctx,v_filas);
    dbms_xmlgen.setskiprows(l_ctx, v_inicio);
    dbms_xmlgen.setrowsettag(l_ctx, 'resultado');
    
    dbms_xmlgen.setrowtag (l_ctx, 'result');
    l_xmltype := dbms_xmlgen.getxmltype(l_ctx);
    l_xmltype := xmltype(replace(l_xmltype.getClobVal(), '<result>', '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="accion">'));
    dbms_xmlgen.closecontext (l_ctx);
    total := 0;
    xmlsal := l_xmltype.getClobVal;
   
  EXCEPTION 
    when norows then
      xmlsal :='<resultado/>';
  
  END GETACCIONES;



  FUNCTION generarWhere(v_ids in varchar2 default null,
    v_title in varchar2 default null,
    v_notes in varchar2 default null,
        v_service_code in varchar2 default null, 
    v_externo_code in numeric default null,
    v_agency_responsible in numeric default null, 
    v_account_id in numeric default null, 
    v_user_id in numeric default null,
        v_start_date in date default null, 
        v_end_date in date default null, 
    v_type in varchar2 default null,
        v_status in varchar2 default null,
    v_public in varchar2 default null,
    usuarioTicketing in numeric default null,
    p_groupoperator varchar2 default null,
    p_operator varchar2 default null,
    p_answer_requested varchar2 default null,
    p_barrio varchar2 default null,
    origin in numeric default null,
    inspector in varchar2 default null,
    operadorget in varchar2 default null,
    v_id_cat_sip in numeric default null
    ) RETURN string
  IS
  consulta varchar2(4000);
  usuario numeric;
  BEGIN
    usuario := nvl(usuarioTicketing,v_account_id);
    if usuario is null and v_user_id is null then
      consulta := consulta || 'hb_re.rqt_public=''S'' AND hb_re.rqt_validated=''S'' AND hb_re.rst_id!=5 AND hb_re.rqt_introductiondatetime>=to_date(''11-12-2013'',''dd-mm-yyyy'') AND ';
    elsif v_user_id is null and usuario <> -1 and usuario<>2 then
      consulta := consulta || '(hb_re.USR_HBID_REQUESTER='||usuario
            ||' OR hb_re.USR_HBID_INTRODUCER='||usuario
            ||' OR hb_re.USR_HBID_MANAGER='|| usuario
            ||' OR hb_re.OPERADOR like ''%:'|| operadorget ||''''
            ||' OR (hb_re.USR_HBID_MANAGER is null and ('|| usuario ||'=2 or '||usuario||'=235798528))'
            
            --||' OR '|| usuario ||' in (select USR_HBID_AGENT from hbrequestactions where RQT_HBID_REQUEST = hb_re.RQT_HBID)'
            ||') AND ';
            
            if v_status is null and v_ids is null and usuarioTicketing is null then
              consulta := consulta || 'hb_re.rqt_introductiondatetime>=to_date(''11-12-2013'',''dd-mm-yyyy'') AND ';
            end if;
    end if;
    if inspector is not null then
      consulta := consulta || '(hb_re.rqt_inspectorid='''|| inspector ||''' OR hb_re.OPERADOR like ''%:'|| inspector ||''') AND ';
    end if;
    if v_service_code is not null then
      consulta := consulta || 'hb_le.cal_hbid in ('|| v_service_code ||') AND ';
    end if;
    if v_externo_code is not null then
      consulta := consulta || 'hb_re.RQT_EXTERNOID='|| v_externo_code ||' AND ';
    end if;
    
    if p_groupoperator is not null then
      consulta := consulta || 'hb_re.OPERADOR like '''|| p_groupoperator ||':%'' AND ';
    end if;
    
    if p_answer_requested is not null then
      consulta := consulta || 'hb_re.SOLICITA_RESPUESTA = '''|| p_answer_requested ||''' AND ';
    end if;
    
    if v_id_cat_sip is not null then
      consulta := consulta || 'hb_re.ID_CAT_SIP = '|| v_id_cat_sip ||' AND ';
    end if;
    
    if p_operator is not null then
      consulta := consulta || 'hb_re.OPERADOR like ''%:'|| p_operator ||''' AND ';
    end if;
    
    if origin is not null then
      consulta := consulta || 'hb_re.ROG_HBID='|| origin ||' AND ';
    end if;
    
    if p_barrio is not null then
      consulta := consulta || 'hb_re.address_id in (select p.id_por from intra.portalero p, intra.junta j where j.id_junta=p.id_jun and j.id_junta='||p_barrio||') AND ';      
    end if;

    if v_public is not null then
      if v_public='N' then
        consulta := consulta || 'hb_re.RQT_PUBLIC=''S'' and hb_re.RQT_VALIDATED='''|| v_public ||''' AND ';
      else
        consulta := consulta || 'hb_re.RQT_VALIDATED='''|| v_public ||''' AND ';
      end if;
    end if;
    if v_ids is not null then
      consulta := consulta || 'hb_re.rqt_requestnumber in (' || v_ids || ') AND ';
    end if;
    if v_title is not null then
      consulta := consulta || 'contains(rqt_creationdescription,'''|| replace(v_title,' ',' & ') ||''',1) > 0 AND ';

    end if;
    if v_notes is not null then
      consulta := consulta || 'hb_re.notes like ''%'|| v_notes ||'%'' AND ';
    end if;
    if v_status is not null then
      if lower(v_status) = 'open' then
        consulta := consulta || 'hb_re.rqt_closedate is null AND ';
      elsif lower(v_status) = 'closed' then
        consulta := consulta || 'hb_re.rqt_closedate is not null AND ';
      else
        consulta := consulta || 'hb_re.RST_ID in (' || v_status || ') AND ';
      end if;
    end if;
    
    if v_agency_responsible is not null then
      consulta := consulta || 'hb_re.USR_HBID_MANAGER = ' || v_agency_responsible || ' AND ';
    end if;
    if v_account_id is not null then
      consulta := consulta || 'hb_re.USR_HBID_REQUESTER = ' || v_account_id || ' AND ';
    end if;
    if v_user_id is not null then
      consulta := consulta || 'hb_re.USER_ID = ' || v_user_id || ' AND ';
    end if;
    if v_start_date is not null then
      consulta := consulta || 'hb_re.rqt_introductiondatetime between to_date(''' || to_char(v_start_date,'dd/mm/yyyy') || ''',''dd/mm/yyyy'') and to_date(''' || to_char(v_end_date,'dd/mm/yyyy') || ''',''dd/mm/yyyy'') AND ';
    end if;
    if v_type is not null then
      consulta := consulta || 'hb_re.RTY_HBID in ('|| v_type ||') AND ';
    end if;
    return consulta;
  END;

  FUNCTION GET_ROWS(pString IN varchar2) return integer
    IS 
        -----------------------------------------------------------------
        -- DECLARACIONES
        -----------------------------------------------------------------
        cursor_name INTEGER;
        v_total number;
    reg integer;
    BEGIN
    execute immediate 'select count(1) ' || pString into v_total;
    return v_total;
        EXCEPTION
            WHEN OTHERS THEN
                IF DBMS_SQL.IS_OPEN(cursor_name) THEN
                    DBMS_SQL.CLOSE_CURSOR(cursor_name);
                END IF;
                RAISE;
    END GET_ROWS;
  
  
  PROCEDURE CREAR(
        v_service_code numeric default null,
                p_email hbusers.cli_email%type default null, 
                v_address_string hbusers.per_address%type default null, 
        v_address_id varchar2 default null,
                p_account_id varchar2 default null, 
                p_first_name varchar2 default null, 
                p_last_name varchar2 default null, 
        p_user_address hbusers.per_address%type default null,
                p_phone varchar2 default null, 
                v_title varchar2 default null, 
                v_notes varchar2 default null, 
                v_description in hbrequests.rqt_creationdescription%type default null,
                v_media_name hbrequestloadfiles.rlf_filename%type default null, 
        v_media_description hbrequestloadfiles.rlf_description%type default null, 
                p_publico varchar2 default null, 
                v_x number default null, 
                v_y number default null, 
      
      
        p_agency_code numeric default null,
        p_type numeric default 1,
        p_priority numeric default 2,
        p_origin numeric default 3,
        p_validated varchar2 default null,
        --p_visible varchar2 default null,
        p_zona_inspeccion numeric default null,
        p_fecha_prevista date default null,
        
        p_usuarioadmin varchar2 default null,
        p_operator varchar2 default null,
        p_answer_requested varchar2 default null,
        p_id_cita numeric default null,
        p_user_id numeric default null,
        
        xmlsal OUT CLOB
    )
  IS
   v_account_id number;
   v_email hbusers.cli_email%type;
   v_first_name varchar2(255);
   v_last_name varchar2(255);
   v_user_address hbusers.per_address%type;
   v_phone varchar2(50);
   
   v_id number;
   v_requestnumber number;
   v_actionnumber number;

   v_publico varchar2(1);
   v_operator varchar2(40);
   v_service_code_ins numeric;
   v_gestor numeric;
   v_origin numeric;
   v_zona_inspeccion numeric;
   v_inspectorname varchar2(40);
   v_UsuarioNoExistente EXCEPTION;
   v_usuario_alta numeric;
   v_substate numeric;
  BEGIN
  
    v_account_id := p_account_id;
    v_email := p_email;
    v_first_name := p_first_name;
    v_last_name := p_last_name;
    v_user_address := p_user_address;
    v_phone := p_phone;
    
    if v_service_code is null then
      v_service_code_ins := 2;
    else
      v_service_code_ins := v_service_code;
    end if;
    
    if p_publico is null then
      v_publico := 'N';
    else
      v_publico := p_publico;
    end if;
    
    -- se le asocia el usuarioadmin porque en este caso se ha introducido desde la gestion
    if v_email is null 
        and v_first_name is null 
        and v_last_name is null 
        and v_user_address is null 
        and v_phone is null 
        and v_account_id is null 
        and p_usuarioadmin is not null then
      --v_account_id := p_usuarioadmin;
      v_account_id := 3506176;
    end if;
    
    -- Si el usuario es parques y jardines o conservacion
    if p_usuarioadmin = 4816905 or p_usuarioadmin = 4816903 then
      v_usuario_alta := p_usuarioadmin;
      v_substate := 1;
    else
      v_usuario_alta := null;
      v_substate := 3;
    end if;
    
     if p_operator is not null then
      v_operator := p_usuarioadmin || ':' || p_operator;
      if p_operator = 'ztc' then
        v_substate := 7;
      end if;
    end if;
    
    SAVEPOINT antescrear; 
    
    select REQUESTID - 1 into v_id from FO_HBREQUESTSID_UNIQUE_KEY;
    update FO_HBREQUESTSID_UNIQUE_KEY set REQUESTID = v_id;
    select REQUESTID into v_id from FO_HBREQUESTSID_UNIQUE_KEY;
  
    select REQUESTNUMBERID + 1 into v_requestnumber from HBREQUESTNUMBER_UNIQUE_KEY;
    update HBREQUESTNUMBER_UNIQUE_KEY set REQUESTNUMBERID = v_requestnumber;
    select REQUESTNUMBERID into v_requestnumber from HBREQUESTNUMBER_UNIQUE_KEY;
    
    if v_account_id is null then
      -- v_account_id es nulo entonces es usuario anónimo
      SELECT usr_hbid into v_account_id FROM hbusers WHERE usr_username= 'anonymous';
    else
      -- Sino recogemos los datos de la tabla hbusers
      begin
      SELECT per_firstname, per_lastname, per_phone1, cli_email, per_address
      into v_first_name,v_last_name,v_phone,v_email,v_user_address
        FROM hbusers WHERE usr_hbid=v_account_id;
      
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE v_UsuarioNoExistente;
      end;
    end if;
    
    if p_origin is not null and p_usuarioadmin is not null then
      begin
        select id_origin into v_origin from hbusers_origin where id_user=p_usuarioadmin;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        v_origin := p_origin;
      end;
    else
      v_origin := p_origin;
    end if;

    v_zona_inspeccion := null;

    if p_zona_inspeccion is null then
      if v_address_id is not null then
        begin
          select id_zona into v_zona_inspeccion from intra.portal_zona_inspeccion_parques where id_portal=v_address_id;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
          v_zona_inspeccion := null;
        end;
      end if;
    else
      v_zona_inspeccion := p_zona_inspeccion;
    end if;

    INSERT INTO hbrequests (rqt_hbid, rqt_hbversion, usr_hbid_requester, 
          usr_hbid_introducer,usr_hbid_manager, rst_id, rog_hbid, rty_hbid, rpt_hbid, 
          sgp_hbid, cat_hbid, 
          rqt_introductiondatetime, 
          rqt_creationdescription,description,title,first_name,email,phone,user_address,address_string,address_id,notes, rqt_requestdatetime, 
          rqt_requestdate, rqt_requestnumber, 
          rqt_expectedresolutiondatetime, rqt_requesthour,RQT_PUBLIC,RQT_VALIDATED, operador,solicita_respuesta,RQT_ZONAINSPECCIONID,ID_CITA, token,user_id) 
          VALUES (v_id, /*version*/0, v_account_id, nvl(p_usuarioadmin,v_account_id), v_usuario_alta,
        /*substate*/v_substate, /*ORIGIN*/v_origin, /*type*/p_type, /*priority*/p_priority, /*ManagerGroup*/1, /*category*/v_service_code_ins, sysdate,  
       v_description || '///' || v_title || '///' || nvl(v_first_name,'') ||' '|| nvl(v_last_name,'') || '///' 
          || nvl(v_email,'') || '///' || nvl(v_phone,'') ||'///' || nvl(v_user_address,'') || nvl2(v_address_string,'///' || v_address_string,'') || nvl2(v_address_id,'///' || v_address_id,''), 
          v_description,v_title,nvl(v_first_name,'') ||' '|| nvl(v_last_name,''), v_email,v_phone,v_user_address,v_address_string,v_address_id,
        v_notes,sysdate, sysdate, v_requestnumber, nvl(p_fecha_prevista,ADD_MONTHS(sysdate, 1)), 
          TO_CHAR(sysdate,'HH24'),v_publico,'N', v_operator,p_answer_requested,v_zona_inspeccion,p_id_cita,dbms_random.string('X', 40)/*token*/,p_user_id);

    BEGIN
        select usuario.usr_hbid into v_gestor
        from hbcategorylevels categoria, hbcategoryscalinggroups cat_grupo, hbscalinggroups grupo, hbscalinggroupusers grupo_usuario, hbusers usuario
        where categoria.cal_hbid=cat_grupo.cat_hbid(+) and cat_grupo.sgp_hbid= grupo.sgp_hbid(+) 
        and grupo_usuario.sgp_hbid(+)= grupo.sgp_hbid and grupo_usuario.usr_hbid=usuario.usr_hbid(+)
        and  (grupo.sgp_hbid != 1 or grupo.sgp_hbid is null) 
        and categoria.autoassign='S'
        and categoria.cal_hbid=v_service_code_ins;
        
        update hbrequests set RQT_GESTORDATE=sysdate, usr_hbid_manager=v_gestor, rst_id=1 where RQT_HBID=v_id;
        
        
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
          v_gestor := NULL;
        END;
    if p_zona_inspeccion is not null then
    --asociamos al inspector correspondiente y la marcamos como pendiente

        CASE 
            WHEN p_zona_inspeccion=1 or p_zona_inspeccion=4 THEN v_inspectorname := 'jaherrero';
            WHEN p_zona_inspeccion=2 or p_zona_inspeccion=3 THEN v_inspectorname := 'jlopezr';

            WHEN p_zona_inspeccion=5 THEN v_inspectorname := 'miroyo';
            WHEN p_zona_inspeccion=6 or p_zona_inspeccion=7 THEN v_inspectorname := 'adelaserna';
            WHEN p_zona_inspeccion=8 THEN v_inspectorname := 'papilluelo';
            WHEN p_zona_inspeccion=9 or p_zona_inspeccion=11 THEN v_inspectorname := 'bbenito';
            WHEN p_zona_inspeccion=10 THEN v_inspectorname := 'rjaime';
            WHEN p_zona_inspeccion=13 or p_zona_inspeccion=14 THEN v_inspectorname := 'jhernandezt';
            WHEN p_zona_inspeccion=15 or p_zona_inspeccion=16 THEN v_inspectorname := 'fmiravete';
            WHEN p_zona_inspeccion=17 THEN v_inspectorname := 'jbuisan';
            WHEN p_zona_inspeccion=18 or p_zona_inspeccion=12 THEN v_inspectorname := 'afranco';
            WHEN p_zona_inspeccion=19 THEN v_inspectorname := 'dperezm';
            WHEN p_zona_inspeccion=20 THEN v_inspectorname := '';
            WHEN p_zona_inspeccion=21 THEN v_inspectorname := 'jlazaro';
            ELSE v_inspectorname := '';
          END CASE;
          ASOCIAR(v_requestnumber, v_service_code, 4816905, p_usuarioadmin);
          update hbrequests set rst_id=10, RQT_INSPECTORDATE=sysdate, RQT_INSPECTORID=v_inspectorname, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    end if;
    if v_x is not null and v_y is not null then
      update hbrequests set RQT_GEOLOCATION=MDSYS.SDO_GEOMETRY(2001, 23030, MDSYS.SDO_POINT_TYPE(v_x,v_y, NULL), NULL, NULL) where rqt_hbid=v_id;
    end if;

    select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
    update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
    select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;

    INSERT INTO hbrequestactions 
          (rqa_hbid, rqa_hbversion, rqa_description, 
          rqa_creationdatetime, 
          rqa_requestactiondatetime,
          rqa_elapsedseconds, usr_hbid_agent, rqt_hbid_request, 
          rss_hbid_substate) 
          VALUES (v_actionnumber, /*version*/0, v_description, sysdate, sysdate, 
          /*ElapsedSeconds*/0, nvl(v_account_id,p_usuarioadmin), v_id, 
          /*substate*/3);

    if v_service_code is not null or p_agency_code is not null then
      ASOCIAR(v_requestnumber, v_service_code, p_agency_code, p_usuarioadmin);
    end if;

    if v_media_name is not null then

      INSERT INTO HBREQUESTLOADFILES (RLF_HBID, RLF_HBVERSION, RLF_DESCRIPTION, RLF_FILENAME, RQT_HBID_REQUEST, USR_HBID_USERLOADFILE) 
        VALUES (SEQ_HBREQUESTLOADFILES.nextval, /*version*/0, v_media_description, v_media_name, v_id, nvl(v_account_id,p_usuarioadmin));
    end if;

    commit;
    DETALLE(v_requestnumber,v_account_id,xmlsal);
  EXCEPTION
  
    WHEN v_UsuarioNoExistente THEN
          RAISE_APPLICATION_ERROR(-20002,'Usuario no existente');
          ROLLBACK TO dup_found;
    WHEN OTHERS THEN
          raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
          ROLLBACK TO dup_found;
  END CREAR;
  
  
  
  
  PROCEDURE GUARDAR(
        p_requestnumber number,
        p_service_code numeric default null,
                p_email hbusers.cli_email%type default null, 
                p_address_string hbusers.per_address%type default null, 
        p_address_id varchar2 default null,
                p_account_id varchar2 default null, 
                p_first_name varchar2 default null, 
                p_last_name varchar2 default null, 
                p_user_address hbusers.per_address%type default null, 
                p_phone varchar2 default null, 
                p_title varchar2 default null, 
                p_notes varchar2 default null, 
                p_description in hbrequests.rqt_creationdescription%type default null,  
                p_media_name hbrequestloadfiles.rlf_filename%type default null, 
        p_media_description hbrequestloadfiles.rlf_description%type default null, 
                p_publico varchar2 default null, 
                p_x number default null, 
                p_y number default null, 
      
      
        p_agency_code numeric default null,--tratado
        p_type numeric default null,
        p_priority numeric default null,
        p_origin numeric default null,
        p_validated varchar2 default null,--TRATADO
        --p_visible varchar2 default null,--TRATADO
    
        p_zona_inspeccion numeric default null,    
        p_fecha_prevista date default null,
        
        p_usuarioadmin varchar2 default null,
        p_answer_requested varchar2 default null,
      
        p_id_cita numeric default null,
        p_id_cat_sip numeric default null,
      
        xmlsal OUT CLOB
    )
  IS
   v_account_id number;
   v_email hbusers.cli_email%type;
   v_first_name varchar2(255);
   v_description hbrequests.rqt_creationdescription%type;
   v_user_address hbusers.per_address%type;
   v_phone varchar2(50);
   
   v_title varchar2(255);
   
   v_address_string hbusers.per_address%type;
   v_address_id varchar2(255);
   
   v_id number;
   v_actionnumber number;

   v_type number;
   v_origin number;
   v_priority number;
   v_publico varchar2(1);
   
   v_zona_inspeccion numeric;
   
   v_fecha_prevista date;
   v_service_code_ins numeric;
   v_UsuarioNoExistente EXCEPTION;
   v_id_cat_sip numeric;
   
  BEGIN
    select RQT_HBID into v_id from hbrequests where rqt_requestnumber=p_requestnumber;
    SAVEPOINT antesguardar;
    if p_validated is not null and p_publico is not null then
      update hbrequests set RQT_PUBLIC=p_publico, RQT_VALIDATED=p_validated where rqt_hbid=v_id;
    end if;
    
    if p_answer_requested is not null then
      update hbrequests set solicita_respuesta=p_answer_requested where rqt_hbid=v_id;
    end if;
    
    if p_service_code is not null or p_agency_code is not null then
      ASOCIAR(p_requestnumber, p_service_code, p_agency_code, p_usuarioadmin);
    end if;

    if p_x is not null and p_y is not null then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de coordenadas',sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
      update hbrequests set RQT_GEOLOCATION=MDSYS.SDO_GEOMETRY(2001, 23030, MDSYS.SDO_POINT_TYPE(p_x,p_y, NULL), NULL, NULL) where rqt_requestnumber=p_requestnumber;
    end if;
    
    if p_origin is not null and p_origin <> 3 then
        select rog_hbid into v_origin from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_origin<>v_origin then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set rog_hbid=p_origin where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de origen ' || v_origin || ' por ' || p_origin,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_origin := p_origin;
        end if;
    end if;
    if p_type is not null then
        select rty_hbid into v_type from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_type<>v_type then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set rty_hbid=p_type where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio tipo ' || p_type || ' por ' || v_type,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_type := p_type;
        end if;
    end if;
    
    if p_priority is not null then
        select rpt_hbid into v_priority from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_priority<>v_priority then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set rpt_hbid=p_priority where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio prioridad ' || p_priority || ' por ' || v_priority,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_priority := p_priority;
        end if;
    end if;
    if p_fecha_prevista is not null then
        select RQT_EXPECTEDRESOLUTIONDATETIME into v_fecha_prevista from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_fecha_prevista<>v_fecha_prevista then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set RQT_EXPECTEDRESOLUTIONDATETIME=p_fecha_prevista where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio fecha prevista resolución ' || to_char(p_fecha_prevista,'dd-mm-yyyy') || ' por ' || to_char(v_fecha_prevista,'dd-mm-yyyy'),sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_fecha_prevista := p_fecha_prevista;
        end if;
    end if;
    if p_email is not null or p_address_string is not null or p_address_id is not null or p_account_id is not null or p_first_name is not null or p_last_name is not null or
                p_user_address is not null or p_phone is not null or p_title is not null or p_description is not null or p_publico is not null then 

        SELECT description,
              title, 
              first_name, 
              email, 
              phone, 
              user_address, 
              address_string, 
              address_id into v_description,v_title,
                    v_first_name,v_email,v_phone,v_user_address,v_address_string,v_address_id
        from hbrequests where rqt_requestnumber=p_requestnumber;
        
        if p_description is not null and p_description<>v_description then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de descripción anterior por:' || p_description,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_description := p_description;
        end if;
        if p_title is not null and (v_title is null or p_title<>v_title) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de asunto ' || v_title || ' por ' || p_title,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_title := p_title;
        end if;
        if p_first_name is not null and (v_first_name is null or p_first_name<>v_first_name) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de nombre usuario ' || nvl(v_first_name,'vacío') || ' por ' || p_first_name,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_first_name := p_first_name;
        end if;
        if p_email is not null and (v_email is null or p_email<>v_email) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de correo de usuario ' || nvl(v_email,'vacío') || ' por ' || p_email,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_email := p_email;
        end if;
        if p_phone is not null and (v_phone is null or p_phone<>v_phone) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de telefono de usuario ' || nvl(v_phone,'vacío') || ' por ' || p_phone,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_phone := p_phone;
        end if;
        if p_user_address is not null and (v_user_address is null or p_user_address<>v_user_address) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de direccion de usuario ' || nvl(v_user_address,'vacío') || ' por ' || p_user_address,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_user_address := p_user_address;
        end if;
        if p_address_string is not null and (v_address_string is null or p_address_string<>v_address_string) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de direccion de queja ' || nvl(v_address_string,'vacío') || ' por ' || p_address_string,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_address_string := p_address_string;
        end if;
        if p_address_id is not null and (v_address_id is null or p_address_id<>v_address_id) then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de identificador de direccion de queja ' || nvl(v_address_id,'vacío') || ' por ' || p_address_id,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_address_id := p_address_id;


          begin
            select id_zona into v_zona_inspeccion from intra.portal_zona_inspeccion_parques where id_portal=p_address_id;
            update hbrequests set RQT_ZONAINSPECCIONID=v_zona_inspeccion where rqt_requestnumber=p_requestnumber;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            null;
          end;


        end if;
        
        update hbrequests set RQT_HBVERSION=RQT_HBVERSION + 1,
            description=v_description,
            title=v_title,
            first_name=v_first_name,
            email=v_email,
            phone=v_phone,
            user_address=v_user_address,
            address_string=v_address_string,
            address_id=v_address_id,
            rqt_creationdescription= v_description || '///' || v_title || '///' || nvl(v_first_name,'') || '///' 
            || nvl(v_email,'') || '///' || nvl(v_phone,'') ||'///' || nvl(v_user_address,'') || nvl2(v_address_string,'///' || v_address_string,'') || nvl2(v_address_id,'///' || v_address_id,'') 
        where rqt_requestnumber=p_requestnumber;
    end if;
     if p_id_cat_sip is not null then
        select id_cat_sip into v_id_cat_sip from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_id_cat_sip<>v_id_cat_sip or v_id_cat_sip is null then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set id_cat_sip=p_id_cat_sip where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio tipo SIP ' || p_id_cat_sip || ' por ' || v_id_cat_sip,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_id_cat_sip := p_id_cat_sip;
        end if;
    end if;
    if p_zona_inspeccion is not null then
        select RQT_ZONAINSPECCIONID into v_zona_inspeccion from hbrequests where rqt_requestnumber=p_requestnumber;
        if p_zona_inspeccion<>v_zona_inspeccion then
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          update hbrequests set RQT_ZONAINSPECCIONID=p_zona_inspeccion where rqt_requestnumber=p_requestnumber;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de zona de inspección ' || p_zona_inspeccion || ' por ' || v_zona_inspeccion,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0);
          v_zona_inspeccion := p_zona_inspeccion;
        end if;
    end if;
    if p_media_name is not null then
      
      INSERT INTO HBREQUESTLOADFILES (RLF_HBID, RLF_HBVERSION, RLF_DESCRIPTION, RLF_FILENAME, RQT_HBID_REQUEST, USR_HBID_USERLOADFILE) 
           VALUES (SEQ_HBREQUESTLOADFILES.nextval, /*version*/0, p_media_description, p_media_name, v_id, nvl(v_account_id,p_usuarioadmin));
    end if;
    
    commit;
    DETALLE(p_requestnumber,p_usuarioadmin,xmlsal);
  EXCEPTION
  
    WHEN v_UsuarioNoExistente THEN
          RAISE_APPLICATION_ERROR(-20002,'Usuario no existente');
          ROLLBACK TO dup_found;
    WHEN OTHERS THEN
          raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
          ROLLBACK TO dup_found;
  END GUARDAR;
  
  
  PROCEDURE ACCIONES(
        v_requestnumber number,
        p_accion numeric default null,
                v_texto hbrequestactions.rqa_description%type default null, 
                
        v_fecha date default null,
        
        v_idexterno numeric default null,
        
        p_usuarioadmin varchar2 default null,
        p_usuariogcz varchar2 default null,
        p_uuid varchar2 default null,
        
        p_estadointerno number default null,
    
        xmlsal OUT CLOB
    )
  IS
   v_id number;
   v_accion number;
   v_actionnumber number;
   v_inspectorname varchar2(60);
   v_UsuarioNoExistente EXCEPTION;
   v_estadoInterno varchar2(100);

  BEGIN
    SAVEPOINT acciones; 
    select RQT_HBID into v_id from hbrequests where rqt_requestnumber=v_requestnumber;
    
    v_accion := p_accion;
    if p_usuariogcz = 'ztc' then
      v_accion := 12;
    end if;
    
    if v_accion=1 then
    --resolver
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP, OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,1,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;

          if p_estadointerno is null then
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Resuelta',sysdate,sysdate,0,p_usuarioadmin,v_id,4,0,p_usuariogcz) ;
          else
            if p_estadointerno = 1 then
               v_estadoInterno := 'Obra Ejecutada';
            else
               v_estadoInterno := 'Sin Intervencion';
            end if;
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Resuelta con estado '|| v_estadointerno,sysdate,sysdate,0,p_usuarioadmin,v_id,4,0,p_usuariogcz) ;
          end if;

          update hbrequests set rst_id=4, RQT_RESOLVEDDATATIME=nvl2(v_fecha,v_fecha,sysdate), RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
          
           if p_estadointerno is not null then
              update hbrequests set INTERNAL_STATUS= p_estadointerno where RQT_HBID=v_id;
          end if;
          
    elsif v_accion=2 then
    --contestar
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,4,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          if p_estadointerno is null then
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Cerrada',sysdate,sysdate,0,p_usuarioadmin,v_id,2,0,p_usuariogcz) ;
          else
            if p_estadointerno = 1 then
               v_estadoInterno := 'Obra Ejecutada';
            else
               v_estadoInterno := 'Sin Intervencion';
            end if;
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Cerrada con estado '|| v_estadointerno,sysdate,sysdate,0,p_usuarioadmin,v_id,2,0,p_usuariogcz) ;
          end if;

          update hbrequests set rst_id=2, service_notice=v_texto, RQT_CLOSEDATETIME=sysdate, RQT_CLOSEDATE=sysdate, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;

          if p_estadointerno is not null then
              update hbrequests set INTERNAL_STATUS= p_estadointerno where RQT_HBID=v_id;
          end if;
    elsif v_accion=12 then
     --cerrar sin informar a ciudadano ni web
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,4,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Cerrada sin contestar',sysdate,sysdate,0,p_usuarioadmin,v_id,2,0,p_usuariogcz) ;

          update hbrequests set rst_id=2,service_notice=v_texto, RQT_CLOSEDATETIME=sysdate, RQT_CLOSEDATE=sysdate, RQT_RESOLVEDDATATIME=nvl2(v_fecha,v_fecha,sysdate), RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=3 then
    --archivar
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,9,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          if p_estadointerno is null then
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Archivada',sysdate,sysdate,0,p_usuarioadmin,v_id,2,0,p_usuariogcz) ;
          else 
            if p_estadointerno = 1 then
               v_estadoInterno := 'Obra Ejecutada';
            else
               v_estadoInterno := 'Sin Intervencion';
            end if;
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR)
            values(v_actionnumber,0,'Cambio de Estado a Archivada con estado '|| v_estadointerno,sysdate,sysdate,0,p_usuarioadmin,v_id,2,0,p_usuariogcz) ;
          end if;

          update hbrequests set rst_id=9, RQT_CLOSEDATETIME=sysdate, RQT_CLOSEDATE=sysdate, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
          if p_estadointerno is not null then
              update hbrequests set INTERNAL_STATUS= p_estadointerno where RQT_HBID=v_id;
          end if;
    elsif v_accion=4 then
    --eliminar
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,3,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Borrada',sysdate,sysdate,0,p_usuarioadmin,v_id,5,0,p_usuariogcz) ;
    
          update hbrequests set rst_id=5, RQT_CLOSEDATETIME=sysdate, RQT_CLOSEDATE=sysdate, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=5 then
    --solicitar mas informacion
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,2,3,0,p_usuariogcz);
    
    
          insert into HBREQUESTTOKEN (rqt_hbid,rqa_hbid,token) values(v_id,v_actionnumber,p_uuid);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Solicitada información',sysdate,sysdate,0,p_usuarioadmin,v_id,6,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=6, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=6 then
    --rechazar
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,3,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Rechazada',sysdate,sysdate,0,p_usuarioadmin,v_id,8,0,p_usuariogcz) ;
    
          update hbrequests set rst_id=8, RQT_CLOSEDATETIME=sysdate, RQT_CLOSEDATE=sysdate, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=7 then
    --derivar a la web
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,3,1,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Derivada a web',sysdate,sysdate,0,p_usuarioadmin,v_id,3,0,p_usuariogcz) ;
    
          update hbrequests set rst_id=3, cat_hbid=2, usr_hbid_manager=null, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=8 then
    --derivar a externo
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,3,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Derivada a externo',sysdate,sysdate,0,p_usuarioadmin,v_id,7,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=7, RQT_EXTERNODATE=sysdate, RQT_EXTERNOID=v_idexterno, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
  elsif v_accion=11 then
    --derivar a inspector
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,3,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          CASE 
            WHEN v_idexterno=1 THEN v_inspectorname := 'jaherrero';
            WHEN v_idexterno=2 THEN v_inspectorname := 'jlopezr';
            WHEN v_idexterno=3 THEN v_inspectorname := 'miroyo';
            WHEN v_idexterno=4 THEN v_inspectorname := 'adelaserna';
            WHEN v_idexterno=5 THEN v_inspectorname := 'clorente';
            WHEN v_idexterno=6 THEN v_inspectorname := 'cdominguez';
            WHEN v_idexterno=7 THEN v_inspectorname := 'bbenito';
            WHEN v_idexterno=8 THEN v_inspectorname := 'rjaime';
            WHEN v_idexterno=9 THEN v_inspectorname := 'jhernandezt';
            WHEN v_idexterno=10 THEN v_inspectorname := 'aisabel';
            WHEN v_idexterno=11 THEN v_inspectorname := 'fmiravete';
            WHEN v_idexterno=12 THEN v_inspectorname := 'jbuisan';
            WHEN v_idexterno=13 THEN v_inspectorname := 'afranco';
            WHEN v_idexterno=14 THEN v_inspectorname := 'dperezm';
            WHEN v_idexterno=15 THEN v_inspectorname := 'papilluelo';
            WHEN v_idexterno=16 THEN v_inspectorname := 'jlazaro';
            ELSE v_inspectorname := '';
          END CASE;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Derivada a inspector ' || v_inspectorname,sysdate,sysdate,0,p_usuarioadmin,v_id,10,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=10, RQT_INSPECTORDATE=sysdate, RQT_INSPECTORID=v_inspectorname, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
            
    elsif v_accion=9 then
    --informar al usuario
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,'Correo enviado al usuario:' || v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,2,3,0,p_usuariogcz);
    
    elsif v_accion=10 then
    --Accion personalizada
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,3,0,p_usuariogcz);
    
    elsif v_accion=13 then
    --realizada por valoracion
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,13,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Realizada por valoracion',sysdate,sysdate,0,p_usuarioadmin,v_id,13,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=13, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=14 then
    --pendiente por valoracion
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,14,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Pendiente por valoracion',sysdate,sysdate,0,p_usuarioadmin,v_id,14,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=14, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=20 then
    --rechazada por externo
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,11,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Rechazada por externo',sysdate,sysdate,0,p_usuarioadmin,v_id,11,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=11, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    elsif v_accion=21 then
    --ampliar información
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,1,12,0,p_usuariogcz);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
          values(v_actionnumber,0,'Cambio de Estado a Ampliar información',sysdate,sysdate,0,p_usuarioadmin,v_id,12,0,p_usuariogcz) ;
          
          update hbrequests set rst_id=12, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    end if;
    
    
    commit;
    DETALLE(v_requestnumber,p_usuarioadmin,xmlsal);
  EXCEPTION
  
    WHEN v_UsuarioNoExistente THEN
          RAISE_APPLICATION_ERROR(-20002,'Usuario no existente');
          ROLLBACK TO acciones;
    WHEN OTHERS THEN
          raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
          ROLLBACK TO acciones;
  END ACCIONES;
  
  
  PROCEDURE ESTADOANTERIORQUEJA(
        v_requestnumber number,
        v_accion numeric default null,
            v_texto hbrequestactions.rqa_description%type default null,        
        p_usuarioadmin varchar2 default null,
        p_usuariogcz varchar2 default null
    )
  IS
   v_id number;
   v_actionnumber number;
   v_inspectorname varchar2(60);
   v_UsuarioNoExistente EXCEPTION;
   v_estadoInterno varchar2(100);

  BEGIN
    SAVEPOINT acciones; 
    select RQT_HBID into v_id from hbrequests where rqt_requestnumber=v_requestnumber;
    
     select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
     update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
     select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
     insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP,OPERADOR) 
     values(v_actionnumber,0,v_texto,sysdate,sysdate,0,p_usuarioadmin,v_id,12,0,p_usuariogcz) ;
          
     update hbrequests set rst_id=v_accion, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
    
    commit;
  EXCEPTION
    WHEN OTHERS THEN
          raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
          ROLLBACK TO acciones;
  END ESTADOANTERIORQUEJA;
  
  
  PROCEDURE TOKEN(
        v_token varchar2 default null,
                v_texto hbrequestactions.rqa_description%type default null, 
        xmlsal OUT CLOB
    )IS
   v_id number;
   v_requestnumber number;
   v_actionnumber number;
   v_account_id number default null;
   v_UsuarioNoExistente EXCEPTION;
   
  BEGIN
    
    select RQT_HBID,rqa_hbid into v_id,v_actionnumber from hbrequesttoken where token=v_token;
    select rqt_requestnumber,usr_hbid_introducer into v_requestnumber,v_account_id from hbrequests where RQT_HBID=v_id;
    
    SAVEPOINT acciones; 
    select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RAT_HBID_TYPE, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,1,v_texto,sysdate,sysdate,0,v_account_id,v_id,1,3,0);
    
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de Estado a Pendiente',sysdate,sysdate,0,v_account_id,v_id,3,0) ;
          
          update hbrequests set rst_id=1, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_id;
          delete from hbrequesttoken where token=v_token;
          
    commit;
    DETALLE(v_requestnumber,v_account_id,xmlsal);
  EXCEPTION
  
    WHEN v_UsuarioNoExistente THEN
          RAISE_APPLICATION_ERROR(-20002,'Usuario no existente');
          ROLLBACK TO acciones;
    WHEN OTHERS THEN
          raise_application_error(-20001,SQLERRM);
          ROLLBACK TO acciones;
  END TOKEN;
  
  
  
  
  
  PROCEDURE ASOCIAR(
        v_id numeric,
        v_service_code numeric,
        v_agency_responsible numeric default null,
        v_user_id numeric
    )
    IS
      v_idinterno numeric;
      v_service_name varchar2(200);
      v_service_code_actual numeric;
      v_service_name_actual varchar2(200);
      v_agency_name varchar2(200);
      v_agency_code_actual numeric;
      v_agency_name_actual varchar2(200);
      v_group_user numeric;
      v_actionnumber numeric;
    BEGIN
      SAVEPOINT asociar; 
        select req.RQT_HBID,req.cat_hbid,cat.CAL_NAME into v_idinterno,v_service_code_actual,v_service_name_actual 
        from hbrequests req, HBCATEGORYLEVELS cat 
        where req.rqt_requestnumber=v_id and req.cat_hbid=cat.CAL_HBID;
        
        if v_service_code != v_service_code_actual then
          select padre.CAL_NAME ||'/'|| hijo.CAL_NAME into v_service_name 
          from HBCATEGORYLEVELS padre, HBCATEGORYLEVELS hijo  where hijo.cal_hbid=v_service_code and padre.cal_hbid=hijo.cal_parent;
          
          update hbrequests set cat_hbid=v_service_code, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_idinterno;
          
          select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
          select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
          
          insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
          values(v_actionnumber,0,'Cambio de categoría  ' || v_service_name_actual || ' por: ' || v_service_name,sysdate,sysdate,0,v_user_id,v_idinterno,3,0) ;
        end if;
        
        if v_agency_responsible is not null then
          select req.RQT_HBID,req.usr_hbid_manager, nvl2(usr.per_firstname, usr.per_firstname ||' '|| usr.per_lastname,'no asignado') into v_idinterno,v_agency_code_actual,v_agency_name_actual
          from hbrequests req, hbusers usr 
          where req.rqt_requestnumber=v_id and req.usr_hbid_manager=usr.usr_hbid(+);
          
          if v_agency_code_actual is null or v_agency_responsible != v_agency_code_actual then
            select per_firstname ||' '|| per_lastname into v_agency_name 
            from HBUSERS where usr_hbid=v_agency_responsible;
            
            
            
            select sgp_hbid into v_group_user from HBSCALINGGROUPUSERS where usr_hbid=v_agency_responsible;
            
            
            update hbrequests set RQT_GESTORDATE=sysdate, sgp_hbid=v_group_user, usr_hbid_manager=v_agency_responsible, rst_id=1, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_idinterno;
            
            select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
            select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
            values(v_actionnumber,0,'Cambio de Colaborador ' || v_agency_name_actual || ' por: ' || v_agency_name,sysdate,sysdate,0,v_user_id,v_idinterno,1,0) ;
            
            select REQACTIONID - 1 into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            update FO_HBREQACTIONSID_UNIQUE_KEY set REQACTIONID = v_actionnumber;
            select REQACTIONID into v_actionnumber from FO_HBREQACTIONSID_UNIQUE_KEY;
            
            insert into HBREQUESTACTIONS (RQA_HBID, RQA_HBVERSION, RQA_DESCRIPTION, RQA_CREATIONDATETIME, RQA_REQUESTACTIONDATETIME, RQA_ELAPSEDSECONDS, USR_HBID_AGENT, RQT_HBID_REQUEST, RSS_HBID_SUBSTATE, RSS_SLA_STOP) 
            values(v_actionnumber,0,'Cambio de Estado a Pendiente',sysdate,sysdate,0,v_user_id,v_idinterno,1,0) ;
            
            --update hbrequests set rst_id=1, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_idinterno;  
            
            --update hbrequests set rqt_closedate=null, rqt_closedatetime=null, rst_id=1, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_idinterno;
            update hbrequests set rqt_resolveddatatime=null, rqt_closedate=null, rqt_closedatetime=null, rst_id=1, RQT_HBVERSION=RQT_HBVERSION + 1 where RQT_HBID=v_idinterno;
            update HBREQUESTACTIONS set rqa_hbversion=0, RAT_HBID_TYPE=2,RSS_HBID_SUBSTATE=3 where RSS_HBID_SUBSTATE=4 and RQT_HBID_REQUEST=v_idinterno;
            
          end if;
        end if;

      COMMIT;
    EXCEPTION 
      WHEN OTHERS THEN
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
        ROLLBACK TO asociar;  
    END ASOCIAR;
  
  
  
   PROCEDURE INFORMES(
        v_start_date in date default null, 
        v_end_date in date default null,
        v_ids_categoria in varchar2 default null,
        /*v_distrito in numeric default null,
        v_barrio in numeric default null,*/
                v_texto varchar2 default null,
        usuarioTicketing in numeric default null,
        xmlsal OUT CLOB
    )IS
       TYPE values_t IS TABLE OF category_type;
        l_values   values_t;
        numero numeric;
        
        p_start_date date;
        p_end_date date;
        
        p_usuarioTicketing numeric;
        sfrom varchar2(1000);
        swhere varchar2(1000);
        
   v_UsuarioNoExistente EXCEPTION;
  BEGIN
    DBMS_LOB.createtemporary(xmlsal,TRUE);
    sfrom := ' FROM hbrequests hb_re, hbcategorylevels hb_le, HBREQUESTSTATES hb_st, hbusers usr,
                  (select max(RQA_REQUESTACTIONDATETIME) as fecha,RQT_HBID_REQUEST from HBREQUESTACTIONS group by RQT_HBID_REQUEST) acciones 
                  where ';
    swhere := 'hb_re.cat_hbid= hb_le.cal_hbid(+) AND hb_re.RST_ID=hb_st.RST_HBID(+) and hb_re.USR_HBID_MANAGER = usr.USR_HBID(+) AND acciones.RQT_HBID_REQUEST = hb_re.rqt_hbid ';
    
    if usuarioTicketing = 2 then
      p_usuarioTicketing := -1;
    else 
      p_usuarioTicketing := usuarioTicketing;
    end if;
    
    if v_start_date is null then
      p_start_date := to_date('01-01-2000', 'dd-mm-yyyy');
    else
      p_start_date := v_start_date;
    end if;
    if v_end_date is null then
      p_end_date := sysdate;
    else
      p_end_date := v_end_date;
    end if;
    if v_ids_categoria is not null then
        EXECUTE IMMEDIATE 'SELECT category_type(cal_hbid,cal_name) FROM HBCATEGORYLEVELS WHERE cal_hbid in ('|| v_ids_categoria ||')' BULK COLLECT INTO l_values;
        --xmlsal := xmlsal || '<resultado><totalCount>'|| l_values.COUNT ||'</totalCount>';
        
        dbms_lob.writeappend(xmlsal,length('<resultado><totalCount>'|| l_values.COUNT ||'</totalCount>'),'<resultado><totalCount>'|| l_values.COUNT ||'</totalCount>');
        
        
        FOR indx IN 1 .. l_values.COUNT
        LOOP
           -- xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
           --                       <id>'|| l_values(indx).cal_hbid ||'</id><title>'|| l_values(indx).cal_name ||'</title>';
           dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
                                  <id>'|| l_values(indx).cal_hbid ||'</id><title>'|| l_values(indx).cal_name ||'</title>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
                                  <id>'|| l_values(indx).cal_hbid ||'</id><title>'|| l_values(indx).cal_name ||'</title>');
           
           
            --TOTAL
            numero := GET_ROWS(sfrom || generarWhere(null,v_texto,null,l_values(indx).cal_hbid,null,null,null,null,p_start_date,p_end_date,null,null,null,p_usuarioTicketing,null,null,null,null,null,null,null,null) || swhere);

            --xmlsal := xmlsal || '<total>'|| numero ||'</total>';
            dbms_lob.writeappend(xmlsal,length('<total>'|| numero ||'</total>'),'<total>'|| numero ||'</total>');
            --CERRADAS o RESUELTAS
            numero := GET_ROWS(sfrom || generarWhere(null,v_texto,null,l_values(indx).cal_hbid,null,null,null,null,p_start_date,p_end_date,null,'2,4',null,p_usuarioTicketing,null,null,null,null,null, null,null,null) || swhere);
            --xmlsal := xmlsal || '<resueltas>'|| numero ||'</resueltas>';
            dbms_lob.writeappend(xmlsal,length('<resueltas>'|| numero ||'</resueltas>'),'<resueltas>'|| numero ||'</resueltas>');
            -- Pendientes de mas de un anyo
            numero := GET_ROWS('FROM hbrequests hb_re where hb_re.cat_hbid='|| l_values(indx).cal_hbid ||' AND hb_re.rst_id in (1,3,6,7) AND hb_re.rqt_introductiondatetime < add_months(sysdate,-12)');
            --xmlsal := xmlsal || '<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>';
            dbms_lob.writeappend(xmlsal,length('<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>'),'<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>');
            
            /* POR JUNTA CERRADAS*/
            FOR i IN (
              select j.nombre,count(*) as numero FROM intra.calle c, intra.junta j, intra.portalero p, hbrequests hb_re 
              where c.id=p.id and hb_re.rst_id in (2,4) and j.id_junta=p.id_jun and p.id_por=address_id 
              and CAT_HBID=l_values(indx).cal_hbid
              and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
              group by j.nombre
              )
            LOOP
              --xmlsal := xmlsal || '<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>';
              dbms_lob.writeappend(xmlsal,length('<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>'),'<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>');
            END LOOP;
            
            /* POR BARRIO PENDIENTES*/
            FOR i IN (
              select b.nombre,count(*) as numero FROM intra.calle c, intra.junta b, intra.portalero p, hbrequests hb_re 
              where c.id=p.id and hb_re.rst_id in (1,3,6,7) and b.id_junta=p.id_jun and p.id_por=address_id 
              and CAT_HBID=l_values(indx).cal_hbid
              and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
              group by b.nombre
              )
            LOOP
              --xmlsal := xmlsal || '<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>';
              dbms_lob.writeappend(xmlsal,length('<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>'),'<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>');
            END LOOP;
            /* CERRADAS POR ENTIDAD EXTERNA
            */
             FOR i IN (
              select b.nombre,count(*) as numero FROM HB_ENTIDADESEXTERNAS b, hbrequests hb_re 
              where hb_re.RQT_EXTERNOID=b.id and hb_re.rst_id in (2,4) 
              and CAT_HBID=l_values(indx).cal_hbid 
              and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
              group by b.nombre
              )
            LOOP
              --xmlsal := xmlsal || '<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>';
              dbms_lob.writeappend(xmlsal,length('<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>'),'<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>');
            END LOOP;
            /* PENDIENTES POR ENTIDAD EXTERNA */
            FOR i IN (
              select b.nombre,count(*) as numero FROM HB_ENTIDADESEXTERNAS b, hbrequests hb_re 
              where hb_re.RQT_EXTERNOID=b.id and hb_re.rst_id in (1,3,6,7) 
              and CAT_HBID=l_values(indx).cal_hbid 
              and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
              group by b.nombre
              )
            LOOP
              --xmlsal := xmlsal || '<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>';
              dbms_lob.writeappend(xmlsal,length('<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>'),'<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>');
            END LOOP;
            --xmlsal := xmlsal || '</result>';
            dbms_lob.writeappend(xmlsal,length('</result>'),'</result>');
        END LOOP;
    else
      --xmlsal := xmlsal || '<resultado><totalCount>1</totalCount>';
      dbms_lob.writeappend(xmlsal,length('<resultado><totalCount>1</totalCount>'),'<resultado><totalCount>1</totalCount>');
      --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
      --                        <id>1</id><title>Datos Generales</title>';
      dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
                              <id>1</id><title>Datos Generales</title>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="serviceConsulta">
                              <id>1</id><title>Datos Generales</title>');
        --TOTAL
        dbms_output.put_line(sfrom || generarWhere(null,v_texto,null,null,null,null,null,null,p_start_date,p_end_date,null,null,null,p_usuarioTicketing, null, null, null, null, null,null,null,null) || swhere);
        numero := GET_ROWS(sfrom || generarWhere(null,v_texto,null,null,null,null,null,null,p_start_date,p_end_date,null,null,null,p_usuarioTicketing, null, null, null, null, null,null,null,null) || swhere);

        --xmlsal := xmlsal || '<total>'|| numero ||'</total>';
        dbms_lob.writeappend(xmlsal,length('<total>'|| numero ||'</total>'),'<total>'|| numero ||'</total>');
        --CERRADAS o RESUELTAS
        numero := GET_ROWS(sfrom || generarWhere(null,v_texto,null,null,null,null,null,null,p_start_date,p_end_date,null,'2,4',null,p_usuarioTicketing, null, null, null, null, null,null,null,null) || swhere);
        --xmlsal := xmlsal || '<resueltas>'|| numero ||'</resueltas>';
        dbms_lob.writeappend(xmlsal,length('<resueltas>'|| numero ||'</resueltas>'),'<resueltas>'|| numero ||'</resueltas>');
        
        -- Pendientes de mas de un anyo
        numero := GET_ROWS('FROM hbrequests hb_re where hb_re.rst_id in (1,3,6,7) AND hb_re.rqt_introductiondatetime < add_months(sysdate,-12)');
        --xmlsal := xmlsal || '<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>';
        dbms_lob.writeappend(xmlsal,length('<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>'),'<pendientes_mas_1y>'|| numero ||'</pendientes_mas_1y>');
        
        
        /* POR BARRIO CERRADAS*/
        FOR i IN (
          select b.nombre,count(*) as numero FROM intra.calle c, intra.junta b, intra.portalero p, hbrequests hb_re 
          where c.id=p.id and hb_re.rst_id in (2,4) and b.id_junta=p.id_jun and p.id_por=address_id 
          and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
          and (hb_re.USR_HBID_INTRODUCER=usuarioTicketing or hb_re.USR_HBID_MANAGER=usuarioTicketing or usuarioTicketing=2)
          group by b.nombre
          )
        LOOP
          --xmlsal := xmlsal || '<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>';
          dbms_lob.writeappend(xmlsal,length('<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>'),'<barrio_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_resuelta>');
        END LOOP;
        
        /* POR BARRIO PENDIENTES*/
        FOR i IN (
          select b.nombre,count(*) as numero FROM intra.calle c, intra.junta b, intra.portalero p, hbrequests hb_re 
          where c.id=p.id and hb_re.rst_id in (1,3,6,7,10) and b.id_junta=p.id_jun and p.id_por=address_id 
          and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
          and (hb_re.USR_HBID_INTRODUCER=usuarioTicketing or hb_re.USR_HBID_MANAGER=usuarioTicketing or usuarioTicketing=2)
          group by b.nombre
          )
        LOOP
          --xmlsal := xmlsal || '<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>';
          dbms_lob.writeappend(xmlsal,length('<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>'),'<barrio_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></barrio_pendiente>');
        END LOOP;
        /* CERRADAS POR ENTIDAD EXTERNA
        */
         FOR i IN (
          select b.nombre,count(*) as numero FROM HB_ENTIDADESEXTERNAS b, hbrequests hb_re 
          where hb_re.RQT_EXTERNOID=b.id and hb_re.rst_id in (2,4)
          and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
          and (hb_re.USR_HBID_INTRODUCER=usuarioTicketing or hb_re.USR_HBID_MANAGER=usuarioTicketing or usuarioTicketing=2)
          group by b.nombre
          )
        LOOP
          --xmlsal := xmlsal || '<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>';
          dbms_lob.writeappend(xmlsal,length('<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>'),'<entidad_resuelta><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_resuelta>');
        END LOOP;
        /* PENDIENTES POR ENTIDAD EXTERNA */
        FOR i IN (
          select b.nombre,count(*) as numero FROM HB_ENTIDADESEXTERNAS b, hbrequests hb_re 
          where hb_re.RQT_EXTERNOID=b.id and hb_re.rst_id in (1,3,6,7) 
          and hb_re.rqt_introductiondatetime between p_start_date and p_end_date
          and (hb_re.USR_HBID_INTRODUCER=usuarioTicketing or hb_re.USR_HBID_MANAGER=usuarioTicketing or usuarioTicketing=2)
          group by b.nombre
          )
        LOOP
          --xmlsal := xmlsal || '<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>';
          dbms_lob.writeappend(xmlsal,length('<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>'),'<entidad_pendiente><nombre>'|| i.nombre || '</nombre><numero>' || i.numero ||'</numero></entidad_pendiente>');
        END LOOP;
        --xmlsal := xmlsal || '</result>';
        dbms_lob.writeappend(xmlsal,length('</result>'),'</result>');
    
    end if;
    --xmlsal := xmlsal || '</resultado>';
    dbms_lob.writeappend(xmlsal,length('</resultado>'),'</resultado>');
  EXCEPTION
  
    WHEN v_UsuarioNoExistente THEN
          RAISE_APPLICATION_ERROR(-20002,'Usuario no existente');
          ROLLBACK TO acciones;
    WHEN OTHERS THEN
          raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
          ROLLBACK TO acciones;
  END INFORMES;
  
  
  PROCEDURE CATEGORIA(
       rootCategory number default null,
       xmlsal OUT CLOB
  )
   IS
   
   tiene2nivel boolean;
   tiene3nivel boolean;
   
   norows EXCEPTION;
   PRAGMA EXCEPTION_INIT(norows, -30625);
   BEGIN
   
      DBMS_LOB.createtemporary(xmlsal,TRUE);
   
      xmlsal := xmlsal || '<resultado>';
      FOR nivel1 IN (
              select 
                  c1.CAL_HBID as service_code,
                  c1.CAL_NAME as service_name,
                  d1.CAT_TEMPLATEREQUESTDESCRIPTION as service_description,
                  usuario1.usr_hbid as agency_responsible_code,
                  usuario1.per_firstname ||' ' || usuario1.per_lastname as agency_responsible,
                  grupo1.SGP_HBID as group_code,
                  grupo1.sgp_name as group_name,
                  c1.cal_parent as service_parent_code,
                  c1.cal_level as service_level,
                  c1.autoassign as autoassign
                  
                  FROM HBCATEGORYLEVELS c1, 
                  hbcategories d1,                 
                  hbcategoryscalinggroups cat_grupo1, 
                  hbscalinggroups grupo1, 
                  hbscalinggroupusers grupo_usuario1, 
                  hbusers usuario1
                                    
                  where 
                  c1.cal_hbid=d1.cat_hbid(+) 
                  and c1.cal_parent=rootCategory
                  and c1.cal_hbid=cat_grupo1.cat_hbid(+)
                  and cat_grupo1.sgp_hbid=grupo1.sgp_hbid(+) 
                  and grupo_usuario1.sgp_hbid(+)= grupo1.sgp_hbid 
                  and grupo_usuario1.usr_hbid=usuario1.usr_hbid(+)
                  and (grupo1.sgp_hbid != 1 or grupo1.sgp_hbid is null)
                  order by c1.cal_name
              )
            LOOP
              tiene2nivel:=false;
              FOR nivel2 IN (
                select 
                  c2.CAL_HBID as service_code,
                  c2.CAL_NAME as service_name,
                  d2.CAT_TEMPLATEREQUESTDESCRIPTION as service_description,
                  usuario2.usr_hbid as agency_responsible_code,
                  usuario2.per_firstname ||' ' || usuario2.per_lastname as agency_responsible,
                  grupo2.SGP_HBID as group_code,
                  grupo2.sgp_name as group_name,
                  c2.cal_parent as service_parent_code,
                  c2.cal_level as service_level,
                  c2.autoassign as autoassign
                  
                  FROM HBCATEGORYLEVELS c2, 
                  hbcategories d2,                 
                  hbcategoryscalinggroups cat_grupo2, 
                  hbscalinggroups grupo2, 
                  hbscalinggroupusers grupo_usuario2, 
                  hbusers usuario2
                                    
                  where 
                  c2.cal_hbid=d2.cat_hbid(+) 
                  and c2.cal_parent=nivel1.service_code
                  and c2.cal_hbid=cat_grupo2.cat_hbid(+)
                  and cat_grupo2.sgp_hbid=grupo2.sgp_hbid(+) 
                  and grupo_usuario2.sgp_hbid(+)= grupo2.sgp_hbid 
                  and grupo_usuario2.usr_hbid=usuario2.usr_hbid(+)
                  and (grupo2.sgp_hbid != 1 or grupo2.sgp_hbid is null)
                  order by c2.cal_name
                )
              LOOP
                if tiene2nivel=false then
                    --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; Por defecto</SERVICE_NAME><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE></result>';
                    dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; Por defecto</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel1.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel1.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel1.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel1.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel1.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel1.service_level || '</SERVICE_LEVEL></result>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; Por defecto</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel1.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel1.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel1.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel1.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel1.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel1.service_level || '</SERVICE_LEVEL></result>');
                end if;
                tiene2nivel:=true;                
                
                tiene3nivel := false;
                FOR nivel3 IN (
                    select 
                    c3.CAL_HBID as service_code,
                    c3.CAL_NAME as service_name,
                    d3.CAT_TEMPLATEREQUESTDESCRIPTION as service_description,
                    usuario3.usr_hbid as agency_responsible_code,
                    usuario3.per_firstname ||' ' || usuario3.per_lastname as agency_responsible,
                    grupo3.SGP_HBID as group_code,
                    grupo3.sgp_name as group_name,
                    c3.cal_parent as service_parent_code,
                    c3.cal_level as service_level,
                    c3.autoassign as autoassign
                    
                    FROM HBCATEGORYLEVELS c3, 
                    hbcategories d3,                 
                    hbcategoryscalinggroups cat_grupo3, 
                    hbscalinggroups grupo3, 
                    hbscalinggroupusers grupo_usuario3, 
                    hbusers usuario3
                                      
                    where 
                    c3.cal_hbid=d3.cat_hbid(+) 
                    and c3.cal_parent=nivel2.service_code
                    and c3.cal_hbid=cat_grupo3.cat_hbid(+)
                    and cat_grupo3.sgp_hbid=grupo3.sgp_hbid(+) 
                    and grupo_usuario3.sgp_hbid(+)= grupo3.sgp_hbid 
                    and grupo_usuario3.usr_hbid=usuario3.usr_hbid(+)
                    and (grupo3.sgp_hbid != 1 or grupo3.sgp_hbid is null)
                    order by c3.cal_name
                )
                LOOP
                  if tiene3nivel=false then
                    --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; Por defecto</SERVICE_NAME><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE></result>';
                    dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; Por defecto</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel2.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel2.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel2.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel2.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel2.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel2.service_level || '</SERVICE_LEVEL></result>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; Por defecto</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel2.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel2.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel2.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel2.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel2.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel2.service_level || '</SERVICE_LEVEL></result>');
                  end if;
                  tiene3nivel:=true;
                  --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel3.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; ' || nivel3.service_name || '</SERVICE_NAME><AGENCY_RESPONSIBLE_CODE>' || nivel3.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel3.agency_responsible || '</AGENCY_RESPONSIBLE></result>';
                  dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel3.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; ' || nivel3.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel3.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel3.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel3.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel3.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel3.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel3.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel3.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel3.service_level || '</SERVICE_LEVEL></result>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel3.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || ' &gt; ' || nivel3.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel3.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel3.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel3.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel3.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel3.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel3.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel3.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel3.service_level || '</SERVICE_LEVEL></result>');
                END LOOP;
                if tiene3nivel=false then
                  --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || '</SERVICE_NAME><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE></result>';
                  dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel2.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel2.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel2.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel2.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel2.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel2.service_level || '</SERVICE_LEVEL></result>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel2.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || ' &gt; ' || nivel2.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel2.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel2.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel2.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel2.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel2.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel2.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel2.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel2.service_level || '</SERVICE_LEVEL></result>');
                end if;
              END LOOP;
              if tiene2nivel=false then
                --xmlsal := xmlsal || '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || '</SERVICE_NAME><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE></result>';
                dbms_lob.writeappend(xmlsal,length('<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel1.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel1.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel1.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel1.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel1.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel1.service_level || '</SERVICE_LEVEL></result>'),'<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="category"><SERVICE_CODE>' || nivel1.service_code || '</SERVICE_CODE><SERVICE_NAME>' || nivel1.service_name || '</SERVICE_NAME><SERVICE_DESCRIPTION>' || nivel1.service_description || '</SERVICE_DESCRIPTION><AGENCY_RESPONSIBLE_CODE>' || nivel1.agency_responsible_code || '</AGENCY_RESPONSIBLE_CODE><AGENCY_RESPONSIBLE>' || nivel1.agency_responsible || '</AGENCY_RESPONSIBLE><GROUP_CODE>' || nivel1.group_code || '</GROUP_CODE><GROUP_NAME>' || nivel1.group_name || '</GROUP_NAME><AUTOASSIGN>' || nivel1.autoassign || '</AUTOASSIGN><SERVICE_PARENT_CODE>' || nivel1.service_parent_code || '</SERVICE_PARENT_CODE><SERVICE_LEVEL>' || nivel1.service_level || '</SERVICE_LEVEL></result>');
              end if;
            END LOOP;
      --xmlsal := xmlsal || '</resultado>';
      dbms_lob.writeappend(xmlsal,length('</resultado>'),'</resultado>');
  EXCEPTION 
    when norows then
      --xmlsal :='<resultado/>';
      dbms_lob.writeappend(xmlsal,length('</resultado>'),'</resultado>');
      raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
    when others then
      raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
  END CATEGORIA;
END PCK_QYS_REQUESTS;
/
create or replace PACKAGE             "PCK_QYS_SERVICES" AS TYPE cursorRetorno IS REF CURSOR;
    PROCEDURE GET(filas IN number, inicio IN number, xml OUT clob, total OUT number);
END PCK_QYS_SERVICES;
/
create or replace PACKAGE BODY             "PCK_QYS_SERVICES" AS

   PROCEDURE GET(filas IN number, inicio IN number, xml OUT clob, total OUT number)
    IS
        -----------------------------------------------------------------
        -- DECLARACIONES
        -----------------------------------------------------------------
   l_xmltype XMLTYPE;
   l_ctx dbms_xmlgen.ctxhandle;
    BEGIN
        --OPEN pResultado FOR
      --SELECT XMLELEMENT("service", XMLELEMENT("service_code", c.CAL_HBID), XMLELEMENT ( "service_name", c.CAL_NAME))
    --FROM HBCATEGORYLEVELS c ;

    select count(*)  into total FROM HBCATEGORYLEVELS;

    l_ctx := dbms_xmlgen.newcontext('SELECT CAL_HBID as service_code,
          CAL_NAME service_name,
          d.cat_templaterequestdescription as description
          FROM HBCATEGORYLEVELS c, hbcategories d
          where c.cal_hbid not in(2,109740032,109740033,109740034,4849667,5144584) and c.cal_hbid=d.cat_hbid and c.cal_iscategory=1 and c.cal_hbid in (select cat_hbid from hbrequests where rst_id!=5)
          ');

   dbms_xmlgen.setMaxRows(l_ctx,filas);
   dbms_xmlgen.setrowsettag(l_ctx, 'resultado');
   dbms_xmlgen.setrowtag (l_ctx, 'result');
   l_xmltype := dbms_xmlgen.getxmltype (l_ctx);
   l_xmltype := xmltype(replace(l_xmltype.getstringval(), '<result>', '<result xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="service">'));
   dbms_xmlgen.closecontext (l_ctx);
   xml := l_xmltype.getClobVal;
  END GET;

END PCK_QYS_SERVICES;