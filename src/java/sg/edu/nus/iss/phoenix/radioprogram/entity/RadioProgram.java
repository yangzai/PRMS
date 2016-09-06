package sg.edu.nus.iss.phoenix.radioprogram.entity;

import java.io.Serializable;
import java.sql.Time;

public class RadioProgram implements Cloneable, Serializable {

    /**
	 * eclipse identifier
	 */
	private static final long serialVersionUID = -5500218812568593553L;
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String name;
    private String description;
    private Time typicalDuration;



    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public RadioProgram () {

    }

    public RadioProgram (String nameIn) {

          this.name = nameIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */

    public String getName() {
          return this.name;
    }
    public void setName(String nameIn) {
          this.name = nameIn;
    }

    public String getDescription() {
          return this.description;
    }
    public void setDescription(String descriptionIn) {
          this.description = descriptionIn;
    }

    public Time getTypicalDuration() {
          return this.typicalDuration;
    }
    public void setTypicalDuration(Time typicalDurationIn) {
          this.typicalDuration = typicalDurationIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param nameIn
     * @param descriptionIn
     * @param typicalDurationIn
     */

    public void setAll(String nameIn,
          String descriptionIn,
          Time typicalDurationIn) {
          this.name = nameIn;
          this.description = descriptionIn;
          this.typicalDuration = typicalDurationIn;
    }


    /** 
     * hasEqualMapping-method will compare two RadioProgram instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(RadioProgram valueObject) {

          if (this.name == null) {
                    if (valueObject.getName() != null)
                           return(false);
          } else if (!this.name.equals(valueObject.getName())) {
                    return(false);
          }
          if (this.description == null) {
                    if (valueObject.getDescription() != null)
                           return(false);
          } else if (!this.description.equals(valueObject.getDescription())) {
                    return(false);
          }
          if (this.typicalDuration == null) {
                    if (valueObject.getTypicalDuration() != null)
                           return(false);
          } else if (!this.typicalDuration.equals(valueObject.getTypicalDuration())) {
                    return(false);
          }

          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nRadioProgram class, mapping to table radio-program\n");
        out.append("Persistent attributes: \n"); 
        out.append("name = ").append(this.name).append("\n"); 
        out.append("description = ").append(this.description).append("\n"); 
        out.append("typicalDuration = ").append(this.typicalDuration).append("\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        RadioProgram cloned = new RadioProgram();

        if (this.name != null)
             cloned.setName(this.name); 
        if (this.description != null)
             cloned.setDescription(this.description); 
        if (this.typicalDuration != null)
             cloned.setTypicalDuration((Time)this.typicalDuration.clone()); 
        return cloned;
    }



}