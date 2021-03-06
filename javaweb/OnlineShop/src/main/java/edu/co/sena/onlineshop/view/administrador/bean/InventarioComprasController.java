package edu.co.sena.onlineshop.view.administrador.bean;

import edu.co.sena.onlineshop.model.entities.InventarioCompras;
import edu.co.sena.onlineshop.view.general.util.JsfUtil;
import edu.co.sena.onlineshop.view.general.util.JsfUtil.PersistAction;
import edu.co.sena.onlineshop.contoller.administrador.beans.InventarioComprasFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("inventarioComprasController")
@SessionScoped
public class InventarioComprasController implements Serializable {

    @EJB
    private edu.co.sena.onlineshop.contoller.administrador.beans.InventarioComprasFacade ejbFacade;
    private List<InventarioCompras> items = null;
    private InventarioCompras selected;

    public InventarioComprasController() {
    }

    public InventarioCompras getSelected() {
        return selected;
    }

    public void setSelected(InventarioCompras selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getInventarioComprasPK().setProveedorNumDocumento(selected.getProveedor().getProveedorPK().getNumDocumento());
        selected.getInventarioComprasPK().setProveedorTipoDocumentoTipoDocumento(selected.getProveedor().getProveedorPK().getTipoDocumentoTipoDocumento());
        selected.getInventarioComprasPK().setProductoIdProducto(selected.getProducto().getIdProducto());
    }

    protected void initializeEmbeddableKey() {
        selected.setInventarioComprasPK(new edu.co.sena.onlineshop.model.entities.InventarioComprasPK());
    }

    private InventarioComprasFacade getFacade() {
        return ejbFacade;
    }

    public InventarioCompras prepareCreate() {
        selected = new InventarioCompras();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InventarioComprasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InventarioComprasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InventarioComprasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<InventarioCompras> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public InventarioCompras getInventarioCompras(edu.co.sena.onlineshop.model.entities.InventarioComprasPK id) {
        return getFacade().find(id);
    }

    public List<InventarioCompras> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<InventarioCompras> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = InventarioCompras.class)
    public static class InventarioComprasControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventarioComprasController controller = (InventarioComprasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarioComprasController");
            return controller.getInventarioCompras(getKey(value));
        }

        edu.co.sena.onlineshop.model.entities.InventarioComprasPK getKey(String value) {
            edu.co.sena.onlineshop.model.entities.InventarioComprasPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new edu.co.sena.onlineshop.model.entities.InventarioComprasPK();
            key.setIdinventario(Integer.parseInt(values[0]));
            key.setProductoIdProducto(values[1]);
            key.setProveedorTipoDocumentoTipoDocumento(values[2]);
            key.setProveedorNumDocumento(values[3]);
            return key;
        }

        String getStringKey(edu.co.sena.onlineshop.model.entities.InventarioComprasPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdinventario());
            sb.append(SEPARATOR);
            sb.append(value.getProductoIdProducto());
            sb.append(SEPARATOR);
            sb.append(value.getProveedorTipoDocumentoTipoDocumento());
            sb.append(SEPARATOR);
            sb.append(value.getProveedorNumDocumento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof InventarioCompras) {
                InventarioCompras o = (InventarioCompras) object;
                return getStringKey(o.getInventarioComprasPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InventarioCompras.class.getName()});
                return null;
            }
        }

    }

}
