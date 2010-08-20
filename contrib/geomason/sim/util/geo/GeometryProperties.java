package sim.util.geo;

import java.util.*;

/**
 * GeometryProperties allows the inspection of JTS geometries (ostensibly contained within MasonGeometry objects).  The 
 * names and data types are taken from the underlying attribute fields of the GIS files.
 * 
 *   <p> Currently, the number and ordering of the attributes is fixed, but this might change in the future.  However, 
 *   the value of the attributes can be changed.   
 * 
 */

public class GeometryProperties extends sim.util.Properties implements java.io.Serializable {

	private static final long serialVersionUID = -8373893417608186127L;

	/** Holds information about all the attributes associated with the JTS geometry */ 
	public ArrayList<AttributeField> attributes = new ArrayList<AttributeField>(); 
	
	/** Constructor.  Sets the internal attribute list, if the provided list is not null.  */ 
	public GeometryProperties(ArrayList<AttributeField> attrs) { 
		if (attrs != null) 
			attributes = attrs; 
	}
	
	/** The number and order of the attributes is fixed.  Might change in future releases. */ 
	public boolean isVolatile() { return false; } 
	
	/** How many attributes are associated with the JTS geometry */ 
	public int numProperties() { return attributes.size(); } 
	
	/** Every attribute is both readable and writable. */ 
	public boolean isReadWrite(int index) { return true; } 

	/** Returns the value of the attribute */ 
	public Object getValue(int index) {
		if (index < 0 || index >= numProperties()) return null; 
		return attributes.get(index).value; 
	}

	/** Return the name of the attribute */
	public String getName(int index) {
		if (index < 0 || index >= numProperties()) return null; 
		return attributes.get(index).name; 
	}

	/** Returns the Java class type of the attribute */ 
	public Class<?> getType(int index) {
		if (index < 0 || index >= numProperties()) return null; 
		return getTypeConversion(attributes.get(index).value.getClass()); 
	}
	
	/** Returns if the attribute should be displayed in the inspector or not */ 
	public boolean isHidden(int index) 
	{
		if (index < 0 || index >= numProperties()) return false; 
		return attributes.get(index).hidden; 
	}
	
	/** Updated the value of the attribute */ 
	protected Object _setValue(int index, Object value) 
	{
		if (index < 0 || index >= numProperties()) return null; 
		attributes.get(index).value = value; 
		return getValue(index); 
	}
}
