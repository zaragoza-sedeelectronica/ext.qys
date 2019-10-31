package org.sede.servicio.citaprevia.entity;


public class CitaPreviaException extends Exception {
	 public static final int INFO=0;
	    public static final int WARNING=1;
	    public static final int ERROR=2;
	    
	    public static final int ERR_20001 = 20001; 
	    public static final int ERR_20002 = 20002;
	    public static final int ERR_20003 = 20003;
	    public static final int ERR_20004 = 20004;
	    public static final int ERR_20005 = 20005;
	    public static final int ERR_20006 = 20006;
	    public static final int ERR_20007 = 20007;
	    public static final int ERR_20008 = 20008;
	    
	 // Propiedad Nivel
	    private int nivel;
	    public int getNivel() {
	        return nivel;
	    }
	    public void setNivel(int v) {
	        nivel = v;
	    }

	    
	    /** Creates a new instance of CitaPreviaException */
	    public CitaPreviaException(int nivel, String msg) {
	        this(nivel,msg,null);
	    }
	    
	    /** Creates a new instance of CitaPreviaException */
	    public CitaPreviaException(String msg) {
	        this(WARNING,msg,null);
	    }
	    
	    /** Creates a new instance of CitaPreviaException */
	    public CitaPreviaException(Exception ex) {
	        this(ERROR,"comun.error",ex);
	    }
	    
	    /** Creates a new instance of CitaPreviaException */
	    public CitaPreviaException(int nivel, Throwable ex) {
	        this(nivel,"comun.error",ex);
	    }
	    
	    /** Creates a new instance of CitaPreviaException */
	    public CitaPreviaException(int nivel, String msg, Throwable ex) {
	        super(msg,ex);
	        this.nivel = nivel;
	    }
	    public static CitaPreviaException wrap(Throwable ex) {
	        if(ex.getMessage().contains("ErrorIdAsunto")){
	        	return wrap(ERROR,"citaprevia.error.asunto", ex);
	        } else {
	        	return wrap(ERROR,"comun.error",ex);
	        }
	    }
	    public static CitaPreviaException wrap(int nivel, String msg, Throwable ex) {
	        if (CitaPreviaException.class.isInstance(ex)) {
	            return (CitaPreviaException)ex;
	        }
	        else {
	            return new CitaPreviaException(nivel,msg,ex);
	        }
	    }
}
