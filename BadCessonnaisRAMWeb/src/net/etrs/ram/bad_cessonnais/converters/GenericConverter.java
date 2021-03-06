package net.etrs.ram.bad_cessonnais.converters;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GenericConverter implements Converter, Serializable 
{
	private BidiMap temporaryStore;

	@PostConstruct
	public final void init()
	{
		temporaryStore = new DualHashBidiMap();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
	{
		try
		{
			UUID key = UUID.fromString(value);
			if (key != null && temporaryStore.containsKey(key))
			{
				return temporaryStore.get(key); 
			}
			else
			{
			    return value;
			}
		}
		catch (IllegalArgumentException ex)
		{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
		if (value == null)
		{
			return null;
		} 
		else 
		{
			if (!temporaryStore.containsValue(value)) 
			{
				UUID id = UUID.randomUUID();
				temporaryStore.put(id, value);
				return id.toString();
			} else 
			{
				return temporaryStore.getKey(value).toString();
			}
		}
	}
}
