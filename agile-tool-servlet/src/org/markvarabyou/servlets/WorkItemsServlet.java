package org.markvarabyou.servlets;

import org.markvarabyou.services.exceptions.EntityCreationFailedException;
import org.markvarabyou.services.exceptions.EntityNotFoundException;
import org.markvarabyou.services.exceptions.EntityUpdateFailedException;
import org.markvarabyou.services.exceptions.InternalDaoException;
import org.markvarabyou.servlets.utilities.ServiceFactory;
import org.markvarabyou.servlets.utilities.ServletUtilities;
import org.markvarabyou.servlets.utilities.exceptions.ServiceSetupException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class for WorkItems.
 * User: Mark Varabyou
 * Date: 12/2/13
 * Time: 3:18 PM
 */
public class WorkItemsServlet extends HttpServlet {
    private ServiceFactory services = new ServiceFactory();
    private ServletUtilities utilities = new ServletUtilities();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer id = utilities.getIdFromRequestParameter(request);
        if (id == null)
        {
            String json = null;
            try {
                json = services.getWorkItemsService().get();
            } catch (InternalDaoException e) {
                response.setStatus(400);
            } catch (ServiceSetupException e) {
                response.setStatus(400);
            }
            utilities.setJsonToResponseBody(response, json);
            return;
        }
        // Id not provided, returning all entities.
        try {
            String json = services.getWorkItemsService().get(id);
            utilities.setJsonToResponseBody(response, json);
        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        } catch (InternalDaoException e) {
            response.setStatus(400);
        } catch (ServiceSetupException e) {
            response.setStatus(400);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String inputJson = utilities.getJsonFromRequestBody(request);
        try {
            String outputJson = services.getWorkItemsService().post(inputJson);
            utilities.setJsonToResponseBody(response, outputJson);
        } catch (EntityCreationFailedException e) {
            response.setStatus(400);
        } catch (ServiceSetupException e) {
            response.setStatus(400);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        Integer id = utilities.getIdFromRequestParameter(request);
        String inputJson = utilities.getJsonFromRequestBody(request);
        if (id != null)
        {
            try {
                String outputJson = services.getWorkItemsService().put(id, inputJson);
                utilities.setJsonToResponseBody(response, outputJson);
            } catch (EntityUpdateFailedException e) {
                response.setStatus(400);
            } catch (ServiceSetupException e) {
                response.setStatus(400);
            }
            return;
        }
        response.setStatus(404);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Integer id = utilities.getIdFromRequestParameter(request);
        try {
            services.getWorkItemsService().delete(id);
        } catch (EntityNotFoundException e) {
            response.setStatus(404);
        } catch (ServiceSetupException e) {
            response.setStatus(400);
        } catch (InternalDaoException e) {
            response.setStatus(400);
        }
    }
}
